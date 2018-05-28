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

import com.wallnit.wallnitlogin.WallnitUserUploadPosts.WallnitPostUserLink;

import java.io.InputStream;

public class WallnitUserUploadLink extends AppCompatActivity {

    EditText linkUploadPost,linkUploadPostDescription,edlinkPostAnonymous,linkPostUserCircle;
    CheckBox linkPostAnonymous;
    Session session;
    InputStream is = null;
    WallnitPostUserLink wallnitPostUserLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_upload_link);
        session = new Session(this);
        linkUploadPost = (EditText) findViewById(R.id.link_post);
        linkUploadPostDescription = (EditText) findViewById(R.id.user_upload_linkpost);
        edlinkPostAnonymous = (EditText) findViewById(R.id.eduser_linkpost_anonymous);
        linkPostUserCircle = (EditText) findViewById(R.id.userpostlink_userorcircle);
        linkPostAnonymous = (CheckBox) findViewById(R.id.user_upload_linkpost_anonymous);

        wallnitPostUserLink = new WallnitPostUserLink();

        linkPostAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonCheck, boolean b) {
                if(buttonCheck.isChecked())
                {
                    edlinkPostAnonymous.setText("1");
                }
                else
                {
                    edlinkPostAnonymous.setText("0");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.wallnit_user_link_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String linkPost,linkPostDesc,postAnonymous,postUserOrCircle,usersessionid;
        linkPost = linkUploadPost.getText().toString();
        linkPostDesc = linkUploadPostDescription.getText().toString();
        postAnonymous = edlinkPostAnonymous.getText().toString();
        postUserOrCircle = linkPostUserCircle.getText().toString();
        usersessionid = session.getUserSession();

        if(item.getItemId()==R.id.link_post_upload)
        {
            if(linkPost.trim().length()==0)
            {
                linkUploadPost.requestFocus();
            }
            else
            {
                new UserLinkPostInsert(usersessionid,linkPost,linkPostDesc,postUserOrCircle,postAnonymous).execute();
            }
        }

        return false;
    }

    public class UserLinkPostInsert extends AsyncTask<Void,Void,Void>{

        String usersessionid,linkPost,linkPostDesc,postUserOrCircle,postAnonymous;
        ProgressDialog progressDialog;

        public UserLinkPostInsert(String usersessionid,String linkPost,String linkPostDesc,String postUserOrCircle,String postAnonymous){
            this.usersessionid = usersessionid;
            this.linkPost = linkPost;
            this.linkPostDesc = linkPostDesc;
            this.postUserOrCircle = postUserOrCircle;
            this.postAnonymous = postAnonymous;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitUserUploadLink.this,"","Post Upload...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent = new Intent(WallnitUserUploadLink.this,Wall.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            wallnitPostUserLink.insertUserLinkPost(usersessionid,linkPost,linkPostDesc,postUserOrCircle,postAnonymous);
            return null;
        }
    }
}
