package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitCircleSignupEmail.WallnitCircleSignupCircleId;
import com.wallnit.wallnitlogin.WallnitCircleSignupEmail.WallnitCircleSignupValueInsert;
import com.wallnit.wallnitlogin.WallnitUserSignupEmailExists.WallnitUserSignupCheck;

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

public class WallnitSignupCircle extends AppCompatActivity {
    private Button signupcircleButton;
    private TextView termsofService,signupuser,login;
    private EditText signupCirclename,signupEmail,signupPassword;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;
    Session session;
    ConnectionDetector connectionDetector;
    WallnitUserSignupCheck wallnitUserSignupCheck;
    WallnitCircleSignupValueInsert wallnitCircleSignupValueInsert;
    WallnitCircleSignupCircleId wallnitCircleSignupCircleId;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_signup_circle);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        session = new Session(this);
        signupcircleButton = (Button) findViewById(R.id.signup_circle_button);
        termsofService = (TextView) findViewById(R.id.terms_of_service_txtcircle);
        signupuser = (TextView) findViewById(R.id.firstgo_signup_user_text);
        login = (TextView) findViewById(R.id.login_gotxt);
        signupCirclename = (EditText) findViewById(R.id.signupcircle_enter_circlename);
        signupEmail = (EditText) findViewById(R.id.signupcircle_enter_email);
        signupPassword = (EditText) findViewById(R.id.signupcircle_enter_pass);

        wallnitUserSignupCheck = new WallnitUserSignupCheck();
        wallnitCircleSignupValueInsert = new WallnitCircleSignupValueInsert();
        wallnitCircleSignupCircleId = new WallnitCircleSignupCircleId();

        connectionDetector = new ConnectionDetector(this);

        termsofService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitSignupCircle.this,ForgetPassword_StepOne.class));
            }
        });

        signupuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitSignupCircle.this,WallnitSignupUser.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitSignupCircle.this,WallnitLogin.class));
            }
        });

        signupcircleButton.setOnClickListener(new View.OnClickListener() {
            InputStream is = null;
            @Override
            public void onClick(View view) {
                if(connectionDetector.isConnected())
                {
                String circlename = "" + signupCirclename.getText().toString();
                String email = "" + signupEmail.getText().toString();
                String password = "" + signupPassword.getText().toString();

                if (circlename.trim().length() == 0) {
                    signupCirclename.requestFocus();
                } else if (email.trim().length() == 0) {
                    signupEmail.requestFocus();
                } else if (password.trim().length() == 0) {
                    signupPassword.requestFocus();
                } else {
                    new SignupCircleEnter(circlename,email,password).execute();
                }
            }
                else{
                    Toast.makeText(WallnitSignupCircle.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class SignupCircleEnter extends AsyncTask<Void,Void,Void>{

        String circlename,email,password;
        ProgressDialog progressDialog;
        String getCircleEmailExists = "";
        String getCircleSignupId = "";

        public SignupCircleEnter(String circlename,String email,String password){
            this.circlename = circlename;
            this.email = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitSignupCircle.this,"","Please wait...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            if(getCircleEmailExists.equals("not insert"))
            {
                Toast.makeText(WallnitSignupCircle.this, "Email already exists", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WallnitSignupCircle.this,WallnitSignupCircle.class));
                finish();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            getCircleEmailExists = wallnitUserSignupCheck.userCheckEmailId(email);

            if(getCircleEmailExists.equals("insert"))
            {
                wallnitCircleSignupValueInsert.insertCircleSignupValue(circlename,email,password);

                getCircleSignupId = wallnitCircleSignupCircleId.getSignupCircleId(email);

                session.setLoggedin(false);
                session.setCircleLoggedin(true);
                session.setCircleSession(getCircleSignupId);
                session.setUserSession("user_not_login");
                session.setCircleCheckConfirmation("null");
                session.setCircleCreateCircle("");

                Intent intent = new Intent(getApplicationContext(), WallnitCircleAccountConfirmation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

            return null;
        }

    }
}
