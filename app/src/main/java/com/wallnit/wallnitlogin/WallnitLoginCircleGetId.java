package com.wallnit.wallnitlogin;

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

/**
 * Created by Saurabh Gaur on 11/24/2016.
 */
public class WallnitLoginCircleGetId {

    InputStream is = null;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;
    String getCircleLoginId;

    public String loginCircleGetId(String loginemailid,String loginpassword)
    {
        List<NameValuePair> loginEmailPasswordSend = new ArrayList<NameValuePair>(1);
        loginEmailPasswordSend.add(new BasicNameValuePair("webloginemailid", loginemailid));
        loginEmailPasswordSend.add(new BasicNameValuePair("webloginpassword", loginpassword));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_login_circle.php");
            httpPost.setEntity(new UrlEncodedFormEntity(loginEmailPasswordSend));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
        List<NameValuePair> loginEmailGetIdSend = new ArrayList<NameValuePair>(1);
        loginEmailGetIdSend.add(new BasicNameValuePair("webloginemailid", loginemailid));
        loginEmailGetIdSend.add(new BasicNameValuePair("webloginpassword", loginpassword));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_login_circleid_get.php");
            httpPost.setEntity(new UrlEncodedFormEntity(loginEmailGetIdSend));
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
                temp += json_data.getString("getCircleId") + ":";
            }

            arr = temp.split(":");

            getCircleLoginId = Arrays.toString(arr).replace("[", "").replace("]", "");

        } catch (Exception e) {

        }

        return getCircleLoginId;
    }
}
