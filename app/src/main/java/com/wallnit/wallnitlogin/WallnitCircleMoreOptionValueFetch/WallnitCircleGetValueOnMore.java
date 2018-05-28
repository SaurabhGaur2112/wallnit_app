package com.wallnit.wallnitlogin.WallnitCircleMoreOptionValueFetch;

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
 * Created by Saurabh Gaur on 1/19/2017.
 */
public class WallnitCircleGetValueOnMore {

    public static String[] getvaluesCircleMore(String circleid)
    {
        InputStream is = null;
        String line = null;
        String result = null;
        String temp_username = "",temp_email = "",temp_profile_image = "";
        String[] arr_username,arr_email,arr_profile_image;
        String getusername = null,getemail = null,getprofile_image = null;

        List<NameValuePair> loginEmailSend = new ArrayList<NameValuePair>(1);
        loginEmailSend.add(new BasicNameValuePair("sessioncircleid", circleid));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_get_more_values.php");
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
                temp_username += json_data.getString("circlename") + ":";
                temp_email += json_data.getString("email_address") + ":";
                temp_profile_image += json_data.getString("profile_image") + ":";

            }

            arr_username = temp_username.split(":");
            arr_email = temp_email.split(":");
            arr_profile_image = temp_profile_image.split(":");


            getusername = Arrays.toString(arr_username).replace("[", "").replace("]", "");
            getemail = Arrays.toString(arr_email).replace("[", "").replace("]", "");
            getprofile_image = Arrays.toString(arr_profile_image).replace("[", "").replace("]", "");


        } catch (Exception e) {

        }


        return new String[]
                {
                        getusername,getemail,getprofile_image
                };
    }

}
