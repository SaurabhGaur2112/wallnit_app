package com.wallnit.wallnitlogin;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.Toast;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class WallnitUploadCircleImage extends WallnitRuntimePermission implements View.OnClickListener {
    CircleImageView uploadProfileImage;
    private static final int RESULT_LOAD_IMAGE = 1;
    EditText imageSelectOrNot;

    String sessionCircleId;
    Session session;
    private static final int REQUEST_PERMISSION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_upload_circle_image);

        requestAppPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                R.string.runtime_permission_msg,REQUEST_PERMISSION);

        session = new Session(this);

        uploadProfileImage = (CircleImageView) findViewById(R.id.circleprofile_changeupload);
        imageSelectOrNot = (EditText) findViewById(R.id.uploadImageOrNot);
        imageSelectOrNot.setText("");

        uploadProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_profile_image_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.profile_imagechange)
        {
            String getImage = imageSelectOrNot.getText().toString();
            if(getImage.equals(""))
            {
                Toast.makeText(WallnitUploadCircleImage.this, "select any image", Toast.LENGTH_SHORT).show();
            }
            else
            {
                sessionCircleId = session.getCircleSession();
                Bitmap image = ((BitmapDrawable) uploadProfileImage.getDrawable()).getBitmap();
                new UploadCircleProfileImage(image,sessionCircleId).execute();
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            uploadProfileImage.setImageURI(selectedImage);
            imageSelectOrNot.setText("image select");
        }
    }

    @Override
    public void onClick(View view) {

    }

    public class UploadCircleProfileImage extends AsyncTask<Void, Void, Void> {
        Bitmap image;
        String circle_id;
        ProgressDialog progressDialog;

        private static final String SERVER_ADDRESS = "http://www.wallnit.com/wallnit_android/";


        public UploadCircleProfileImage(Bitmap image,String circle_id){
            this.image = image;
            this.circle_id = circle_id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitUploadCircleImage.this,"","Please wait...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            startActivity(new Intent(WallnitUploadCircleImage.this,WallnitCircleProfile.class));
            finish();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("sessionCircleId", circle_id));
            dataToSend.add(new BasicNameValuePair("image", encodedImage));

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "wallnit_android_circle_profile_image.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        private HttpParams getHttpRequestParams(){
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
            HttpConnectionParams.setSoTimeout(httpRequestParams, 1000 * 30);
            return httpRequestParams;
        }

    }
}
