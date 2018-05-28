package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCircleGetEditPost.WallnitCircleGetEditQuotePost;
import com.wallnit.wallnitlogin.WallnitCircleUpdateEditPost.WallnitCircleUpdateEditQuotePost;

public class WallnitCircleEditQuote extends AppCompatActivity {

    WallnitCircleUpdateEditQuotePost wallnitCircleUpdateEditQuotePost;
    WallnitCircleGetEditQuotePost wallnitCircleGetEditQuotePost;

    EditText quotePost_edit;
    String editPost_number;
    String getEditValues[];
    String quotePostValue,quotePostDatetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_edit_quote);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        wallnitCircleUpdateEditQuotePost = new WallnitCircleUpdateEditQuotePost();
        wallnitCircleGetEditQuotePost = new WallnitCircleGetEditQuotePost();

        quotePost_edit = (EditText) findViewById(R.id.circle_edit_quotepost);

        Intent i = getIntent();
        editPost_number = i.getStringExtra("circle_editQuote_number_post");

        getEditValues = wallnitCircleGetEditQuotePost.getvaluesEditPostNumber(editPost_number);

        quotePostValue = getEditValues[0];
        quotePostDatetime = getEditValues[1];

        quotePost_edit.setText(quotePostValue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String txtPost;
        txtPost = quotePost_edit.getText().toString();
        if(item.getItemId()==R.id.text_post_upload)
        {
            if(txtPost.trim().length()==0)
            {
                quotePost_edit.requestFocus();
            }
            else
            {
                wallnitCircleUpdateEditQuotePost.updateEditQuotePost(editPost_number,txtPost,quotePostDatetime);
                startActivity(new Intent(this,WallnitCircleProfile.class));
                finish();
            }
        }
        return false;
    }
}
