package com.wallnit.wallnitlogin;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitRuntimePermission.WallnitRuntimePermission;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class WallnitUserUploadPhotos extends WallnitRuntimePermission implements View.OnClickListener{

    ImageView img_gallery,img_camera,imageshow;
    public static final int REQUEST_CAPTURE = 1;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String SERVER_ADDRESS = "http://www.wallnit.com/wallnit_android/";
    EditText imagefind,edtxtPostAnonymous,postUserCircle,caption_uploadpost,total_num_image;
    CheckBox postanonymous_check;
    Session session;
    private static final int REQUEST_PERMISSION = 10;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_upload_photos);


        requestAppPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                R.string.runtime_permission_msg,REQUEST_PERMISSION);

        img_gallery = (ImageView) findViewById(R.id.user_photos_gallery);
        img_camera = (ImageView) findViewById(R.id.user_photos_camera);
        imageshow = (ImageView) findViewById(R.id.user_uploadphotos);
        imagefind = (EditText) findViewById(R.id.image_which_one);
        edtxtPostAnonymous = (EditText) findViewById(R.id.eduser_photospost_anonymous);
        postUserCircle = (EditText) findViewById(R.id.userpost_userorcircle);
        postanonymous_check = (CheckBox) findViewById(R.id.user_upload_photospost_anonymous);
        caption_uploadpost = (EditText) findViewById(R.id.user_upload_captionpost);
        total_num_image = (EditText) findViewById(R.id.userpost_totalnum_image);
        session = new Session(this);

        cd = new ConnectionDetector(this);

        img_gallery.setOnClickListener(this);
        img_camera.setOnClickListener(this);

        postanonymous_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    edtxtPostAnonymous.setText("1");
                }
                else
                {
                    edtxtPostAnonymous.setText("0");
                }
            }
        });

        if(!hasCamera())
        {
            img_camera.setEnabled(false);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
//        Toast.makeText(WallnitUserUploadPhotos.this, "Permission granted", Toast.LENGTH_SHORT).show();
    }

    public boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View v)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String find_which_image = imagefind.getText().toString();
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && find_which_image.equals("gallery"))
        {
            Uri selectedImage = data.getData();
            imageshow.setImageURI(selectedImage);
        }
        if(requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK && data != null && find_which_image.equals("camera"))
        {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap)extras.get("data");
            imageshow.setImageBitmap(photo);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String image_name = "wallnit_";
        String usersessionid,captionpost,postUserOrCircle,postAnonymous,total_img;
        if(item.getItemId()==R.id.text_post_upload)
        {
            if(cd.isConnected()) {
                if (null != imageshow.getDrawable()) {
                    usersessionid = session.getUserSession();
                    captionpost = caption_uploadpost.getText().toString();
                    postUserOrCircle = postUserCircle.getText().toString();
                    postAnonymous = edtxtPostAnonymous.getText().toString();
                    total_img = total_num_image.getText().toString();

                    Bitmap image = ((BitmapDrawable) imageshow.getDrawable()).getBitmap();
                    new UploadImage(image, image_name, usersessionid, captionpost, postUserOrCircle, postAnonymous, total_img).execute();
                }
                if (null == imageshow.getDrawable()) {
                    Toast.makeText(WallnitUserUploadPhotos.this, "select any image", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(WallnitUserUploadPhotos.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }

        }
        return false;
    }


    private class UploadImage extends AsyncTask<Void, Void, Void> {
        Bitmap image;
        String name,puserid,caption,postuserCircle,postAnony,totalimg;
        ProgressDialog progressDialog;

        public UploadImage(Bitmap image, String name, String puserid, String caption, String postuserCircle, String postAnony,String totalimg){
            this.image = image;
            this.name = name;
            this.puserid = puserid;
            this.caption = caption;
            this.postuserCircle = postuserCircle;
            this.postAnony = postAnony;
            this.totalimg = totalimg;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitUserUploadPhotos.this,"","Post upload...",false,false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image", encodedImage));
            dataToSend.add(new BasicNameValuePair("name", name));
            dataToSend.add(new BasicNameValuePair("caption", caption));
            dataToSend.add(new BasicNameValuePair("usersessionid", puserid));
            dataToSend.add(new BasicNameValuePair("userorcircle", postuserCircle));
            dataToSend.add(new BasicNameValuePair("userpostanonymous", postAnony));
            dataToSend.add(new BasicNameValuePair("totalnum_imagepost", totalimg));


            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "wallnit_android_user_uploadphotospost.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent = new Intent(WallnitUserUploadPhotos.this,Wall.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }


    private HttpParams getHttpRequestParams(){
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams, 1000 * 30);
        return httpRequestParams;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.user_photos_gallery)
        {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            imagefind.setText("gallery");
        }
        if(view.getId()==R.id.user_photos_camera)
        {
            launchCamera(view);
            imagefind.setText("camera");
        }
    }
}
