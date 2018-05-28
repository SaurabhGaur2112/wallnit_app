package com.wallnit.wallnitlogin;

import android.content.Intent;
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

public class WallnitUserAccountWorkAndPlace extends AppCompatActivity {
    EditText work,place;
    Session session;
    String sessionUserId;
    InputStream is = null;
    String line = null;
    String result = null;
    String temp_work = "",temp_place = "";
    String[] arr_work,arr_place;
    String work_get,place_get;
    WallnitUserAccountWorkAndPlaceUpdate wallnitUserAccountWorkAndPlaceUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_account_work_and_place);
        work = (EditText) findViewById(R.id.work_ed);
        place = (EditText) findViewById(R.id.place_ed);
        session = new Session(this);
        sessionUserId = session.getUserSession();
        wallnitUserAccountWorkAndPlaceUpdate = new WallnitUserAccountWorkAndPlaceUpdate();

        List<NameValuePair> userSendId = new ArrayList<NameValuePair>(1);
        userSendId.add(new BasicNameValuePair("webusersession", sessionUserId));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_account_workplace_get.php");
            httpPost.setEntity(new UrlEncodedFormEntity(userSendId));
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
                temp_work += json_data.getString("first_work") + ":";
                temp_place += json_data.getString("first_place") + ":";
            }

            arr_work = temp_work.split(":");
            arr_place = temp_place.split(":");

            work_get = Arrays.toString(arr_work).replace("[", "").replace("]", "");
            place_get = Arrays.toString(arr_place).replace("[", "").replace("]", "");
            work.setText(work_get);
            place.setText(place_get);

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
            String worktxt,placetxt;
            worktxt = work.getText().toString();
            placetxt = place.getText().toString();
            wallnitUserAccountWorkAndPlaceUpdate.userWorkPlaceUpdate(sessionUserId,worktxt,placetxt);
            startActivity(new Intent(WallnitUserAccountWorkAndPlace.this,WallnitUserAccountSetting.class));
            finish();
        }
        return false;
    }
}
