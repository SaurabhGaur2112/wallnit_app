package com.wallnit.wallnitlogin.WallnitCircleProfileFetchDetails;

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
 * Created by Saurabh Gaur on 12/28/2016.
 */
public class WallnitCircleProfileGetValues {

    public static String[] getvaluesCircleProfile(String circleid)
    {
        InputStream is = null;
        String line = null;
        String result = null;
        String temp_circlename = "",temp_profile_image = "",temp_category = "",temp_type = "",temp_background = "",temp_about_desc = "",temp_whow_can_post = "",temp_post_or_not = "";
        String[] arr_circlename,arr_profile_image,arr_category,arr_type,arr_background,arr_about_desc,arr_who_can_post,arr_post_or_not;
        String getcirclename = null,getprofile_image = null,category = null,type = null,background = null,about_desc = null,who_can_post = null,post_or_not = null;

        List<NameValuePair> loginEmailSend = new ArrayList<NameValuePair>(1);
        loginEmailSend.add(new BasicNameValuePair("circlesessionid", circleid));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_profile_details.php");
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
                temp_circlename += json_data.getString("profile_circlename") + ":";
                temp_profile_image += json_data.getString("profile_image") + ":";
                temp_category += json_data.getString("profile_category") + ":";
                temp_type += json_data.getString("profile_circle_type") + ":";
                temp_background += json_data.getString("profile_circle_background") + ":";
                temp_about_desc += json_data.getString("profile_circle_about_desc") + ":";
                temp_whow_can_post += json_data.getString("profile_circle_who_can_post") + ":";
                temp_post_or_not += json_data.getString("profile_circle_postornot") + ":";

            }


            arr_circlename = temp_circlename.split(":");
            arr_profile_image = temp_profile_image.split(":");
            arr_category = temp_category.split(":");
            arr_type = temp_type.split(":");
            arr_background = temp_background.split(":");
            arr_about_desc = temp_about_desc.split(":");
            arr_who_can_post = temp_whow_can_post.split(":");
            arr_post_or_not = temp_post_or_not.split(":");


            getcirclename = Arrays.toString(arr_circlename).replace("[", "").replace("]", "");
            getprofile_image = Arrays.toString(arr_profile_image).replace("[", "").replace("]", "");
            category = Arrays.toString(arr_category).replace("[", "").replace("]", "");
            type = Arrays.toString(arr_type).replace("[", "").replace("]", "");
            background = Arrays.toString(arr_background).replace("[", "").replace("]", "");
            about_desc = Arrays.toString(arr_about_desc).replace("[", "").replace("]", "");
            who_can_post = Arrays.toString(arr_who_can_post).replace("[", "").replace("]", "");
            post_or_not = Arrays.toString(arr_post_or_not).replace("[","").replace("]","");



        } catch (Exception e) {

        }


        return new String[]
                {
                        getcirclename,getprofile_image,category,type,background,about_desc,who_can_post,post_or_not
                };
    }
}
