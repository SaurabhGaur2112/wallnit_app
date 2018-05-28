package com.wallnit.wallnitlogin.WallnitUserUploadPosts;

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
public class WallnitPostUserStatus {

    InputStream is = null;

    public void insertUserBlogPost(String usersessionid,String uploadStatusText){

        List<NameValuePair> uploadStatusUserSend = new ArrayList<NameValuePair>(1);
        uploadStatusUserSend.add(new BasicNameValuePair("usersession",usersessionid));
        uploadStatusUserSend.add(new BasicNameValuePair("userstatusuploadtxt",uploadStatusText));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_uploadstatus.php");
            httpPost.setEntity(new UrlEncodedFormEntity(uploadStatusUserSend));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }

}
