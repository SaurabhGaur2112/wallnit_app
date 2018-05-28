package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitUserGetEditPost.WallnitUserGetEditLinkPost;
import com.wallnit.wallnitlogin.WallnitUserUpdateEditPost.WallnitUserUpdateEditLinkPost;

public class WallnitUserEditLink extends AppCompatActivity {

    WallnitUserGetEditLinkPost wallnitUserGetEditLinkPost;
    WallnitUserUpdateEditLinkPost wallnitUserUpdateEditLinkPost;
    String editPostNumber;
    String getEditValues[];
    String linkPostValue,linkDescValue,linkPostDateTime;

    EditText linkPostWrite,linkDescWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_edit_link);

        wallnitUserUpdateEditLinkPost = new WallnitUserUpdateEditLinkPost();

        Intent i = getIntent();
        editPostNumber = i.getStringExtra("editlink_number_post");


        wallnitUserGetEditLinkPost = new WallnitUserGetEditLinkPost();
        getEditValues = wallnitUserGetEditLinkPost.getvaluesEditPostNumber(editPostNumber);

        linkPostValue = getEditValues[0];
        linkDescValue = getEditValues[1];
        linkPostDateTime = getEditValues[2];

        linkPostWrite = (EditText) findViewById(R.id.link_post_edit);
        linkDescWrite = (EditText) findViewById(R.id.user_edit_linkpost);

        linkPostWrite.setText(linkPostValue);
        linkDescWrite.setText(linkDescValue);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String txtLink,txtLinkDesc;
        txtLink = linkPostWrite.getText().toString();
        txtLinkDesc = linkDescWrite.getText().toString();

        if(item.getItemId()==R.id.text_post_upload)
        {
            if(txtLink.trim().length()==0)
            {
                linkPostWrite.requestFocus();
            }
            else
            {
                wallnitUserUpdateEditLinkPost.updateEditLinkPost(editPostNumber,txtLink,txtLinkDesc,linkPostDateTime);
                startActivity(new Intent(this,Wall.class));
                finish();
            }
        }
        return false;
    }
}
