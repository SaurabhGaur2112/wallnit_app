package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class WallnitCircleAboutUs extends AppCompatActivity {
    EditText circle_aboutUs;
    Session session;
    String sessionCircleId;
    WallnitCircleAccountAboutUsGet wallnitCircleAccountAboutUsGet;
    String circleGetAboutUs;
    WallnitCircleAboutUsUpdate wallnitCircleAboutUsUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_about_us);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        circle_aboutUs = (EditText) findViewById(R.id.circle_aboutusdesc);
        session = new Session(this);
        sessionCircleId = session.getCircleSession();
        wallnitCircleAccountAboutUsGet = new WallnitCircleAccountAboutUsGet();
        circleGetAboutUs = wallnitCircleAccountAboutUsGet.circleAboutUsGet(sessionCircleId);
        circle_aboutUs.setText(circleGetAboutUs);
        wallnitCircleAboutUsUpdate = new WallnitCircleAboutUsUpdate();

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
            String circle_aboutus_desc;
            circle_aboutus_desc = circle_aboutUs.getText().toString();
            wallnitCircleAboutUsUpdate.circleAboutUsUpdate(sessionCircleId,circle_aboutus_desc);
            startActivity(new Intent(WallnitCircleAboutUs.this,WallnitCircleAccountSetting.class));
            finish();
        }
        return false;
    }
}
