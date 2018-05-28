package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WallnitCircleAccountVerification extends AppCompatActivity {
    private EditText userAccountVerifi,codeCheckConfirm;
    private TextView verifinext;
    Session session;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_circle_account_verification);
        session = new Session(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        userAccountVerifi = (EditText) findViewById(R.id.code_enter_confirm_circle);
        codeCheckConfirm = (EditText) findViewById(R.id.codeenter_checkverifi);
        verifinext = (TextView) findViewById(R.id.circleaccount_verification_stepsecond);

        Intent getbyintent = getIntent();
        String verificationcodetxt = getbyintent.getStringExtra("verificationCodeTxt");
        codeCheckConfirm.setText(verificationcodetxt);

        verifinext.setOnClickListener(new View.OnClickListener() {
            InputStream is = null;
            @Override
            public void onClick(View view) {
                String verifiFirst,verifiSecond;
                verifiFirst = ""+userAccountVerifi.getText().toString();
                verifiSecond = ""+codeCheckConfirm.getText().toString();
                String circlesessionid = session.getCircleSession();

                if(verifiFirst.trim().length()==0)
                {
                    userAccountVerifi.requestFocus();
                }
                else if(verifiFirst.equals(verifiSecond))
                {
                    List<NameValuePair> confirmVerificationSend = new ArrayList<NameValuePair>(1);
                    confirmVerificationSend.add(new BasicNameValuePair("circlesession",circlesessionid));
                    try {
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_confirm_verifi.php");
                        httpPost.setEntity(new UrlEncodedFormEntity(confirmVerificationSend));
                        HttpResponse response = httpClient.execute(httpPost);
                        HttpEntity entity = response.getEntity();
                        is = entity.getContent();
                    }catch (Exception e){

                    }

                    session.setCircleCheckConfirmation("1");
                    startActivity(new Intent(WallnitCircleAccountVerification.this,WallnitCircleCreateOptions.class));
                    finish();
                }
                else
                {
                    Toast.makeText(WallnitCircleAccountVerification.this, "Enter correct code", Toast.LENGTH_SHORT).show();
                    userAccountVerifi.setText(null);
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    userAccountVerifi.requestFocus();
                }

            }
        });
    }
}
