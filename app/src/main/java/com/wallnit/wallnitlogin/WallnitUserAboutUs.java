package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class WallnitUserAboutUs extends AppCompatActivity {
    EditText aboutus_edit;
    Session session;
    WallnitUserAboutDesc wallnitUserAboutDesc;
    String aboutUsGet,sessionuserid;
    WallnitUserAboutUsUpdate wallnitUserAboutUsUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_about_us);
        aboutus_edit = (EditText) findViewById(R.id.user_aboutdesc);
        session = new Session(this);
        wallnitUserAboutDesc = new WallnitUserAboutDesc();
        sessionuserid = session.getUserSession();
        aboutUsGet = wallnitUserAboutDesc.aboutUsDesc(sessionuserid);
        aboutus_edit.setText(aboutUsGet);
        wallnitUserAboutUsUpdate = new WallnitUserAboutUsUpdate();

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
            String aboutus_desc;
            aboutus_desc = aboutus_edit.getText().toString();
            wallnitUserAboutUsUpdate.userAboutUs(sessionuserid,aboutus_desc);
            startActivity(new Intent(WallnitUserAboutUs.this,WallnitUserAccountSetting.class));
            finish();
        }
        return false;
    }
}
