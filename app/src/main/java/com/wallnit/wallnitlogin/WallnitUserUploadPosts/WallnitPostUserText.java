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
public class WallnitPostUserText {

    InputStream is = null;

    public void insertUserTextPost(String usersessionid,String textPost,String postUserOrCircle,String postAnonymous){
        List<NameValuePair> textPostUpload = new ArrayList<NameValuePair>(1);
        textPostUpload.add(new BasicNameValuePair("usersession",usersessionid));
        textPostUpload.add(new BasicNameValuePair("textuploadpost",textPost));
        textPostUpload.add(new BasicNameValuePair("postinuserorcircle", postUserOrCircle));
        textPostUpload.add(new BasicNameValuePair("usertxtpostanonymous",postAnonymous));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_uploadtextpost.php");
            httpPost.setEntity(new UrlEncodedFormEntity(textPostUpload));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }
}
