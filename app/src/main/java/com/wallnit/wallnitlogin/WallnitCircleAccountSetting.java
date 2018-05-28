package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class WallnitCircleAccountSetting extends AppCompatActivity implements View.OnClickListener{
    TextView circle_info,circle_contact,circle_about,circle_password;
    Session session;
    WallnitCircleGetOldPassword wallnitCircleGetOldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_account_setting);
        circle_info = (TextView) findViewById(R.id.circleaccountinfoid);
        circle_contact = (TextView) findViewById(R.id.circlecontactinfoid);
        circle_about = (TextView) findViewById(R.id.circleaboutinfoid);
        circle_password = (TextView) findViewById(R.id.circle_changepasswordid);
        session = new Session(this);
        wallnitCircleGetOldPassword = new WallnitCircleGetOldPassword();

        circle_info.setText("Add and edit information about your circle.");
        circle_contact.setText("Add your email, contact and website so that it would be easy for your followers to reach you out.");
        circle_about.setText("Add attractive and informative description about your circle.");

        circle_info.setOnClickListener(this);
        circle_contact.setOnClickListener(this);
        circle_about.setOnClickListener(this);
        circle_password.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.circleaccountinfoid:
                startActivity(new Intent(WallnitCircleAccountSetting.this,WallnitCircleAccountInformation.class));
                break;
            case R.id.circlecontactinfoid:
                startActivity(new Intent(WallnitCircleAccountSetting.this,WallnitCircleAccountContact.class));
                break;
            case R.id.circleaboutinfoid:
                startActivity(new Intent(WallnitCircleAccountSetting.this,WallnitCircleAboutUs.class));
                break;
            case R.id.circle_changepasswordid:
                String sessionCircleId = session.getCircleSession();
                String getOldPass = wallnitCircleGetOldPassword.wallnitGetPassword(sessionCircleId);
                Intent i = new Intent(getApplicationContext(),WallnitCircleChangePassword.class);
                i.putExtra("oldPasswordSet",getOldPass);
                startActivity(i);
                break;
        }
    }
}
