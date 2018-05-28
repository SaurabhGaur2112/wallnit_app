package com.wallnit.wallnitlogin.WallnitCircleBackgroundUpdate;

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
 * Created by Saurabh Gaur on 12/5/2016.
 */
public class WallnitCircleColorImageThemeUpdate {

    InputStream is = null;
    public void circleColorThemeUpdate(String circleid,String colorTheme)
    {
        List<NameValuePair> sendCircleId = new ArrayList<NameValuePair>(1);
        sendCircleId.add(new BasicNameValuePair("webcirclesession", circleid));
        sendCircleId.add(new BasicNameValuePair("webcirclecolortheme", colorTheme));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_circle_color_theme_update.php");
            httpPost.setEntity(new UrlEncodedFormEntity(sendCircleId));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
    }
}
