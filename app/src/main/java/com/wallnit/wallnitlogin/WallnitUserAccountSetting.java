package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WallnitUserAccountSetting extends AppCompatActivity implements View.OnClickListener{
    TextView account_info,general_info,work_nd_place_info,contact_info,about_info,change_password;
    Session session;
    WallnitUserGetOldPassword wallnitUserGetOldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_account_setting);
        account_info = (TextView) findViewById(R.id.accountinfoid);
        general_info = (TextView) findViewById(R.id.generalinfoid);
        work_nd_place_info = (TextView) findViewById(R.id.workndplaceinfoid);
        contact_info = (TextView) findViewById(R.id.contactinfoid);
        about_info = (TextView) findViewById(R.id.aboutinfoid);
        change_password = (TextView) findViewById(R.id.changepasswordid);
        wallnitUserGetOldPassword = new WallnitUserGetOldPassword();
        session = new Session(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        account_info.setText("Here you can add and edit your Name and Tagname.");
        general_info.setText("Add general information like, Gender, Date of Birth, Language and other informations.");
        work_nd_place_info.setText("Add where you work and place where you live.");
        contact_info.setText("Add your phone number and email so that it would be easy to reach you out.");
        about_info.setText("Add something attractive and interesting about yourself and share it with others.");

        account_info.setOnClickListener(this);
        general_info.setOnClickListener(this);
        work_nd_place_info.setOnClickListener(this);
        contact_info.setOnClickListener(this);
        about_info.setOnClickListener(this);
        change_password.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.accountinfoid:
                startActivity(new Intent(WallnitUserAccountSetting.this,WallnitUserAccountInformation.class));
                break;
            case R.id.generalinfoid:
                startActivity(new Intent(WallnitUserAccountSetting.this,WallnitUserGeneralInformation.class));
                break;
            case R.id.workndplaceinfoid:
                startActivity(new Intent(WallnitUserAccountSetting.this,WallnitUserAccountWorkAndPlace.class));
                break;
            case R.id.contactinfoid:
                startActivity(new Intent(WallnitUserAccountSetting.this,WallnitUserAccountContact.class));
                break;
            case R.id.aboutinfoid:
                startActivity(new Intent(WallnitUserAccountSetting.this,WallnitUserAboutUs.class));
                break;
            case R.id.changepasswordid:
                String sessionUserId = session.getUserSession();
                String getOldPass = wallnitUserGetOldPassword.wallnitGetPassword(sessionUserId);
                Intent i = new Intent(getApplicationContext(),WallnitUserChangePassword.class);
                i.putExtra("oldPasswordSet",getOldPass);
                startActivity(i);
                break;
        }
    }
}
