package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class WallnitUserAccountContact extends AppCompatActivity {
    EditText contact_edit;
    Session session;
    String sessionUserId,getContact;
    WallnitUserGetContactInfo wallnitUserGetContactInfo;
    WallnitUserAccountContactUpdate wallnitUserAccountContactUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_account_contact);
        contact_edit = (EditText) findViewById(R.id.user_contact);
        session = new Session(this);
        sessionUserId = session.getUserSession();
        wallnitUserGetContactInfo = new WallnitUserGetContactInfo();
        getContact = wallnitUserGetContactInfo.getContactInfo(sessionUserId);
        contact_edit.setText(getContact);
        wallnitUserAccountContactUpdate = new WallnitUserAccountContactUpdate();
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
            String contacttxt;
            contacttxt = contact_edit.getText().toString();
            wallnitUserAccountContactUpdate.userAccountContactUpdate(sessionUserId,contacttxt);
            startActivity(new Intent(WallnitUserAccountContact.this,WallnitUserAccountSetting.class));
            finish();
        }
        return false;
    }
}
