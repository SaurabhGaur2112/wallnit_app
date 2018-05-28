package com.wallnit.wallnitlogin.WallnitUserUpdateEditPost;

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
 * Created by Saurabh Gaur on 12/26/2016.
 */
public class WallnitUserUpdateEditBlogPost {

    InputStream is = null;

    public void updateEditBlogPost(String post_number,String editblogTitle,String editblogBody,String post_dateTime)
    {
        List<NameValuePair> loginEmailSend = new ArrayList<NameValuePair>(1);
        loginEmailSend.add(new BasicNameValuePair("post_number", post_number));
        loginEmailSend.add(new BasicNameValuePair("blogpost_title", editblogTitle));
        loginEmailSend.add(new BasicNameValuePair("blogpost_body", editblogBody));
        loginEmailSend.add(new BasicNameValuePair("blogdatetime", post_dateTime));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_update_editblog_post.php");
            httpPost.setEntity(new UrlEncodedFormEntity(loginEmailSend));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }

}
