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
public class WallnitUserUpdateNewPassword {

    InputStream is = null;
    public String userUpdateNewPassword(String newPassword,String userId)
    {
        List<NameValuePair> userSendIdAndPassword = new ArrayList<NameValuePair>(1);
        userSendIdAndPassword.add(new BasicNameValuePair("webusersession", userId));
        userSendIdAndPassword.add(new BasicNameValuePair("webnewpassword", newPassword));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_update_new_password.php");
            httpPost.setEntity(new UrlEncodedFormEntity(userSendIdAndPassword));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
        return null;
    }
}
