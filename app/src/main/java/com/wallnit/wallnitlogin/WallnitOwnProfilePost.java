package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wallnit.wallnitlogin.WallnitCacheFile.AppController;
import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitUserOwnProfilePost.WallnitProfileFeedItem;
import com.wallnit.wallnitlogin.WallnitUserOwnProfilePost.WallnitProfileFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitOwnProfilePost extends AppCompatActivity {

    private static final String TAG = WallnitOwnProfilePost.class.getSimpleName();
    private ListView listView;
    private WallnitProfileFeedListAdapter listAdapter;
    private List<WallnitProfileFeedItem> wallnitProfileFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_ownprofile_getpost.php?userid=";
    private String URL_SEND_ID = "&other_userid=";
    private ProgressBar profileProgress;
    String ownUserId,otherUserId;
    Session session;
    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_own_profile_post);

        listView = (ListView) findViewById(R.id.listView_profilepost);
        profileProgress = (ProgressBar) findViewById(R.id.profile_progressBar);
        wallnitProfileFeedItems = new ArrayList<WallnitProfileFeedItem>();
        session = new Session(this);

        cd = new ConnectionDetector(this);

        Intent getIntentId = getIntent();
        ownUserId = session.getUserSession();
        otherUserId = getIntentId.getStringExtra("profile_post_user_id");

        listAdapter = new WallnitProfileFeedListAdapter(this, wallnitProfileFeedItems);
        listView.setAdapter(listAdapter);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(ownUserId)+URL_SEND_ID + String.valueOf(otherUserId));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(ownUserId)+URL_SEND_ID + String.valueOf(otherUserId), null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        profileProgress.setVisibility(View.GONE);
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            AppController.getInstance().addToRequestQueue(jsonReq);
        } else {
            if (entry != null) {
                try {
                    String data = new String(entry.data, "UTF-8");
                    try {
                        profileProgress.setVisibility(View.GONE);
                        parseJsonFeed(new JSONObject(data));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void parseJsonFeed(JSONObject response){
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for(int i=0; i<feedArray.length(); i++){
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                WallnitProfileFeedItem item = new WallnitProfileFeedItem();

                item.setTextPost(feedObj.getString("post_text"));
                item.setLinkPost(feedObj.getString("post_link"));
                item.setLinkDescPost(feedObj.getString("post_link_desc"));
                item.setBlogPostTitle(feedObj.getString("post_blog_title"));
                item.setBlogPostBody(feedObj.getString("post_blog_body"));

                item.setFirstImage(feedObj.getString("post_first_image"));
                item.setSecondImage(feedObj.getString("post_second_image"));
                item.setThirdImage(feedObj.getString("post_third_image"));
                item.setFourthImage(feedObj.getString("post_fourth_image"));
                item.setFifthImage(feedObj.getString("post_fifth_image"));
                item.setSixthImage(feedObj.getString("post_sixth_image"));
                item.setSeventhImage(feedObj.getString("post_seventh_image"));
                item.setEighthImage(feedObj.getString("post_eighth_image"));
                item.setNinthImage(feedObj.getString("post_ninth_image"));
                item.setTenthImage(feedObj.getString("post_tenth_image"));

                String firstImageNull = feedObj.getString("post_first_image_null");
                String secondImageNull = feedObj.getString("post_second_image_null");
                String thirdImageNull = feedObj.getString("post_third_image_null");
                String fourthImageNull = feedObj.getString("post_fourth_image_null");
                String fifthImageNull = feedObj.getString("post_fifth_image_null");
                String sixthImageNull = feedObj.getString("post_sixth_image_null");
                String seventhImageNull = feedObj.getString("post_seventh_image_null");
                String eighthImageNull = feedObj.getString("post_eighth_image_null");
                String ninthImageNull = feedObj.getString("post_ninth_image_null");
                String tenthImageNull = feedObj.getString("post_tenth_image_null");

                String imageCaption = feedObj.getString("post_image_caption");

                if(firstImageNull.equals(""))
                {
                    firstImageNull = "null";
                    item.setFirstImageNull(firstImageNull);
                } else {
                    item.setFirstImageNull(firstImageNull);
                }

                if(secondImageNull.equals(""))
                {
                    secondImageNull = "null";
                    item.setSecondImageNull(secondImageNull);
                } else {
                    item.setSecondImageNull(secondImageNull);
                }

                if(thirdImageNull.equals(""))
                {
                    thirdImageNull = "null";
                    item.setThirdImageNull(thirdImageNull);
                } else {
                    item.setThirdImageNull(thirdImageNull);
                }

                if(fourthImageNull.equals(""))
                {
                    fourthImageNull = "null";
                    item.setFourthImageNull(fourthImageNull);
                } else {
                    item.setFourthImageNull(fourthImageNull);
                }

                if(fifthImageNull.equals(""))
                {
                    fifthImageNull = "null";
                    item.setFifthImageNull(fifthImageNull);
                } else {
                    item.setFifthImageNull(fifthImageNull);
                }

                if(sixthImageNull.equals(""))
                {
                    sixthImageNull = "null";
                    item.setSixthImageNull(sixthImageNull);
                } else {
                    item.setSixthImageNull(sixthImageNull);
                }

                if(seventhImageNull.equals(""))
                {
                    seventhImageNull = "null";
                    item.setSeventhImageNull(seventhImageNull);
                } else {
                    item.setSeventhImageNull(seventhImageNull);
                }

                if(eighthImageNull.equals(""))
                {
                    eighthImageNull = "null";
                    item.setEighthImageNull(eighthImageNull);
                } else {
                    item.setEighthImageNull(eighthImageNull);
                }

                if(ninthImageNull.equals(""))
                {
                    ninthImageNull = "null";
                    item.setNinthImageNull(ninthImageNull);
                } else {
                    item.setNinthImageNull(ninthImageNull);
                }

                if(tenthImageNull.equals(""))
                {
                    tenthImageNull = "null";
                    item.setTenthImageNull(tenthImageNull);
                } else {
                    item.setTenthImageNull(tenthImageNull);
                }

                if(imageCaption.equals(""))
                {
                    imageCaption = "null";
                    item.setImageCaption(imageCaption);
                } else {
                    item.setImageCaption(imageCaption);
                }

                item.setTotalNumberPostLike(feedObj.getString("total_numlike"));
                item.setPostLikeOrNot(feedObj.getString("post_youlike_not"));
                item.setOwnuserid(feedObj.getString("user_ownid"));
                item.setOtheruserid(feedObj.getString("user_otherid"));
                item.setPostNumber(feedObj.getString("post_number"));


                wallnitProfileFeedItems.add(item);
            }

            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
