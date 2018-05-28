package com.wallnit.wallnitlogin;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WallnitUserPostReport extends AppCompatActivity implements View.OnClickListener {

    TextView report_headline_txt;
    RadioButton radio_first,radio_second,radio_third,radio_fourth,radio_fifth,radio_sixth;
    EditText report_edtxt,report_options,report_query;
    Session session;
    InputStream is = null;
    String getPostNumberReport;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_post_report);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        session = new Session(this);

        cd = new ConnectionDetector(this);
        report_headline_txt = (TextView) findViewById(R.id.report_headertxt);
        report_headline_txt.setText("What's wrong with this post?");
        radio_first = (RadioButton) findViewById(R.id.report_first);
        radio_second = (RadioButton) findViewById(R.id.report_second);
        radio_third = (RadioButton) findViewById(R.id.report_third);
        radio_fourth = (RadioButton) findViewById(R.id.report_fourth);
        radio_fifth = (RadioButton) findViewById(R.id.report_fifth);
        radio_sixth = (RadioButton) findViewById(R.id.report_sixth);
        report_edtxt = (EditText) findViewById(R.id.report_other_text);
        report_options = (EditText) findViewById(R.id.report_select_options);
        report_query = (EditText) findViewById(R.id.report_query);
        radio_first.setText("It's a Spam");
        radio_second.setText("It will spread Hatred");
        radio_third.setText("It is false and misleading");
        radio_fourth.setText("It is vulgar and indecent");
        radio_fifth.setText("It is Immoral");
        radio_sixth.setText("Something else?");

        Intent i = getIntent();
        getPostNumberReport = i.getStringExtra("repost_number_post");

        radio_first.setOnClickListener(this);
        radio_second.setOnClickListener(this);
        radio_third.setOnClickListener(this);
        radio_fourth.setOnClickListener(this);
        radio_fifth.setOnClickListener(this);
        radio_sixth.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_report_submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.report_post_submit)
        {
            if(cd.isConnected()) {

                String get_report_option = report_options.getText().toString();
                String get_query_report;

                if (get_report_option.trim().length() != 0) {
                    if (get_report_option.equals("first")) {
                        get_query_report = report_query.getText().toString();
                        submit_report_onServer(get_query_report);
                    }
                    if (get_report_option.equals("second")) {
                        get_query_report = report_query.getText().toString();
                        submit_report_onServer(get_query_report);
                    }
                    if (get_report_option.equals("third")) {
                        get_query_report = report_query.getText().toString();
                        submit_report_onServer(get_query_report);
                    }
                    if (get_report_option.equals("fourth")) {
                        get_query_report = report_query.getText().toString();
                        submit_report_onServer(get_query_report);
                    }
                    if (get_report_option.equals("fifth")) {
                        get_query_report = report_query.getText().toString();
                        submit_report_onServer(get_query_report);
                    }
                    if (get_report_option.equals("sixth")) {
                        get_query_report = report_edtxt.getText().toString();
                        submit_report_onServer(get_query_report);
                    }
                } else {

                }
            } else {
                Toast.makeText(WallnitUserPostReport.this, "No interner connection", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.report_first) {
            report_options.setText("first");
            report_query.setText("It's a Spam");
            report_edtxt.setVisibility(View.GONE);
        }
        if(view.getId()==R.id.report_second) {
            report_options.setText("second");
            report_query.setText("It will spread Hatred");
            report_edtxt.setVisibility(View.GONE);
        }
        if(view.getId()==R.id.report_third) {
            report_options.setText("third");
            report_query.setText("It is false and misleading");
            report_edtxt.setVisibility(View.GONE);
        }
        if(view.getId()==R.id.report_fourth) {
            report_options.setText("fourth");
            report_query.setText("It is vulgar and indecent");
            report_edtxt.setVisibility(View.GONE);
        }
        if(view.getId()==R.id.report_fifth) {
            report_options.setText("fifth");
            report_query.setText("It is Immoral");
            report_edtxt.setVisibility(View.GONE);
        }
        if (view.getId() == R.id.report_sixth) {
            report_options.setText("sixth");
            report_query.setText(null);
            report_edtxt.setVisibility(View.VISIBLE);
        }
    }

    public void submit_report_onServer(String report_q)
    {
        String sessionUserId = session.getUserSession();

        List<NameValuePair> textPostUpload = new ArrayList<NameValuePair>(1);
        textPostUpload.add(new BasicNameValuePair("userpostnumbertxt",getPostNumberReport));
        textPostUpload.add(new BasicNameValuePair("usersession",sessionUserId));
        textPostUpload.add(new BasicNameValuePair("textuploadpost",report_q));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_post_report.php");
            httpPost.setEntity(new UrlEncodedFormEntity(textPostUpload));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch (Exception e){

        }

        startActivity(new Intent(WallnitUserPostReport.this,Wall.class));
        finish();
    }
}
