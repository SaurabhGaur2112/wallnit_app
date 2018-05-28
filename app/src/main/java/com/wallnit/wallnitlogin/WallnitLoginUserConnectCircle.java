package com.wallnit.wallnitlogin;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saurabh Gaur on 11/24/2016.
 */
public class WallnitLoginUserConnectCircle {

    InputStream is = null;

    public String circleAutomaticConnectWithUser(String useridSend)
    {
        List<NameValuePair> userSendId = new ArrayList<NameValuePair>(1);
        userSendId.add(new BasicNameValuePair("webusersession", useridSend));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_connect_circles.php");
            httpPost.setEntity(new UrlEncodedFormEntity(userSendId));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
        return null;
    }
}
