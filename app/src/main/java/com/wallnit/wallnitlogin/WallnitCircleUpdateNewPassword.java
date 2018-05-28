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
public class WallnitCircleUpdateNewPassword {

    InputStream is = null;
    public String circleUpdateNewPassword(String newPassword,String circleid)
    {
        List<NameValuePair> circleSendIdAndPassword = new ArrayList<NameValuePair>(1);
        circleSendIdAndPassword.add(new BasicNameValuePair("webcirclesession", circleid));
        circleSendIdAndPassword.add(new BasicNameValuePair("webnewpassword", newPassword));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_update_new_password.php");
            httpPost.setEntity(new UrlEncodedFormEntity(circleSendIdAndPassword));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
        return null;
    }
}
