package com.wallnit.wallnitlogin.WallnitCircleGetEditPost;

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
 * Created by Saurabh Gaur on 12/29/2016.
 */
public class WallnitCircleGetEditQuotePost {

    public static String[] getvaluesEditPostNumber(String post_number)
    {
        InputStream is = null;
        String line = null;
        String result = null;
        String temp_quotePost = "",temp_dateTime = "";
        String[] arr_quotePost,arr_dateTime;
        String getquotePost = null,getDateTime = null;

        List<NameValuePair> loginEmailSend = new ArrayList<NameValuePair>(1);
        loginEmailSend.add(new BasicNameValuePair("editquote_number", post_number));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_get_editquote_post.php");
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
                temp_quotePost += json_data.getString("quote") + ":";
                temp_dateTime += json_data.getString("circle_user_post_date_time") + ":";

            }

            arr_quotePost = temp_quotePost.split(":");
            arr_dateTime = temp_dateTime.split(":");


            getquotePost = Arrays.toString(arr_quotePost).replace("[", "").replace("]", "");
            getDateTime = Arrays.toString(arr_dateTime).replace("[", "").replace("]", "");



        } catch (Exception e) {

        }


        return new String[]
                {
                        getquotePost,getDateTime
                };
    }
}
