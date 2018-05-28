package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wallnit.wallnitlogin.WallnitCircleGetEditPost.WallnitCircleGetEditBlogPost;
import com.wallnit.wallnitlogin.WallnitCircleUpdateEditPost.WallnitCircleUpdateEditBlogPost;

public class WallnitCircleEditBlog extends AppCompatActivity {

    WallnitCircleGetEditBlogPost wallnitCircleGetEditBlogPost;
    WallnitCircleUpdateEditBlogPost wallnitCircleUpdateEditBlogPost;
    String editPostnumber;
    String getEditValues[];

    String editBlogPostTitle,editBlogPostBody,editBlogPostDateTime;

    EditText blogPostTitle,blogPostBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_edit_blog);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        wallnitCircleGetEditBlogPost = new WallnitCircleGetEditBlogPost();
        wallnitCircleUpdateEditBlogPost = new WallnitCircleUpdateEditBlogPost();

        Intent i = getIntent();
        editPostnumber = i.getStringExtra("circle_editBlog_number_post");

        getEditValues = wallnitCircleGetEditBlogPost.getvaluesEditPostNumber(editPostnumber);

        editBlogPostTitle = getEditValues[0];
        editBlogPostBody = getEditValues[1];
        editBlogPostDateTime = getEditValues[2];

        blogPostTitle = (EditText) findViewById(R.id.blogtitle_post_circle_edit);
        blogPostBody = (EditText) findViewById(R.id.circle_edit_blogpostbody);

        blogPostTitle.setText(editBlogPostTitle);
        blogPostBody.setText(editBlogPostBody);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String txtBlogTitle,txtBlogBody;
        txtBlogTitle = blogPostTitle.getText().toString();
        txtBlogBody = blogPostBody.getText().toString();

        if(item.getItemId()==R.id.text_post_upload)
        {
            if(txtBlogBody.trim().length()==0)
            {
                blogPostBody.requestFocus();
            }
            else
            {
                wallnitCircleUpdateEditBlogPost.updateEditBlogPost(editPostnumber,txtBlogTitle,txtBlogBody,editBlogPostDateTime);
                startActivity(new Intent(this,WallnitCircleProfile.class));
                finish();
            }
        }
        return false;
    }
}
