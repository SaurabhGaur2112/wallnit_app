package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitUserForgetPassword.WallnitUserForgetChangePassword;

public class ForgetPassword_StepThird extends AppCompatActivity {

    TextView forget_third_title,forget_third_desc,forget_login,forget_terms_of_service;
    EditText forget_newpassword;
    Button change_pass;
    WallnitUserForgetChangePassword wallnitUserForgetChangePassword;
    ConnectionDetector connectionDetector;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forget_password__step_third);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        forget_third_title=(TextView) findViewById(R.id.forget_passwordthird_title);
        forget_third_desc=(TextView) findViewById(R.id.forget_password_third_desc);
        forget_login = (TextView) findViewById(R.id.forget_login_first);
        forget_terms_of_service = (TextView) findViewById(R.id.forget_terms_ofservice_first);

        wallnitUserForgetChangePassword = new WallnitUserForgetChangePassword();
        connectionDetector = new ConnectionDetector(this);
        session = new Session(this);

        forget_third_title.setText("Reset your password");
        forget_third_desc.setText("The verification code that you entered has been matched, now enter a new and strong password.");

        forget_newpassword = (EditText) findViewById(R.id.forget_password_enternew);
        change_pass = (Button) findViewById(R.id.change_passwordbtn_forget);

        forget_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword_StepThird.this,WallnitLogin.class));
                finish();
            }
        });

        forget_terms_of_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword_StepThird.this,WallnitTerms_of_Service.class));
                finish();
            }
        });

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPasswordtxt = forget_newpassword.getText().toString();
                if(connectionDetector.isConnected()) {
                    if (newPasswordtxt.trim().length() == 0) {
                        forget_newpassword.requestFocus();
                    } else {
                        String getForgetSessionUserid = session.getForget_UserSession();
                        wallnitUserForgetChangePassword.forget_changepassword(getForgetSessionUserid, newPasswordtxt);
                        session.setForget_UserSession(null);
                        Intent intent = new Intent(getApplicationContext(), WallnitLogin.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}
