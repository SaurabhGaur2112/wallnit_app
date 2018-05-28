package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wallnit.wallnitlogin.WallnitCacheFile.AppController;
import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitUserFollower.WallnitFollowerFeedItem;
import com.wallnit.wallnitlogin.WallnitUserFollower.WallnitFollowerFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitUserFollowerList extends AppCompatActivity {

    String userid_get_follower;
    private static final String TAG = WallnitUserFollowerList.class.getSimpleName();
    private ListView listViewFollower;
    private WallnitFollowerFeedListAdapter listAdapter;
    private List<WallnitFollowerFeedItem> wallnitFollowerFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_user_follower.php?follwerid=";
    ProgressBar followerProgress;
    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_follower_list);

        cd = new ConnectionDetector(this);
        listViewFollower = (ListView) findViewById(R.id.listViewFollower);
        followerProgress = (ProgressBar) findViewById(R.id.follower_progressbar);

        wallnitFollowerFeedItems = new ArrayList<WallnitFollowerFeedItem>();

        listAdapter = new WallnitFollowerFeedListAdapter(this, wallnitFollowerFeedItems);
        listViewFollower.setAdapter(listAdapter);

        Intent followerid = getIntent();
        userid_get_follower = followerid.getStringExtra("follower_user_id");

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(userid_get_follower));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(userid_get_follower), null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                        if (response != null) {
                        followerProgress.setVisibility(View.GONE);
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
        } else  {
            if (entry != null) {
                try {
                    String data = new String(entry.data, "UTF-8");
                    try {
                        followerProgress.setVisibility(View.GONE);
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

                WallnitFollowerFeedItem item = new WallnitFollowerFeedItem();

                item.setFollower_userid(feedObj.getString("follower_user_id"));
                item.setFollower_username(feedObj.getString("follower_username"));
                String tagname = feedObj.getString("follower_hashtagname");

                if(tagname.equals(""))
                {
                    tagname = "null";
                    item.setFollower_tagname(tagname);
                } else {
                    item.setFollower_tagname(tagname);
                }

                item.setFollower_profile(feedObj.getString("follower_profile_image"));

                wallnitFollowerFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }




}
