package com.wallnit.wallnitlogin.WallnitCircleUpdateEditPost;

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
 * Created by Saurabh Gaur on 12/29/2016.
 */
public class WallnitCircleUpdateEditPhotosPost {

    InputStream is = null;

    public void updateEditPhotosPost(String post_number,String editCaption_post,String post_dateTime)
    {
        List<NameValuePair> loginEmailSend = new ArrayList<NameValuePair>(1);
        loginEmailSend.add(new BasicNameValuePair("photos_number", post_number));
        loginEmailSend.add(new BasicNameValuePair("photos_caption", editCaption_post));
        loginEmailSend.add(new BasicNameValuePair("post_datetime", post_dateTime));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_update_editphotos_post.php");
            httpPost.setEntity(new UrlEncodedFormEntity(loginEmailSend));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }

}
