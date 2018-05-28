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
public class WallnitCircleAccountContactUpdate {

    InputStream is = null;
    public void circleAccountContactUpdate(String circleid,String email,String website,String contact)
    {
        List<NameValuePair> sendCircleId = new ArrayList<NameValuePair>(1);
        sendCircleId.add(new BasicNameValuePair("circlesession", circleid));
        sendCircleId.add(new BasicNameValuePair("webemail", email));
        sendCircleId.add(new BasicNameValuePair("webwebsite", website));
        sendCircleId.add(new BasicNameValuePair("webcontact", contact));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_accountcontact_update.php");
            httpPost.setEntity(new UrlEncodedFormEntity(sendCircleId));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }
}
