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
import com.wallnit.wallnitlogin.WallnitUserSearch.WallnitUserSearchFeedItem;
import com.wallnit.wallnitlogin.WallnitUserSearch.WallnitUserSearchFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitAndroidUserSearch extends AppCompatActivity {

    String valueSearch;
    private static final String TAG = WallnitAndroidUserSearch.class.getSimpleName();
    private ListView listView;
    private ProgressBar wallProgress;
    private WallnitUserSearchFeedListAdapter listAdapter;
    private List<WallnitUserSearchFeedItem> wallnitUserSearchFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_user_search.php?search_value=";
    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_android_user_search);

        Intent getSearchValue = getIntent();
        valueSearch = getSearchValue.getStringExtra("user_search_value");

        listView = (ListView) findViewById(R.id.listViewUserSearch);
        wallProgress = (ProgressBar) findViewById(R.id.searchView_progressbar);
        cd = new ConnectionDetector(this);

        wallnitUserSearchFeedItems = new ArrayList<WallnitUserSearchFeedItem>();

        listAdapter = new WallnitUserSearchFeedListAdapter(this, wallnitUserSearchFeedItems);
        listView.setAdapter(listAdapter);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(valueSearch));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(valueSearch), null, new Response.Listener<JSONObject>() {

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
            Toast.makeText(WallnitAndroidUserSearch.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void parseJsonFeed(JSONObject response){
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for(int i=0; i<feedArray.length(); i++){
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                WallnitUserSearchFeedItem item = new WallnitUserSearchFeedItem();

                item.setSearchUserUserId(feedObj.getString("search_user_id"));
                item.setSearchUserUsername(feedObj.getString("search_username"));

                String tagname = feedObj.getString("search_hashtagname");

                if(tagname.equals(""))
                {
                    tagname = "null";
                    item.setSearchUserTagname(tagname);
                } else {
                    item.setSearchUserTagname(tagname);
                }

                item.setSearchUserProfile(feedObj.getString("search_profile_image"));

                wallnitUserSearchFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
