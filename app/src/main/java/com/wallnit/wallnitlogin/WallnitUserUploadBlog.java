package com.wallnit.wallnitlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitUserUploadPosts.WallnitPostUserBlog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WallnitUserUploadBlog extends AppCompatActivity {
    EditText blogPostTitle,blogPostBody,edtxtUserPostAnonymous,userPostCircleOrUser;
    CheckBox blogPostAnonymous;
    Session session;
    InputStream is = null;
    WallnitPostUserBlog wallnitPostUserBlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_upload_blog);
        session = new Session(this);
        blogPostTitle = (EditText) findViewById(R.id.blog_post);
        blogPostBody = (EditText) findViewById(R.id.user_upload_blogpost);
        edtxtUserPostAnonymous = (EditText) findViewById(R.id.eduser_blogpost_anonymous);
        userPostCircleOrUser = (EditText) findViewById(R.id.userpostblog_userorcircle);
        blogPostAnonymous = (CheckBox) findViewById(R.id.user_upload_blogpost_anonymous);
        wallnitPostUserBlog = new WallnitPostUserBlog();

        blogPostAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonCheck, boolean b) {
                if(buttonCheck.isChecked())
                {
                    edtxtUserPostAnonymous.setText("1");
                }
                else
                {
                    edtxtUserPostAnonymous.setText("0");
                }
            }
        });
    }

    public class UserBlogPostInsert extends AsyncTask<Void,Void,Void>{

        String usersessionid,blogPosttxt,blogPostBodytxt,postUserOrCircletxt,postAnonymoustxt;
        ProgressDialog progressDialog;

        public UserBlogPostInsert(String usersessionid,String blogPosttxt,String blogPostBodytxt,String postUserOrCircletxt,String postAnonymoustxt){
            this.usersessionid = usersessionid;
            this.blogPosttxt = blogPosttxt;
            this.blogPostBodytxt = blogPostBodytxt;
            this.postUserOrCircletxt = postUserOrCircletxt;
            this.postAnonymoustxt = postAnonymoustxt;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitUserUploadBlog.this,"","Post upload...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent = new Intent(WallnitUserUploadBlog.this,Wall.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            wallnitPostUserBlog.insertUserBlogPost(usersessionid,blogPosttxt,blogPostBodytxt,postUserOrCircletxt,postAnonymoustxt);
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.wallnit_user_blog_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String blogPosttxt,blogPostBodytxt,postAnonymoustxt,postUserOrCircletxt,usersessionid;
        blogPosttxt = blogPostTitle.getText().toString();
        blogPostBodytxt = blogPostBody.getText().toString();
        postAnonymoustxt = edtxtUserPostAnonymous.getText().toString();
        postUserOrCircletxt = userPostCircleOrUser.getText().toString();
        usersessionid = session.getUserSession();


        if(item.getItemId()==R.id.blog_post_upload)
        {
            if(blogPostBodytxt.trim().length()==0)
            {
                blogPostBody.requestFocus();
            }
            else
            {
                new UserBlogPostInsert(usersessionid,blogPosttxt,blogPostBodytxt,postUserOrCircletxt,postAnonymoustxt).execute();
            }
        }
        return false;
    }
}
