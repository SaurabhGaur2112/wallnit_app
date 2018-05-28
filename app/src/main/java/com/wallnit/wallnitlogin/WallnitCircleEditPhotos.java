package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wallnit.wallnitlogin.WallnitCircleGetEditPost.WallnitCircleGetEditPhotosPost;
import com.wallnit.wallnitlogin.WallnitCircleUpdateEditPost.WallnitCircleUpdateEditPhotosPost;

public class WallnitCircleEditPhotos extends AppCompatActivity {

    WallnitCircleGetEditPhotosPost wallnitCircleGetEditPhotosPost;
    WallnitCircleUpdateEditPhotosPost wallnitCircleUpdateEditPhotosPost;

    String editPostNumber;
    String getEditValues[];
    String getPhotosCaptionValue,getPhotosDateTime;

    EditText editTextCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_edit_photos);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        wallnitCircleGetEditPhotosPost = new WallnitCircleGetEditPhotosPost();
        wallnitCircleUpdateEditPhotosPost = new WallnitCircleUpdateEditPhotosPost();

        Intent i = getIntent();
        editPostNumber = i.getStringExtra("circle_editPhotos_number_post");

        getEditValues = wallnitCircleGetEditPhotosPost.getvaluesEditPostNumber(editPostNumber);

        getPhotosCaptionValue = getEditValues[0];
        getPhotosDateTime = getEditValues[1];

        editTextCaption = (EditText) findViewById(R.id.circle_edit_captionpost);

        editTextCaption.setText(getPhotosCaptionValue);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String txtCaption;
        txtCaption = editTextCaption.getText().toString();
        if(item.getItemId()==R.id.text_post_upload)
        {
            wallnitCircleUpdateEditPhotosPost.updateEditPhotosPost(editPostNumber,txtCaption,getPhotosDateTime);
            startActivity(new Intent(this,WallnitCircleProfile.class));
            finish();
        }
        return false;
    }
}
