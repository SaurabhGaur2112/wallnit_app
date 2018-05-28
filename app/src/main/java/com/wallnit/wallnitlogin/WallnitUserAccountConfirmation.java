package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitNumberVerifi.WallnitUserNumberVerifi;

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

public class WallnitUserAccountConfirmation extends AppCompatActivity {
    private EditText countrycode,phonenumber;
    private TextView confirmationnext;
    Session session;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;
    String verificode;
    WallnitUserNumberVerifi wallnitUserNumberVerifi;
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_user_account_confirmation);
        session = new Session(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        wallnitUserNumberVerifi = new WallnitUserNumberVerifi();
        countrycode = (EditText) findViewById(R.id.country_code);
        phonenumber = (EditText) findViewById(R.id.enter_mobile_number);
        confirmationnext = (TextView) findViewById(R.id.useraccount_confirmation_stepfirst);
        confirmationnext.setOnClickListener(new View.OnClickListener() {
            InputStream is = null;
            @Override
            public void onClick(View view) {
                String usersessionid = session.getUserSession();
                String countrycodeid = ""+countrycode.getText().toString();
                String phonenumberid = ""+phonenumber.getText().toString();

                if(phonenumberid.trim().length()==0)
                {
                    phonenumber.requestFocus();
                }
                else
                {
                    new GetNumberVerifi(usersessionid,countrycodeid,phonenumberid).execute();
                }

            }
        });

    }

    public class GetNumberVerifi extends AsyncTask<Void,Void,Void>{

        String useridnum,countryCode,phoneNumber;
        ProgressDialog progressDialog;
        String getVerifiCode;

        public GetNumberVerifi(String useridnum,String countryCode,String phoneNumber){
            this.useridnum = useridnum;
            this.countryCode = countryCode;
            this.phoneNumber = phoneNumber;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitUserAccountConfirmation.this,"","Please wait...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent = new Intent(getApplicationContext(), WallnitUserAccountVerification.class);
            intent.putExtra("verificationCodeTxt",getVerifiCode);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            getVerifiCode = wallnitUserNumberVerifi.userNumVerifi(useridnum,countryCode,phoneNumber);
            return null;
        }
    }
}
