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
 * Created by Saurabh Gaur on 11/23/2016.
 */
public class CircleCreate {

    public void createCircleSendValues(String circlesessionid,String circleContentToPost,String circleCategoryToPost,String circleDescriptionWrite)
    {
        InputStream is = null;
        List<NameValuePair> createCircleSend = new ArrayList<NameValuePair>(1);
        createCircleSend.add(new BasicNameValuePair("circlesession",circlesessionid));
        createCircleSend.add(new BasicNameValuePair("contenttopost",circleContentToPost));
        createCircleSend.add(new BasicNameValuePair("categorytopost",circleCategoryToPost));
        createCircleSend.add(new BasicNameValuePair("circledesc",circleDescriptionWrite));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_create.php");
            httpPost.setEntity(new UrlEncodedFormEntity(createCircleSend));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch (Exception e){

        }
    }
}
