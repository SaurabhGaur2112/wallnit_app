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
import com.wallnit.wallnitlogin.WallnitUserFollowing.WallnitFollowingFeedItem;
import com.wallnit.wallnitlogin.WallnitUserFollowing.WallnitFollowingFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitUserFollowingList extends AppCompatActivity {

    String userid_get_following;

    private static final String TAG = WallnitUserFollowingList.class.getSimpleName();
    private ListView listView;
    private ProgressBar progressBar;
    private WallnitFollowingFeedListAdapter listAdapter;
    private List<WallnitFollowingFeedItem> wallnitFollowingFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_user_following.php?follwingid=";
    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_following_list);

        listView = (ListView) findViewById(R.id.listView_following);
        progressBar = (ProgressBar) findViewById(R.id.following_progressbar);
        cd = new ConnectionDetector(this);

        wallnitFollowingFeedItems = new ArrayList<WallnitFollowingFeedItem>();

        listAdapter = new WallnitFollowingFeedListAdapter(this, wallnitFollowingFeedItems);
        listView.setAdapter(listAdapter);

        Intent followingid = getIntent();
        userid_get_following = followingid.getStringExtra("following_user_id");

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(userid_get_following));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(userid_get_following), null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        progressBar.setVisibility(View.GONE);
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
                        progressBar.setVisibility(View.GONE);
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

                WallnitFollowingFeedItem item = new WallnitFollowingFeedItem();

                item.setFollowing_userid(feedObj.getString("following_user_id"));
                item.setFollowing_username(feedObj.getString("following_username"));
                String hashTagname = feedObj.getString("following_hashtagname");

                if(hashTagname.equals(""))
                {
                    hashTagname = "null";
                    item.setFollowing_tagname(hashTagname);
                } else {
                    item.setFollowing_tagname(hashTagname);
                }

                item.setFollowing_profile(feedObj.getString("following_profile_image"));

                wallnitFollowingFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }



}
