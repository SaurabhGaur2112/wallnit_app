package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wallnit.wallnitlogin.WallnitCacheFile.AppController;
import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitUserPostLike.WallnitPostLikeFeedItem;
import com.wallnit.wallnitlogin.WallnitUserPostLike.WallnitPostLikeFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitUserPostLikeList extends AppCompatActivity {

    String postNumber;
    private static final String TAG = WallnitUserPostLikeList.class.getSimpleName();
    private ListView listView;
    private WallnitPostLikeFeedListAdapter listAdapter;
    private List<WallnitPostLikeFeedItem> wallnitPostLikeFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_user_likelist.php?user_postlike_number=";
    private ProgressBar wallProgress;
    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_post_like_list);

        cd = new ConnectionDetector(this);
        Intent getnumber = getIntent();
        postNumber = getnumber.getStringExtra("likelist_number_post");

        listView = (ListView) findViewById(R.id.likeListView);
        wallProgress = (ProgressBar) findViewById(R.id.like_progressbar);

        wallnitPostLikeFeedItems = new ArrayList<WallnitPostLikeFeedItem>();

        listAdapter = new WallnitPostLikeFeedListAdapter(this, wallnitPostLikeFeedItems);
        listView.setAdapter(listAdapter);


        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(postNumber));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(postNumber), null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        wallProgress.setVisibility(View.GONE);
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
                        wallProgress.setVisibility(View.GONE);
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

                WallnitPostLikeFeedItem item = new WallnitPostLikeFeedItem();

                item.setPostlike_userid(feedObj.getString("likelist_user_id"));
                item.setPostlike_username(feedObj.getString("likelist_username"));
                String tagname = feedObj.getString("likelist_hashtagname");

                if(tagname.equals(""))
                {
                    tagname = "null";
                    item.setPostlike_tagname(tagname);
                } else {
                    item.setPostlike_tagname(tagname);
                }

                item.setPostlike_profile(feedObj.getString("likelist_profile_image"));


                wallnitPostLikeFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }


}
