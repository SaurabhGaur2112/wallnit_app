package com.wallnit.wallnitlogin.WallnitCircleUploadPost;

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
 * Created by Saurabh Gaur on 1/27/2017.
 */
public class WallnitPostCircleBlog {

    InputStream is = null;

    public void insertCircleBlogPost(String circleid,String blogtitle_txt,String blogbody_txt){
        List<NameValuePair> textPostUpload = new ArrayList<NameValuePair>(1);
        textPostUpload.add(new BasicNameValuePair("signup_circleid",circleid));
        textPostUpload.add(new BasicNameValuePair("blogtitle",blogtitle_txt));
        textPostUpload.add(new BasicNameValuePair("blogbody",blogbody_txt));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_uploadown_blog.php");
            httpPost.setEntity(new UrlEncodedFormEntity(textPostUpload));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }
}
