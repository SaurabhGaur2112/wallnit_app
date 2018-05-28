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
public class WallnitPostCircleQuote {

    InputStream is = null;

    public void insertCircleQuotePost(String circlesesionid,String quoteWrite){
        List<NameValuePair> textPostUpload = new ArrayList<NameValuePair>(1);
        textPostUpload.add(new BasicNameValuePair("circleid",circlesesionid));
        textPostUpload.add(new BasicNameValuePair("circle_quote",quoteWrite));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_uploadown_quote.php");
            httpPost.setEntity(new UrlEncodedFormEntity(textPostUpload));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }
}
