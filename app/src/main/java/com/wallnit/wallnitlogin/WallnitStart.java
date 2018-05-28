package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WallnitStart extends AppCompatActivity {
    Button loginButton;
    TextView forgetpassword,signupuser,signupcircle;
    WallnitLoginUserConnectCircle wallnitLoginUserConnectCircle;

    Session session;
    String userIdGetSessions;

    InputStream is = null;
    String line = null;
    String result = null;
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_start);
        loginButton = (Button) findViewById(R.id.start_login_button);
        forgetpassword = (TextView) findViewById(R.id.forget_password_startActivity);
        signupuser = (TextView) findViewById(R.id.firstgo_signup_user_text);
        signupcircle = (TextView) findViewById(R.id.firstgo_signup_circle_text);
        session = new Session(this);


        userIdGetSessions = session.getUserSession();
        cd = new ConnectionDetector(this);

        wallnitLoginUserConnectCircle = new WallnitLoginUserConnectCircle();


            if (session.loggedin()) {
                String checkUserLoginConfirmation = session.getUserCheckConfirmation();

                if (checkUserLoginConfirmation.equals("null")) {
                    startActivity(new Intent(WallnitStart.this, WallnitUserAccountConfirmation.class));
                    finish();
                }
                if (checkUserLoginConfirmation.equals("0")) {
                    startActivity(new Intent(WallnitStart.this, WallnitUserAccountConfirmation.class));
                    finish();
                }
                if (checkUserLoginConfirmation.equals("1")) {

                    String checkUserInterest = session.getUserInterestChoose();
                    if (checkUserInterest.equals("0")) {
                        startActivity(new Intent(WallnitStart.this, WallnitUserChooseInterest.class));
                        finish();
                    }
                    if (checkUserInterest.equals("1")) {
                        if(cd.isConnected())
                        {
                            wallnitLoginUserConnectCircle.circleAutomaticConnectWithUser(userIdGetSessions);
                            startActivity(new Intent(WallnitStart.this, Wall.class));
                            finish();
                        } else {
                            startActivity(new Intent(WallnitStart.this, Wall.class));
                            finish();
                        }

                    }

                }
            }
            if (session.circleloggedin()) {
                String checkCircleLoginConfirmation = session.getCircleCheckConfirmation();

                if (checkCircleLoginConfirmation.equals("null")) {
                    startActivity(new Intent(WallnitStart.this, WallnitCircleAccountConfirmation.class));
                    finish();
                }
                if (checkCircleLoginConfirmation.equals("0")) {
                    startActivity(new Intent(WallnitStart.this, WallnitCircleAccountConfirmation.class));
                    finish();
                }
                if (checkCircleLoginConfirmation.equals("1")) {

                    String checkCircleCreateCircle = session.getCircleCreateCircle();
                    if (checkCircleCreateCircle.equals("")) {
                        startActivity(new Intent(WallnitStart.this, WallnitCircleCreateOptions.class));
                        finish();
                    }
                    if (checkCircleCreateCircle.equals("1")) {
                        startActivity(new Intent(WallnitStart.this, WallnitCircleProfile.class));
                        finish();
                    }

                }
            }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitStart.this,WallnitLogin.class));
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitStart.this,ForgetPassword_StepOne.class));
            }
        });

        signupuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitStart.this,WallnitSignupUser.class));
            }
        });

        signupcircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitStart.this,WallnitSignupCircle.class));
            }
        });
    }
}
