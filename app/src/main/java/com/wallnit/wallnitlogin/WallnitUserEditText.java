package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wallnit.wallnitlogin.WallnitUserGetEditPost.WallnitUserGetEditTextPost;
import com.wallnit.wallnitlogin.WallnitUserUpdateEditPost.WallnitUserUpdateEditTextPost;

public class WallnitUserEditText extends AppCompatActivity {

    WallnitUserGetEditTextPost wallnitUserGetEditTextPost;
    WallnitUserUpdateEditTextPost wallnitUserUpdateEditTextPost;
    String editPostNumber;
    String getEditValues[];
    String textPostValue,textPostDateTime;

    EditText textPostEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_edit_text);

        wallnitUserUpdateEditTextPost = new WallnitUserUpdateEditTextPost();

        textPostEdit = (EditText) findViewById(R.id.user_edit_textpost);

        Intent i = getIntent();
        editPostNumber = i.getStringExtra("edittext_number_post");

        wallnitUserGetEditTextPost = new WallnitUserGetEditTextPost();

        getEditValues = wallnitUserGetEditTextPost.getvaluesEditPostNumber(editPostNumber);

        textPostValue = getEditValues[0];
        textPostDateTime = getEditValues[1];

        textPostEdit.setText(textPostValue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String txtPost;
        txtPost = textPostEdit.getText().toString();
        if(item.getItemId()==R.id.text_post_upload)
        {
            if(txtPost.trim().length()==0)
            {
                textPostEdit.requestFocus();
            }
            else
            {
                wallnitUserUpdateEditTextPost.updateEditTextPost(editPostNumber,txtPost,textPostDateTime);
                startActivity(new Intent(this,Wall.class));
                finish();
            }
        }


        return false;
    }
}
