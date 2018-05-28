package com.wallnit.wallnitlogin.WallnitForget;

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
 * Created by Saurabh Gaur on 1/20/2017.
 */
public class WallnitForgetGetNumber {

    public static String[] getvaluesCircleProfile(String emailidGet,String circleUserEmail)
    {
        InputStream is = null;
        String line = null;
        String result = null;
        String temp_first_no = "",temp_second_no = "";
        String[] arr_first_no,arr_second_no;
        String getfirst_no = null,getsecond_no = null;

        List<NameValuePair> loginEmailSend = new ArrayList<NameValuePair>(1);
        loginEmailSend.add(new BasicNameValuePair("emailidsend", emailidGet));
        loginEmailSend.add(new BasicNameValuePair("circleusersend", circleUserEmail));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_forget_get_number.php");
            httpPost.setEntity(new UrlEncodedFormEntity(loginEmailSend));
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
                temp_first_no += json_data.getString("forget_first_number") + ":";
                temp_second_no += json_data.getString("forget_second_number") + ":";
            }


            arr_first_no = temp_first_no.split(":");
            arr_second_no = temp_second_no.split(":");



            getfirst_no = Arrays.toString(arr_first_no).replace("[", "").replace("]", "");
            getsecond_no = Arrays.toString(arr_second_no).replace("[", "").replace("]", "");



        } catch (Exception e) {

        }


        return new String[]
                {
                        getfirst_no,getsecond_no
                };
    }
}
