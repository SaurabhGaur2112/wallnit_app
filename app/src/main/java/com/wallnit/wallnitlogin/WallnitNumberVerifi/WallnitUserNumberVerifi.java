package com.wallnit.wallnitlogin.WallnitNumberVerifi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Saurabh Gaur on 1/29/2017.
 */
public class WallnitUserNumberVerifi {

    InputStream is = null;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;
    String verificode;
    public String userNumVerifi(String usersessionid,String countrycodeid,String phonenumberid)
    {
        List<NameValuePair> nameSendNumber = new ArrayList<NameValuePair>(1);
        nameSendNumber.add(new BasicNameValuePair("webusersession",usersessionid));
        nameSendNumber.add(new BasicNameValuePair("webcountrycode",countrycodeid));
        nameSendNumber.add(new BasicNameValuePair("webmobilenumber",phonenumberid));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_confirmation.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameSendNumber));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch (Exception e){

        }

        List<NameValuePair> codeSendValue = new ArrayList<NameValuePair>(1);
        codeSendValue.add(new BasicNameValuePair("usersession",usersessionid));
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_verification.php");
            httpPost.setEntity(new UrlEncodedFormEntity(codeSendValue));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch (Exception e){

        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuffer sb = new StringBuffer();
            while((line = reader.readLine())!=null)
                sb.append(line+"\n");
            result = sb.toString();

            is.close();
        }catch (Exception e){

        }

        try{
            JSONArray jArray = new JSONArray(result);

            int count = jArray.length();

            for(int i=0;i<count;i++){
                JSONObject json_data = jArray.getJSONObject(i);
                temp += json_data.getString("user_account_code_send_email_phone")+":";
            }

            arr = temp.split(":");
            verificode = Arrays.toString(arr).replace("[","").replace("]","");

        }catch (Exception e){

        }
        return verificode;
    }
}
