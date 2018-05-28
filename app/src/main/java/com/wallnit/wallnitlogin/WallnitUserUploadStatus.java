package com.wallnit.wallnitlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitUserUploadPosts.WallnitPostUserStatus;

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

public class WallnitUserUploadStatus extends AppCompatActivity {
    EditText userWriteStatus;
    Session session;
    InputStream is = null;
    WallnitPostUserStatus wallnitPostUserStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_upload_status);
        userWriteStatus = (EditText) findViewById(R.id.user_upload_statuspost);
        session = new Session(this);
        wallnitPostUserStatus = new WallnitPostUserStatus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String uploadStatusText,usersessionid;
        uploadStatusText = userWriteStatus.getText().toString();
        usersessionid = session.getUserSession();

        if(item.getItemId()==R.id.text_post_upload)
        {
            if(uploadStatusText.trim().length()==0)
            {
                userWriteStatus.requestFocus();
            }
            else
            {
                new UserStatusInsert(usersessionid,uploadStatusText).execute();
            }
        }
        return false;
    }

    public class UserStatusInsert extends AsyncTask<Void,Void,Void>{

        String usersessionid,uploadStatusText;
        ProgressDialog progressDialog;

        public UserStatusInsert(String usersessionid,String uploadStatusText){
            this.usersessionid = usersessionid;
            this.uploadStatusText = uploadStatusText;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitUserUploadStatus.this,"","Status upload...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent = new Intent(WallnitUserUploadStatus.this,Wall.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            wallnitPostUserStatus.insertUserBlogPost(usersessionid,uploadStatusText);

            return null;
        }
    }
}
