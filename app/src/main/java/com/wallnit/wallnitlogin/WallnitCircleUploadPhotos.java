package com.wallnit.wallnitlogin;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
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

public class WallnitCircleUploadPhotos extends WallnitRuntimePermission implements View.OnClickListener{


    ImageView img_gallery,img_camera,imageshow;
    public static final int REQUEST_CAPTURE = 1;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String SERVER_ADDRESS = "http://www.wallnit.com/wallnit_android/";
    EditText imagefind,caption_uploadpost;
    Session session;
    private static final int REQUEST_PERMISSION = 10;
    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_upload_photos);

        requestAppPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                R.string.runtime_permission_msg,REQUEST_PERMISSION);

        cd = new ConnectionDetector(this);

        img_gallery = (ImageView) findViewById(R.id.circle_photos_gallery);
        img_camera = (ImageView) findViewById(R.id.circle_photos_camera);
        imageshow = (ImageView) findViewById(R.id.circle_uploadphotos);
        imagefind = (EditText) findViewById(R.id.image_which_one_circle);
        caption_uploadpost = (EditText) findViewById(R.id.circle_upload_captionpost);
        session = new Session(this);

        img_gallery.setOnClickListener(this);
        img_camera.setOnClickListener(this);

        if(!hasCamera())
        {
            img_camera.setEnabled(false);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

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
        String circlesessionid,captionpost;
        if(item.getItemId()==R.id.text_post_upload)
        {
            if(cd.isConnected()) {
                if (null != imageshow.getDrawable()) {
                    circlesessionid = session.getCircleSession();
                    captionpost = caption_uploadpost.getText().toString();

                    Bitmap image = ((BitmapDrawable) imageshow.getDrawable()).getBitmap();
                    new UploadImage(image, image_name, circlesessionid, captionpost).execute();
                }
                if (null == imageshow.getDrawable()) {
                    Toast.makeText(WallnitCircleUploadPhotos.this, "select any image", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(WallnitCircleUploadPhotos.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.circle_photos_gallery)
        {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            imagefind.setText("gallery");
        }
        if(view.getId()==R.id.circle_photos_camera)
        {
            launchCamera(view);
            imagefind.setText("camera");
        }
    }

    private class UploadImage extends AsyncTask<Void, Void, Void> {
        Bitmap image;
        String name,puserid,caption;
        ProgressDialog progressDialog;

        public UploadImage(Bitmap image, String name, String puserid, String caption){
            this.image = image;
            this.name = name;
            this.puserid = puserid;
            this.caption = caption;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitCircleUploadPhotos.this,"","Post upload...",false,false);
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
            dataToSend.add(new BasicNameValuePair("circlesessionid", puserid));


            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "wallnit_android_circle_uploadown_photos.php");

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
            startActivity(new Intent(WallnitCircleUploadPhotos.this,WallnitCircleProfile.class));
            finish();
        }
    }

    private HttpParams getHttpRequestParams(){
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams, 1000 * 30);
        return httpRequestParams;
    }

}
