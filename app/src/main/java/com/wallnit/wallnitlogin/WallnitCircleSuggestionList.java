package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wallnit.wallnitlogin.WallnitCacheFile.AppController;
import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitCircleProfileFetchDetails.WallnitCircleProfileGetValues;
import com.wallnit.wallnitlogin.WallnitCircleSuggestion.WallnitCircleSuggestionFeedItem;
import com.wallnit.wallnitlogin.WallnitCircleSuggestion.WallnitCircleSuggestionFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitCircleSuggestionList extends AppCompatActivity {

    Session session;
    String circleid;

    WallnitCircleProfileGetValues wallnitCircleProfileGetValues;
    String circleCategory[];

    String suggestion_category;

    private static final String TAG = WallnitCircleSuggestionList.class.getSimpleName();
    private ListView listView;
    private ProgressBar progressBar;
    private WallnitCircleSuggestionFeedListAdapter listAdapter;
    private List<WallnitCircleSuggestionFeedItem> wallnitCircleSuggestionFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_circle_search_suggestion.php?circleid=";
    private String URL_SEND_CIRCLE_CATEGORY = "&circlecategory=";

    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_suggestion_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        session = new Session(this);
        circleid = session.getCircleSession();

        cd = new ConnectionDetector(this);

        circleCategory = wallnitCircleProfileGetValues.getvaluesCircleProfile(circleid);
        suggestion_category = circleCategory[2];

        listView = (ListView) findViewById(R.id.listView_circleSuggestion);
        progressBar = (ProgressBar) findViewById(R.id.circleSuggestionView_progressbar);

        wallnitCircleSuggestionFeedItems = new ArrayList<WallnitCircleSuggestionFeedItem>();

        listAdapter = new WallnitCircleSuggestionFeedListAdapter(this, wallnitCircleSuggestionFeedItems);
        listView.setAdapter(listAdapter);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(circleid) + URL_SEND_CIRCLE_CATEGORY + String.valueOf(suggestion_category));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(circleid) + URL_SEND_CIRCLE_CATEGORY + String.valueOf(suggestion_category), null, new Response.Listener<JSONObject>() {

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

                WallnitCircleSuggestionFeedItem item = new WallnitCircleSuggestionFeedItem();

                item.setCircleSuggestionCircleId(feedObj.getString("suggestion_circle_id"));
                item.setCircleSuggestionCirclename(feedObj.getString("suggestion_circlename"));
                item.setCircleSuggestionProfile(feedObj.getString("suggestion_profile_image"));

                wallnitCircleSuggestionFeedItems.add(item);

            }
            listAdapter.notifyDataSetChanged();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_search_menu, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent searchCircleName = new Intent(getApplicationContext(),WallnitAndroidCircleSearch.class);
                searchCircleName.putExtra("circle_search_value",s);
                startActivity(searchCircleName);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }
}
