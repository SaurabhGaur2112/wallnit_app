package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;

public class WallnitUserAccountInformation extends AppCompatActivity {
    Session session;
    String sessionuserid;
    EditText account_info_username,account_info_tagname;
    WallnitUserAccountInfoGetUsername wallnitUserAccountInfoGetUsername;
    WallnitUserAccountInfoGettagname wallnitUserAccountInfoGettagname;
    WallnitUserAccountInfoUpdate wallnitUserAccountInfoUpdate;
    ConnectionDetector cd;
    String username,tagname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_account_information);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        session = new Session(this);
        sessionuserid = session.getUserSession();
        account_info_username = (EditText) findViewById(R.id.account_info_usernameedit);
        account_info_tagname = (EditText) findViewById(R.id.account_info_tagnameedit);
        wallnitUserAccountInfoGetUsername = new WallnitUserAccountInfoGetUsername();
        wallnitUserAccountInfoGettagname = new WallnitUserAccountInfoGettagname();

        cd = new ConnectionDetector(this);
        wallnitUserAccountInfoUpdate = new WallnitUserAccountInfoUpdate();

        if(cd.isConnected())
        {
            username = wallnitUserAccountInfoGetUsername.wallnitGetUsername(sessionuserid);
            tagname = wallnitUserAccountInfoGettagname.wallnitGetTagname(sessionuserid);
        } else {
            username = "";
            tagname = "";
        }

        if(!username.equals("null"))
        {
            account_info_username.setText(username);
        }

        if(!tagname.equals("null"))
        {
            account_info_tagname.setText(tagname.replace("#",""));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_account_information_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.account_infochange)
        {
            if(cd.isConnected())
            {
                String username,tagname;
                username = account_info_username.getText().toString();
                tagname = account_info_tagname.getText().toString();
                wallnitUserAccountInfoUpdate.userAccountInfoUpdate(username,tagname,sessionuserid);
                startActivity(new Intent(WallnitUserAccountInformation.this,WallnitUserAccountSetting.class));
                finish();
            } else {
                Toast.makeText(WallnitUserAccountInformation.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }

        }
        return false;
    }
}
