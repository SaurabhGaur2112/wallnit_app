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

public class WallnitCircleAccountInformation extends AppCompatActivity {
    Session session;
    String sessioncircleid;
    EditText circlename,circle_createdon,circle_creator,circle_category,circle_content;
    WallnitCircleAccountCirclenameGet wallnitCircleAccountCirclenameGet;
    String circlename_get;
    InputStream is = null;
    String line = null;
    String result = null;
    String temp_created = "",temp_creator = "",temp_category = "",temp_content = "";
    String[] arr_created,arr_creator,arr_category,arr_content;
    String circleGetCreated,circleGetCreator,circleGetCategory,circleGetContent;
    WallnitCircleAccountInfoUpdate wallnitCircleAccountInfoUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_account_information);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        session = new Session(this);
        sessioncircleid = session.getCircleSession();
        circlename = (EditText) findViewById(R.id.circle_account_circlename);
        circle_createdon = (EditText) findViewById(R.id.circle_account_createdon);
        circle_creator = (EditText) findViewById(R.id.circle_account_creator);
        circle_category = (EditText) findViewById(R.id.circle_account_category);
        circle_content = (EditText) findViewById(R.id.circle_account_contentto_post);
        wallnitCircleAccountCirclenameGet = new WallnitCircleAccountCirclenameGet();
        circlename_get = wallnitCircleAccountCirclenameGet.getCirclename(sessioncircleid);
        circlename.setText(circlename_get);
        wallnitCircleAccountInfoUpdate = new WallnitCircleAccountInfoUpdate();

        List<NameValuePair> sendCircleId = new ArrayList<NameValuePair>(1);
        sendCircleId.add(new BasicNameValuePair("webcirclesession", sessioncircleid));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circleinfo_get.php");
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
                temp_created += json_data.getString("circle_created_on") + ":";
                temp_creator += json_data.getString("circle_creator") + ":";
                temp_category += json_data.getString("circle_category") + ":";
                temp_content += json_data.getString("circle_type") + ":";
            }

            arr_created = temp_created.split(":");
            arr_creator = temp_creator.split(":");
            arr_category = temp_category.split(":");
            arr_content = temp_content.split(":");

            circleGetCreated = Arrays.toString(arr_created).replace("[", "").replace("]", "");
            circleGetCreator = Arrays.toString(arr_creator).replace("[", "").replace("]", "");
            circleGetCategory = Arrays.toString(arr_category).replace("[", "").replace("]", "");
            circleGetContent = Arrays.toString(arr_content).replace("[", "").replace("]", "");

            circle_createdon.setText(circleGetCreated);
            circle_creator.setText(circleGetCreator);
            circle_category.setText(circleGetCategory);
            circle_content.setText(circleGetContent);

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
            String circlenametxt,creatortxt;
            circlenametxt = circlename.getText().toString();
            creatortxt = circle_creator.getText().toString();
            wallnitCircleAccountInfoUpdate.circleAccountInfoUpdate(circlenametxt,creatortxt,sessioncircleid);
            startActivity(new Intent(WallnitCircleAccountInformation.this,WallnitCircleAccountSetting.class));
            finish();
        }
        return false;
    }
}
