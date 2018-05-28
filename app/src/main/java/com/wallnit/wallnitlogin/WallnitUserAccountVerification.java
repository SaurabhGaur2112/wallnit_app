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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WallnitUserAccountVerification extends AppCompatActivity {
    private EditText useraccountVerifi,codecheckconfirm;
    private TextView verifinext;
    Session session;
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_user_account_verification);
        session = new Session(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        useraccountVerifi = (EditText) findViewById(R.id.code_enter_confirm);
        codecheckconfirm = (EditText) findViewById(R.id.code_enter_check);
        verifinext = (TextView) findViewById(R.id.useraccount_verification_stepsecond);

        Intent getbyintent = getIntent();
        String verificationcodetxt = getbyintent.getStringExtra("verificationCodeTxt");
        codecheckconfirm.setText(verificationcodetxt);

        verifinext.setOnClickListener(new View.OnClickListener() {
            InputStream is = null;
            @Override
            public void onClick(View view) {
                String verifiFirst,verifiSecond;
                verifiFirst = ""+useraccountVerifi.getText().toString();
                verifiSecond = ""+codecheckconfirm.getText().toString();
                String usersessionid = session.getUserSession();

                if(verifiFirst.trim().length()==0)
                {
                    useraccountVerifi.requestFocus();
                }
                else if(verifiFirst.equals(verifiSecond))
                {
                    List<NameValuePair> confirmVerificationSend = new ArrayList<NameValuePair>(1);
                    confirmVerificationSend.add(new BasicNameValuePair("usersession",usersessionid));
                    try {
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_confirm_verifi.php");
                        httpPost.setEntity(new UrlEncodedFormEntity(confirmVerificationSend));
                        HttpResponse response = httpClient.execute(httpPost);
                        HttpEntity entity = response.getEntity();
                        is = entity.getContent();
                    }catch (Exception e){

                    }
                    session.setUserCheckConfirmation("1");
                    Intent intent = new Intent(getApplicationContext(), WallnitUserChooseInterest.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(WallnitUserAccountVerification.this, "Enter correct code", Toast.LENGTH_SHORT).show();
                    useraccountVerifi.setText(null);
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    useraccountVerifi.requestFocus();
                }
            }
        });

    }
}
