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
public class WallnitPostUserBlog {

    InputStream is = null;

    public void insertUserBlogPost(String usersessionid,String blogPosttxt,String blogPostBodytxt,String postUserOrCircletxt,String postAnonymoustxt){

        List<NameValuePair> blogPostUploadSend = new ArrayList<NameValuePair>(1);
        blogPostUploadSend.add(new BasicNameValuePair("usersession",usersessionid));
        blogPostUploadSend.add(new BasicNameValuePair("bloguploadposttitle",blogPosttxt));
        blogPostUploadSend.add(new BasicNameValuePair("bloguploadpostbody", blogPostBodytxt));
        blogPostUploadSend.add(new BasicNameValuePair("postinuserorcircle", postUserOrCircletxt));
        blogPostUploadSend.add(new BasicNameValuePair("userblogpostanonymous",postAnonymoustxt));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_uploadblogpost.php");
            httpPost.setEntity(new UrlEncodedFormEntity(blogPostUploadSend));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }
}
