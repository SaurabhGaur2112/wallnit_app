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
public class WallnitUserUpdateEditLinkPost {

    InputStream is = null;

    public void updateEditLinkPost(String post_number,String editLink_post,String editLinkDesc_post,String post_dateTime)
    {
        List<NameValuePair> loginEmailSend = new ArrayList<NameValuePair>(1);
        loginEmailSend.add(new BasicNameValuePair("postnumber", post_number));
        loginEmailSend.add(new BasicNameValuePair("linkpost", editLink_post));
        loginEmailSend.add(new BasicNameValuePair("linkpost_desc", editLinkDesc_post));
        loginEmailSend.add(new BasicNameValuePair("datetime", post_dateTime));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_update_editlink_post.php");
            httpPost.setEntity(new UrlEncodedFormEntity(loginEmailSend));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }

}
