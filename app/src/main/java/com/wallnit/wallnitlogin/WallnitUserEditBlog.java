package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wallnit.wallnitlogin.WallnitUserGetEditPost.WallnitUserGetEditBlogPost;
import com.wallnit.wallnitlogin.WallnitUserUpdateEditPost.WallnitUserUpdateEditBlogPost;

public class WallnitUserEditBlog extends AppCompatActivity {

    WallnitUserGetEditBlogPost wallnitUserGetEditBlogPost;
    WallnitUserUpdateEditBlogPost wallnitUserUpdateEditBlogPost;
    String editPostNumber;
    String getEditValues[];

    String editBlogPostTitle,editBlogPostBody,editBlogPostDateTime;

    EditText blogPostTitle,blogPostBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_edit_blog);

        wallnitUserUpdateEditBlogPost = new WallnitUserUpdateEditBlogPost();

        Intent i = getIntent();
        editPostNumber = i.getStringExtra("editblog_number_post");
        wallnitUserGetEditBlogPost = new WallnitUserGetEditBlogPost();

        getEditValues = wallnitUserGetEditBlogPost.getvaluesEditPostNumber(editPostNumber);

        editBlogPostTitle = getEditValues[0];
        editBlogPostBody = getEditValues[1];
        editBlogPostDateTime = getEditValues[2];

        blogPostTitle = (EditText) findViewById(R.id.blog_post_edit);
        blogPostBody = (EditText) findViewById(R.id.user_edit_blogpost);

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
                wallnitUserUpdateEditBlogPost.updateEditBlogPost(editPostNumber,txtBlogTitle,txtBlogBody,editBlogPostDateTime);
                startActivity(new Intent(this,Wall.class));
                finish();
            }
        }
        return false;
    }
}
