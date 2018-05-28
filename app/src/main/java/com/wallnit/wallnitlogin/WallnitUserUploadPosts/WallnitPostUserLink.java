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
public class WallnitPostUserLink {

    InputStream is = null;

    public void insertUserLinkPost(String usersessionid,String linkPost,String linkPostDesc,String postUserOrCircle,String postAnonymous){

        List<NameValuePair> linkPostUploadsend = new ArrayList<NameValuePair>(1);
        linkPostUploadsend.add(new BasicNameValuePair("usersession",usersessionid));
        linkPostUploadsend.add(new BasicNameValuePair("linkuploadpost",linkPost));
        linkPostUploadsend.add(new BasicNameValuePair("linkuploadpostdescription", linkPostDesc));
        linkPostUploadsend.add(new BasicNameValuePair("postinuserorcircle", postUserOrCircle));
        linkPostUploadsend.add(new BasicNameValuePair("userlinkpostanonymous",postAnonymous));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_uploadlinkpost.php");
            httpPost.setEntity(new UrlEncodedFormEntity(linkPostUploadsend));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }
}
