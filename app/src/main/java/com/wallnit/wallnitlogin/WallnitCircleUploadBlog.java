package com.wallnit.wallnitlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCircleUploadPost.WallnitPostCircleBlog;

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

public class WallnitCircleUploadBlog extends AppCompatActivity {


    EditText blog_title,blog_body;
    Session session;
    String circleid;
    InputStream is = null;
    WallnitPostCircleBlog wallnitPostCircleBlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_upload_blog);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        session = new Session(this);
        circleid = session.getCircleSession();

        blog_title = (EditText) findViewById(R.id.blogtitle_post_circle);
        blog_body = (EditText) findViewById(R.id.circle_upload_blogpostbody);

        wallnitPostCircleBlog = new WallnitPostCircleBlog();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.text_post_upload)
        {
            String blogtitle_txt,blogbody_txt;
            blogtitle_txt = blog_title.getText().toString();
            blogbody_txt = blog_body.getText().toString();

            if(blogbody_txt.trim().length()==0)
            {
                blog_body.requestFocus();
            }
            else
            {
                new CircleBlogPostInsert(circleid,blogtitle_txt,blogbody_txt);
            }
        }
        return false;
    }

    public class CircleBlogPostInsert extends AsyncTask<Void,Void,Void>{

        String circlesessionid,blogtitle_txt,blogbody_txt;
        ProgressDialog progressDialog;

        public CircleBlogPostInsert(String circlesessionid,String blogtitle_txt,String blogbody_txt){
            this.circlesessionid = circlesessionid;
            this.blogtitle_txt = blogtitle_txt;
            this.blogbody_txt = blogbody_txt;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitCircleUploadBlog.this,"","Post upload...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            startActivity(new Intent(WallnitCircleUploadBlog.this,WallnitCircleProfile.class));
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            wallnitPostCircleBlog.insertCircleBlogPost(circlesessionid,blogtitle_txt,blogbody_txt);
            return null;
        }
    }
}
