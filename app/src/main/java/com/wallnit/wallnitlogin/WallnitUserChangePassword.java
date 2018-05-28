package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class WallnitUserChangePassword extends AppCompatActivity {
    EditText old_password,new_password,set_oldPassword;
    WallnitUserUpdateNewPassword wallnitUserUpdateNewPassword;
    Session session;
    String newEncryptPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_change_password);
        old_password = (EditText) findViewById(R.id.user_insert_oldpassword);
        new_password = (EditText) findViewById(R.id.user_insert_newpassword);
        set_oldPassword = (EditText) findViewById(R.id.old_password_set);
        wallnitUserUpdateNewPassword = new WallnitUserUpdateNewPassword();
        session = new Session(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent i = getIntent();
        String setPass = i.getStringExtra("oldPasswordSet");
        set_oldPassword.setText(setPass);
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
            String oldPassword = old_password.getText().toString();
            String newPassword = new_password.getText().toString();
            newEncryptPassword = MD5(oldPassword);

            if(oldPassword.equals(""))
            {
                old_password.requestFocus();
            }
            else if(newPassword.equals(""))
            {
                new_password.requestFocus();
            }
            else
            {
                String sessionUserId = session.getUserSession();
                String passwordOld = set_oldPassword.getText().toString();
                if(passwordOld.equals(newEncryptPassword))
                {
                    wallnitUserUpdateNewPassword.userUpdateNewPassword(newPassword,sessionUserId);
                    Toast.makeText(WallnitUserChangePassword.this, "Change Password", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WallnitUserChangePassword.this,WallnitUserAccountSetting.class));
                    finish();
                }
                else
                {
                    Toast.makeText(WallnitUserChangePassword.this, "your old password not match", Toast.LENGTH_SHORT).show();
                    old_password.setText("");
                    old_password.requestFocus();
                }
            }
        }
        return false;
    }

    public String MD5(String md5){
        try{
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] arrya = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();

            for(int i=0;i<arrya.length;i++)
            {
                sb.append(Integer.toHexString((arrya[i] & 0xFF) | 0x100).substring(1,3));
            }

            return sb.toString();
        }catch (java.security.NoSuchAlgorithmException e){

        }
        return null;
    }
}
