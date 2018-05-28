package com.wallnit.wallnitlogin.WallnitUserProfileFetchDetails;


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
 * Created by Saurabh Gaur on 12/9/2016.
 */
public class WallnitUserProfileGetValues {

    public static String[] getvaluesProfile(String userid)
    {
        InputStream is = null;
        String line = null;
        String result = null;
        String temp_user = "",temp_tagname = "",temp_profile_image = "",temp_gender = "",temp_month = "",temp_day = "",temp_year = "",temp_place = "",temp_work = "",
                temp_description = "",temp_status = "",temp_background = "",temp_totalnum_follower = "",temp_totalnum_following = "",temp_userpost = "";
        String[] arr_user,arr_tagname,arr_profile_image,arr_gender,arr_month,arr_day,arr_year,arr_place,arr_work,arr_description,arr_status,arr_background,arr_totalnum_follower,arr_totalnum_following,arr_userpost;
        String getusername = null,gethashcode = null,getprofile_image = null,gender = null,month = null,day = null,year = null,getplace = null,
                getwork = null,getuser_description = null,getuser_status = null,getuser_background = null,getuser_totalnum_follower = null,getuser_totalnum_following = null,getuser_post = null;

        List<NameValuePair> loginEmailSend = new ArrayList<NameValuePair>(1);
        loginEmailSend.add(new BasicNameValuePair("usersessionid", userid));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_profile_description.php");
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
                temp_user += json_data.getString("profile_username") + ":";
                temp_tagname += json_data.getString("profile_tagname") + ":";
                temp_profile_image += json_data.getString("profile_image") + ":";
                temp_gender += json_data.getString("profile_gender") + ":";
                temp_month += json_data.getString("profile_month") + ":";
                temp_day += json_data.getString("profile_day") + ":";
                temp_year += json_data.getString("profile_year") + ":";
                temp_place += json_data.getString("profile_first_place") + ":";
                temp_work += json_data.getString("profile_first_work") + ":";
                temp_description += json_data.getString("profile_about_desc") + ":";
                temp_status += json_data.getString("profile_user_status") + ":";
                temp_background += json_data.getString("background_image") + ":";
                temp_totalnum_follower += json_data.getString("totalnum_follower") + ":";
                temp_totalnum_following += json_data.getString("totalnum_following") + ":";
                temp_userpost += json_data.getString("user_post_insert") + ":";
            }

            arr_user = temp_user.split(":");
            arr_tagname = temp_tagname.split(":");
            arr_profile_image = temp_profile_image.split(":");
            arr_gender = temp_gender.split(":");
            arr_month = temp_month.split(":");
            arr_day = temp_day.split(":");
            arr_year = temp_year.split(":");
            arr_place = temp_place.split(":");
            arr_work = temp_work.split(":");
            arr_description = temp_description.split(":");
            arr_status = temp_status.split(":");
            arr_background = temp_background.split(":");
            arr_totalnum_follower = temp_totalnum_follower.split(":");
            arr_totalnum_following = temp_totalnum_following.split(":");
            arr_userpost = temp_userpost.split(":");

            getusername = Arrays.toString(arr_user).replace("[", "").replace("]", "");
            gethashcode = Arrays.toString(arr_tagname).replace("[", "").replace("]", "");
            getprofile_image = Arrays.toString(arr_profile_image).replace("[", "").replace("]", "");
            gender = Arrays.toString(arr_gender).replace("[", "").replace("]", "");
            month = Arrays.toString(arr_month).replace("[", "").replace("]", "");
            day = Arrays.toString(arr_day).replace("[", "").replace("]", "");
            year = Arrays.toString(arr_year).replace("[", "").replace("]", "");
            getplace = Arrays.toString(arr_place).replace("[", "").replace("]", "");
            getwork = Arrays.toString(arr_work).replace("[", "").replace("]", "");
            getuser_description = Arrays.toString(arr_description).replace("[", "").replace("]", "");
            getuser_status = Arrays.toString(arr_status).replace("[", "").replace("]", "");
            getuser_background = Arrays.toString(arr_background).replace("[", "").replace("]", "");
            getuser_totalnum_follower = Arrays.toString(arr_totalnum_follower).replace("[", "").replace("]", "");
            getuser_totalnum_following = Arrays.toString(arr_totalnum_following).replace("[", "").replace("]", "");
            getuser_post = Arrays.toString(arr_userpost).replace("[", "").replace("]", "");


        } catch (Exception e) {

        }


        return new String[]
                {
                        getusername,gethashcode,getprofile_image,gender,month,day,year,getplace,getwork,getuser_description,getuser_status,getuser_background,getuser_totalnum_follower,getuser_totalnum_following,getuser_post
                };
    }
}
