package com.wallnit.wallnitlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCircleUploadPost.WallnitPostCircleQuote;

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

public class WallnitCircleUploadQuote extends AppCompatActivity {

    Session session;
    EditText writeQuote_Circle;
    InputStream is = null;
    String circlesesionid;
    WallnitPostCircleQuote wallnitPostCircleQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_upload_quote);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        session = new Session(this);
        writeQuote_Circle = (EditText) findViewById(R.id.circle_upload_quotepost);
        wallnitPostCircleQuote = new WallnitPostCircleQuote();

        circlesesionid = session.getCircleSession();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.text_post_upload)
        {
            String quoteWrite = writeQuote_Circle.getText().toString();
            if(quoteWrite.trim().length()==0)
            {
                writeQuote_Circle.requestFocus();
            }
            else
            {
                new CircleQuotePostInsert(circlesesionid,quoteWrite).execute();
            }
        }
        return false;
    }

    public class CircleQuotePostInsert extends AsyncTask<Void,Void,Void>{

        String circlesesionid,quoteWrite;
        ProgressDialog progressDialog;

        public CircleQuotePostInsert(String circlesesionid,String quoteWrite){
            this.circlesesionid = circlesesionid;
            this.quoteWrite = quoteWrite;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitCircleUploadQuote.this,"","Post upload...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            startActivity(new Intent(WallnitCircleUploadQuote.this,WallnitCircleProfile.class));
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            wallnitPostCircleQuote.insertCircleQuotePost(circlesesionid,quoteWrite);
            return null;
        }
    }
}
