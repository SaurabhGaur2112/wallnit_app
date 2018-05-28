package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.wallnit.wallnitlogin.WallnitUserSuggestion.WallnitUserSuggestionFeedItem;
import com.wallnit.wallnitlogin.WallnitUserSuggestion.WallnitUserSuggestionFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitUserSuggestionList extends AppCompatActivity {

    String suggestion_userid;
    Session session;
    private static final String TAG = WallnitUserSuggestionList.class.getSimpleName();
    private ListView listView;
    private ProgressBar progressBar;
    private WallnitUserSuggestionFeedListAdapter listAdapter;
    private List<WallnitUserSuggestionFeedItem> wallnitUserSuggestionFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_user_search_suggestion.php?userid=";
    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_suggestion_list);

        session = new Session(this);
        suggestion_userid = session.getUserSession();
        cd = new ConnectionDetector(this);

        listView = (ListView) findViewById(R.id.userSuggestion_listView);
        progressBar = (ProgressBar) findViewById(R.id.suggestionView_progressbar);

        wallnitUserSuggestionFeedItems = new ArrayList<WallnitUserSuggestionFeedItem>();

        listAdapter = new WallnitUserSuggestionFeedListAdapter(this, wallnitUserSuggestionFeedItems);
        listView.setAdapter(listAdapter);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(suggestion_userid));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(suggestion_userid), null, new Response.Listener<JSONObject>() {

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

                WallnitUserSuggestionFeedItem item = new WallnitUserSuggestionFeedItem();

                item.setUserSuggestionUserid(feedObj.getString("suggestion_user_id"));
                item.setUserSuggestionUsername(feedObj.getString("suggestion_username"));
                String tagname = feedObj.getString("suggestion_hashtagname");

                if(tagname.equals(""))
                {
                    tagname = "null";
                    item.setUserSuggestionTagname(tagname);
                } else {
                    item.setUserSuggestionTagname(tagname);
                }

                item.setUserSuggestionProfile(feedObj.getString("suggestion_profile_image"));

                wallnitUserSuggestionFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
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
                Intent searchUserName = new Intent(getApplicationContext(),WallnitAndroidUserSearch.class);
                searchUserName.putExtra("user_search_value",s);
                startActivity(searchUserName);
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
