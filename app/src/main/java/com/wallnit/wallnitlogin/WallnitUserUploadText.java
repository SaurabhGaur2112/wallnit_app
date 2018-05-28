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

import com.wallnit.wallnitlogin.WallnitUserUploadPosts.WallnitPostUserText;

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

public class WallnitUserUploadText extends AppCompatActivity {

    EditText textUploadPost,edtxtPostAnonymous,postUserCircle;
    CheckBox textPostAnonymous;
    Session session;
    InputStream is = null;
    WallnitPostUserText wallnitPostUserText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_upload_text);
        textUploadPost = (EditText) findViewById(R.id.user_upload_textpost);
        edtxtPostAnonymous = (EditText) findViewById(R.id.eduser_textpost_anonymous);
        textPostAnonymous = (CheckBox) findViewById(R.id.user_upload_textpost_anonymous);
        postUserCircle = (EditText) findViewById(R.id.userpost_userorcircle);
        session = new Session(this);
        wallnitPostUserText = new WallnitPostUserText();

        textPostAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonCheck, boolean check) {
                if(buttonCheck.isChecked())
                {
                    edtxtPostAnonymous.setText("1");
                }
                else
                {
                    edtxtPostAnonymous.setText("0");
                }
            }
        });
    }

    public class UserTextPostInsert extends AsyncTask<Void,Void,Void>{

        String usersessionid,textPost,postUserOrCircle,postAnonymous;
        ProgressDialog progressDialog;

        public UserTextPostInsert(String usersessionid,String textPost,String postUserOrCircle,String postAnonymous){
            this.usersessionid = usersessionid;
            this.textPost = textPost;
            this.postUserOrCircle = postUserOrCircle;
            this.postAnonymous = postAnonymous;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitUserUploadText.this,"","Post Upload...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent = new Intent(WallnitUserUploadText.this,Wall.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            wallnitPostUserText.insertUserTextPost(usersessionid,textPost,postUserOrCircle,postAnonymous);
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String textPost,postAnonymous,postUserOrCircle;
        textPost = textUploadPost.getText().toString();
        postAnonymous = edtxtPostAnonymous.getText().toString();
        postUserOrCircle = postUserCircle.getText().toString();
        String usersessionid = session.getUserSession();

        if(item.getItemId()==R.id.text_post_upload)
        {
            if(textPost.trim().length()==0)
            {
                textUploadPost.requestFocus();
            }
            else
            {
                new UserTextPostInsert(usersessionid,textPost,postUserOrCircle,postAnonymous).execute();
            }
        }
        return false;
    }
}
