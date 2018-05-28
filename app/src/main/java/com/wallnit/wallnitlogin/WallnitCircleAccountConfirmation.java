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

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitNumberVerifi.WallnitCircleNumberVerifi;

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


public class WallnitCircleAccountConfirmation extends AppCompatActivity {
    private EditText countrycode,phonenumber;
    private TextView confirmationnext;
    Session session;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;
    String verificode;
    InputStream is = null;
    ConnectionDetector cd;
    WallnitCircleNumberVerifi wallnitCircleNumberVerifi;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_circle_account_confirmation);

        session = new Session(this);
        cd = new ConnectionDetector(this);

        wallnitCircleNumberVerifi = new WallnitCircleNumberVerifi();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        countrycode = (EditText) findViewById(R.id.country_code_circle);
        phonenumber = (EditText) findViewById(R.id.enter_mobile_number_circle);
        confirmationnext = (TextView) findViewById(R.id.circleaccount_confirmation_stepfirst);


        confirmationnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cd.isConnected()) {
                    String circlesessionid = session.getCircleSession();
                    String countrycodeid = "" + countrycode.getText().toString();
                    String phonenumberid = "" + phonenumber.getText().toString();

                    if (phonenumberid.trim().length() == 0) {
                        phonenumber.requestFocus();
                    } else {
                        new GetCircleNumberVerifi(circlesessionid,countrycodeid,phonenumberid).execute();
                    }
                } else {
                    Toast.makeText(WallnitCircleAccountConfirmation.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class GetCircleNumberVerifi extends AsyncTask<Void,Void,Void>{

        String circleidget,countrycodeget,phonenumberget;
        ProgressDialog progressDialog;
        String verificodeGet;

        public GetCircleNumberVerifi(String circleidget,String countrycodeget,String phonenumberget)
        {
            this.circleidget = circleidget;
            this.countrycodeget = countrycodeget;
            this.phonenumberget = phonenumberget;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitCircleAccountConfirmation.this,"","Please wait...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent = new Intent(getApplicationContext(), WallnitCircleAccountVerification.class);
            intent.putExtra("verificationCodeTxt", verificodeGet);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            verificodeGet = wallnitCircleNumberVerifi.userNumVerifi(circleidget,countrycodeget,phonenumberget);
            return null;
        }
    }
}
