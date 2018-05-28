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
import com.wallnit.wallnitlogin.WallnitCircleSearch.WallnitCircleSearchFeedItem;
import com.wallnit.wallnitlogin.WallnitCircleSearch.WallnitCircleSearchFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitAndroidCircleSearch extends AppCompatActivity {

    String valueSearch;
    private static final String TAG = WallnitAndroidCircleSearch.class.getSimpleName();
    private ListView listView;
    private ProgressBar progressBar;
    private WallnitCircleSearchFeedListAdapter listAdapter;
    private List<WallnitCircleSearchFeedItem> wallnitCircleSearchFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_circle_search.php?search_value=";
    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_android_circle_search);

        Intent getSearchValue = getIntent();
        valueSearch = getSearchValue.getStringExtra("circle_search_value");
        cd = new ConnectionDetector(this);

        listView = (ListView) findViewById(R.id.circlesearchListView);
        progressBar = (ProgressBar) findViewById(R.id.circlesearchView_progressbar);

        wallnitCircleSearchFeedItems = new ArrayList<WallnitCircleSearchFeedItem>();

        listAdapter = new WallnitCircleSearchFeedListAdapter(this, wallnitCircleSearchFeedItems);
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

                WallnitCircleSearchFeedItem item = new WallnitCircleSearchFeedItem();

                item.setCircleSearchCircleId(feedObj.getString("search_circle_id"));
                item.setCircleSearchCirclename(feedObj.getString("search_circlename"));
                item.setCircleSearchProfile(feedObj.getString("search_profile_image"));

                wallnitCircleSearchFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
