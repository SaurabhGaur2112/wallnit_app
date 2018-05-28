package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitUserSignupEmailExists.WallnitUserSignupCheck;
import com.wallnit.wallnitlogin.WallnitUserSignupEmailExists.WallnitUserSignupUserId;
import com.wallnit.wallnitlogin.WallnitUserSignupEmailExists.WallnitUserSignupValueInsert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WallnitSignupUser extends AppCompatActivity {
    private Button signupUserButton;
    private TextView termsofservice,login,signupcircle;
    private EditText signupUsername,signupEmail,signupPassword;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;
    Session session;
    ConnectionDetector connectionDetector;
    WallnitUserSignupCheck wallnitUserSignupCheck;
    WallnitUserSignupValueInsert wallnitUserSignupValueInsert;
    WallnitUserSignupUserId wallnitUserSignupUserId;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        session = new Session(this);
        connectionDetector = new ConnectionDetector(this);

        wallnitUserSignupCheck = new WallnitUserSignupCheck();
        wallnitUserSignupValueInsert = new WallnitUserSignupValueInsert();
        wallnitUserSignupUserId = new WallnitUserSignupUserId();

        setContentView(R.layout.activity_wallnit_signup_user);
        signupUserButton = (Button) findViewById(R.id.signup_user_button);
        termsofservice = (TextView) findViewById(R.id.terms_of_service_txtuser);
        login = (TextView) findViewById(R.id.login_gothis_txt);
        signupcircle = (TextView) findViewById(R.id.firstgo_signup_circle_text);
        signupUsername = (EditText) findViewById(R.id.signup_enter_username);
        signupEmail = (EditText) findViewById(R.id.signup_enter_email);
        signupPassword = (EditText) findViewById(R.id.signup_enter_pass);

        termsofservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitSignupUser.this,WallnitTerms_of_Service.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitSignupUser.this,WallnitLogin.class));
            }
        });

        signupcircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitSignupUser.this,WallnitSignupCircle.class));
            }
        });

        signupUserButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(connectionDetector.isConnected())
                {
                String username = "" + signupUsername.getText().toString();
                String email = "" + signupEmail.getText().toString();
                String password = "" + signupPassword.getText().toString();

                if (username.trim().length() == 0) {
                    signupUsername.requestFocus();
                } else if (email.trim().length() == 0) {
                    signupEmail.requestFocus();
                } else if (password.trim().length() == 0) {
                    signupPassword.requestFocus();
                } else {
                    new SignupUserEnter(username,email,password).execute();
                }
            }
                else{
                    Toast.makeText(WallnitSignupUser.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class SignupUserEnter extends AsyncTask<Void,Void,Void>{

        String username,email,password;
        ProgressDialog progressDialog;
        String getUserEmailExists = "";
        String getUserSignupId = "";

        public SignupUserEnter(String username,String email,String password){
            this.username = username;
            this.email = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitSignupUser.this,"","Please wait...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            if(getUserEmailExists.equals("not insert"))
            {
                Toast.makeText(WallnitSignupUser.this, "Email already exists", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WallnitSignupUser.this,WallnitSignupUser.class));
                finish();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getUserEmailExists = wallnitUserSignupCheck.userCheckEmailId(email);

            if(getUserEmailExists.equals("insert"))
            {
                wallnitUserSignupValueInsert.insertUserSignupValue(username,email,password);

                getUserSignupId = wallnitUserSignupUserId.getSignupUserId(email);

                session.setLoggedin(true);
                session.setCircleLoggedin(false);
                session.setUserSession(getUserSignupId);
                session.setCircleSession("circle_not_login");
                session.setUserCheckConfirmation("null");
                session.setUserInterestChoose("0");

                Intent intent = new Intent(getApplicationContext(), WallnitUserAccountConfirmation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
            return null;
        }
    }
}
