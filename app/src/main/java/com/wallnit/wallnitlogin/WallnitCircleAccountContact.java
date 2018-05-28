package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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

public class WallnitCircleAccountContact extends AppCompatActivity {
    EditText circle_emailtxt,circle_websitetxt,circle_contacttxt;
    Session session;
    String sessioncircleid;
    InputStream is = null;
    String line = null;
    String result = null;
    String temp_email = "",temp_website = "",temp_contact = "";
    String[] arr_email,arr_website,arr_contact;
    String circleGetEmail,circleGetWebsite,circleGetContact;
    WallnitCircleAccountContactUpdate wallnitCircleAccountContactUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_account_contact);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        session = new Session(this);
        sessioncircleid = session.getCircleSession();
        circle_emailtxt = (EditText) findViewById(R.id.circle_email);
        circle_websitetxt = (EditText) findViewById(R.id.circle_website);
        circle_contacttxt = (EditText) findViewById(R.id.circle_contact);
        wallnitCircleAccountContactUpdate = new WallnitCircleAccountContactUpdate();

        List<NameValuePair> sendCircleId = new ArrayList<NameValuePair>(1);
        sendCircleId.add(new BasicNameValuePair("webcirclesession", sessioncircleid));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circlecontact_get.php");
            httpPost.setEntity(new UrlEncodedFormEntity(sendCircleId));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null)
                sb.append(line + "\n");
            result = sb.toString();
            is.close();
        } catch (Exception e) {

        }
        try {
            JSONArray jArray = new JSONArray(result);
            int count = jArray.length();

            for (int i = 0; i < count; i++) {

                JSONObject json_data = jArray.getJSONObject(i);
                temp_email += json_data.getString("contact_email") + ":";
                temp_website += json_data.getString("circle_website") + ":";
                temp_contact += json_data.getString("circle_contact") + ":";
            }

            arr_email = temp_email.split(":");
            arr_website = temp_website.split(":");
            arr_contact = temp_contact.split(":");

            circleGetEmail = Arrays.toString(arr_email).replace("[", "").replace("]", "");
            circleGetWebsite = Arrays.toString(arr_website).replace("[", "").replace("]", "");
            circleGetContact = Arrays.toString(arr_contact).replace("[", "").replace("]", "");

            circle_emailtxt.setText(circleGetEmail);
            circle_websitetxt.setText(circleGetWebsite);
            circle_contacttxt.setText(circleGetContact);

        } catch (Exception e) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_account_information_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.account_infochange)
        {
            String circle_email,circle_website,circle_contact;
            circle_email = circle_emailtxt.getText().toString();
            circle_website = circle_websitetxt.getText().toString();
            circle_contact = circle_contacttxt.getText().toString();
            wallnitCircleAccountContactUpdate.circleAccountContactUpdate(sessioncircleid,circle_email,circle_website,circle_contact);
            startActivity(new Intent(WallnitCircleAccountContact.this,WallnitCircleAccountSetting.class));
            finish();
        }
        return false;
    }
}
