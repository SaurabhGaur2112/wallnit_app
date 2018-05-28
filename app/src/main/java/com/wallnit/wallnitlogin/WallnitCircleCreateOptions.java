package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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

public class WallnitCircleCreateOptions extends AppCompatActivity {

    TextView entertainment_tv,health_and_fitness_tv,information_tv,business_tv,lifestyle_tv,other_tv;
    LinearLayout layout_first,layout_second,layout_third,layout_fourth,layout_fifth,layout_sixth;
    Session session;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;
    String getCircleNameValue;
    InputStream is = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_circle_create_options);
        entertainment_tv=(TextView) findViewById(R.id.entertainment_desc);
        health_and_fitness_tv=(TextView) findViewById(R.id.health_and_fitness_desc);
        information_tv=(TextView) findViewById(R.id.informational_desc);
        business_tv=(TextView) findViewById(R.id.business_desc);
        lifestyle_tv=(TextView) findViewById(R.id.lifestyle_desc);
        other_tv=(TextView) findViewById(R.id.other_desc);
        session = new Session(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        entertainment_tv.setText("This arena of interest includes activities related to dance, music, comics, literature," +
                "TV episodes, artists, humor, actors etc.");
        health_and_fitness_tv.setText("This arena of interest includes activities related to exercise, yoga, sports etc");
        information_tv.setText("This arena of interest includes activities related to history, magazines, news, education, " +
                "science, technology etc.");
        business_tv.setText("This arena of interest includes activities related to startup, entrepreneur etc.");
        lifestyle_tv.setText("This arena of interest includes activities related to fashion, travel, photography etc.");
        other_tv.setText("Not satisfied with the provided category? Create circle of your own interest");

        layout_first = (LinearLayout) findViewById(R.id.create_layout_first);
        layout_second = (LinearLayout) findViewById(R.id.create_layout_second);
        layout_third = (LinearLayout) findViewById(R.id.create_layout_third);
        layout_fourth = (LinearLayout) findViewById(R.id.create_layout_fourth);
        layout_fifth = (LinearLayout) findViewById(R.id.create_layout_fifth);
        layout_sixth = (LinearLayout) findViewById(R.id.create_layout_sixth);


        layout_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String circlesessionid = session.getCircleSession();
                List<NameValuePair> getCircleName = new ArrayList<NameValuePair>(1);
                getCircleName.add(new BasicNameValuePair("circlesession",circlesessionid));
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circlename_get.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(getCircleName));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }catch (Exception e){

                }
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuffer sb = new StringBuffer();
                    while((line = reader.readLine())!=null)
                        sb.append(line+"\n");
                    result = sb.toString();

                    is.close();
                }catch (Exception e){

                }
                try{
                    JSONArray jArray = new JSONArray(result);

                    int count = jArray.length();

                    for(int i=0;i<count;i++){
                        JSONObject json_data = jArray.getJSONObject(i);
                        temp += json_data.getString("circlename")+":";
                    }

                    arr = temp.split(":");
                    getCircleNameValue = Arrays.toString(arr).replace("[","").replace("]","");

                }catch (Exception e){

                }

                Intent i = new Intent(getApplicationContext(),WallnitCircleCreateEntertainment.class);
                i.putExtra("putcirclename",getCircleNameValue);
                startActivity(i);
            }
        });

        layout_second.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String circlesessionid = session.getCircleSession();
                List<NameValuePair> getCircleName = new ArrayList<NameValuePair>(1);
                getCircleName.add(new BasicNameValuePair("circlesession",circlesessionid));
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circlename_get.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(getCircleName));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }catch (Exception e){

                }
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuffer sb = new StringBuffer();
                    while((line = reader.readLine())!=null)
                        sb.append(line+"\n");
                    result = sb.toString();

                    is.close();
                }catch (Exception e){

                }
                try{
                    JSONArray jArray = new JSONArray(result);

                    int count = jArray.length();

                    for(int i=0;i<count;i++){
                        JSONObject json_data = jArray.getJSONObject(i);
                        temp += json_data.getString("circlename")+":";
                    }

                    arr = temp.split(":");
                    getCircleNameValue = Arrays.toString(arr).replace("[","").replace("]","");

                }catch (Exception e){

                }

                Intent i = new Intent(getApplicationContext(),WallnitCircleCreateHealthAndFitness.class);
                i.putExtra("putcirclename",getCircleNameValue);
                startActivity(i);
            }
        });

        layout_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String circlesessionid = session.getCircleSession();
                List<NameValuePair> getCircleName = new ArrayList<NameValuePair>(1);
                getCircleName.add(new BasicNameValuePair("circlesession",circlesessionid));
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circlename_get.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(getCircleName));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }catch (Exception e){

                }
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuffer sb = new StringBuffer();
                    while((line = reader.readLine())!=null)
                        sb.append(line+"\n");
                    result = sb.toString();

                    is.close();
                }catch (Exception e){

                }
                try{
                    JSONArray jArray = new JSONArray(result);

                    int count = jArray.length();

                    for(int i=0;i<count;i++){
                        JSONObject json_data = jArray.getJSONObject(i);
                        temp += json_data.getString("circlename")+":";
                    }

                    arr = temp.split(":");
                    getCircleNameValue = Arrays.toString(arr).replace("[","").replace("]","");

                }catch (Exception e){

                }

                Intent i = new Intent(getApplicationContext(),WallnitCircleCreateInformational.class);
                i.putExtra("putcirclename",getCircleNameValue);
                startActivity(i);
            }
        });

        layout_fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String circlesessionid = session.getCircleSession();
                List<NameValuePair> getCircleName = new ArrayList<NameValuePair>(1);
                getCircleName.add(new BasicNameValuePair("circlesession",circlesessionid));
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circlename_get.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(getCircleName));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }catch (Exception e){

                }
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuffer sb = new StringBuffer();
                    while((line = reader.readLine())!=null)
                        sb.append(line+"\n");
                    result = sb.toString();

                    is.close();
                }catch (Exception e){

                }
                try{
                    JSONArray jArray = new JSONArray(result);

                    int count = jArray.length();

                    for(int i=0;i<count;i++){
                        JSONObject json_data = jArray.getJSONObject(i);
                        temp += json_data.getString("circlename")+":";
                    }

                    arr = temp.split(":");
                    getCircleNameValue = Arrays.toString(arr).replace("[","").replace("]","");

                }catch (Exception e){

                }

                Intent i = new Intent(getApplicationContext(),WallnitCircleCreateBusiness.class);
                i.putExtra("putcirclename",getCircleNameValue);
                startActivity(i);
            }
        });

        layout_fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String circlesessionid = session.getCircleSession();
                List<NameValuePair> getCircleName = new ArrayList<NameValuePair>(1);
                getCircleName.add(new BasicNameValuePair("circlesession",circlesessionid));
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circlename_get.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(getCircleName));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }catch (Exception e){

                }
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuffer sb = new StringBuffer();
                    while((line = reader.readLine())!=null)
                        sb.append(line+"\n");
                    result = sb.toString();

                    is.close();
                }catch (Exception e){

                }
                try{
                    JSONArray jArray = new JSONArray(result);

                    int count = jArray.length();

                    for(int i=0;i<count;i++){
                        JSONObject json_data = jArray.getJSONObject(i);
                        temp += json_data.getString("circlename")+":";
                    }

                    arr = temp.split(":");
                    getCircleNameValue = Arrays.toString(arr).replace("[","").replace("]","");

                }catch (Exception e){

                }

                Intent i = new Intent(getApplicationContext(),WallnitCircleCreateLifestyle.class);
                i.putExtra("putcirclename",getCircleNameValue);
                startActivity(i);
            }
        });

        layout_sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String circlesessionid = session.getCircleSession();
                List<NameValuePair> getCircleName = new ArrayList<NameValuePair>(1);
                getCircleName.add(new BasicNameValuePair("circlesession",circlesessionid));
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circlename_get.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(getCircleName));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }catch (Exception e){

                }
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuffer sb = new StringBuffer();
                    while((line = reader.readLine())!=null)
                        sb.append(line+"\n");
                    result = sb.toString();

                    is.close();
                }catch (Exception e){

                }
                try{
                    JSONArray jArray = new JSONArray(result);

                    int count = jArray.length();

                    for(int i=0;i<count;i++){
                        JSONObject json_data = jArray.getJSONObject(i);
                        temp += json_data.getString("circlename")+":";
                    }

                    arr = temp.split(":");
                    getCircleNameValue = Arrays.toString(arr).replace("[","").replace("]","");

                }catch (Exception e){

                }

                Intent i = new Intent(getApplicationContext(),WallnitCircleCreateOther.class);
                i.putExtra("putcirclename",getCircleNameValue);
                startActivity(i);
            }
        });
    }
}
