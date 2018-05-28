package com.wallnit.wallnitlogin;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saurabh Gaur on 11/26/2016.
 */
public class WallnitUserAccountInfoUpdate {

    InputStream is = null;

    public void userAccountInfoUpdate(String usernametxt,String tagnametxt,String userid)
    {
        List<NameValuePair> sendUserId = new ArrayList<NameValuePair>(1);
        sendUserId.add(new BasicNameValuePair("webusersession", userid));
        sendUserId.add(new BasicNameValuePair("username", usernametxt));
        sendUserId.add(new BasicNameValuePair("tagname", tagnametxt));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_account_accountinfo_update.php");
            httpPost.setEntity(new UrlEncodedFormEntity(sendUserId));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }
}
