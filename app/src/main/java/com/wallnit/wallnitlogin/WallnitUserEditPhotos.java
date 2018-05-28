package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wallnit.wallnitlogin.WallnitUserGetEditPost.WallnitUserGetEditPhotosPost;
import com.wallnit.wallnitlogin.WallnitUserUpdateEditPost.WallnitUserUpdateEditPhotosPost;

public class WallnitUserEditPhotos extends AppCompatActivity {

    WallnitUserGetEditPhotosPost wallnitUserGetEditPhotosPost;
    WallnitUserUpdateEditPhotosPost wallnitUserUpdateEditPhotosPost;
    String editPostNumber;
    String getEditValues[];
    String getPhotosCaptionValue,getPhotosDateTime;

    EditText editTextCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_edit_photos);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        wallnitUserUpdateEditPhotosPost = new WallnitUserUpdateEditPhotosPost();

        Intent i = getIntent();
        editPostNumber = i.getStringExtra("editphotos_number_post");

        wallnitUserGetEditPhotosPost = new WallnitUserGetEditPhotosPost();

        getEditValues = wallnitUserGetEditPhotosPost.getvaluesEditPostNumber(editPostNumber);

        getPhotosCaptionValue = getEditValues[0];
        getPhotosDateTime = getEditValues[1];

        editTextCaption = (EditText) findViewById(R.id.user_edit_photospost);

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
            wallnitUserUpdateEditPhotosPost.updateEditPhotosPost(editPostNumber,txtCaption,getPhotosDateTime);
            startActivity(new Intent(this,Wall.class));
            finish();
        }
        return false;
    }
}
