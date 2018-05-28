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
import com.wallnit.wallnitlogin.WallnitUserMember.WallnitMemberFeedItem;
import com.wallnit.wallnitlogin.WallnitUserMember.WallnitMemberFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class WallnitCircleMemberList extends AppCompatActivity {

    String circleIdGet;
    private static final String TAG = WallnitCircleMemberList.class.getSimpleName();
    private ListView listView;
    private WallnitMemberFeedListAdapter listAdapter;
    private List<WallnitMemberFeedItem> wallnitMemberFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_circle_member.php?circle_id=";
    private ProgressBar wallProgress;
    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_member_list);

        Intent getIntentMember = getIntent();
        circleIdGet = getIntentMember.getStringExtra("member_circleid");

        cd = new ConnectionDetector(this);

        listView = (ListView) findViewById(R.id.memberlistView);
        wallProgress = (ProgressBar) findViewById(R.id.circleMember_progressbar);

        wallnitMemberFeedItems = new ArrayList<WallnitMemberFeedItem>();

        listAdapter = new WallnitMemberFeedListAdapter(this, wallnitMemberFeedItems);
        listView.setAdapter(listAdapter);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(circleIdGet));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(circleIdGet), null, new Response.Listener<JSONObject>() {

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

                WallnitMemberFeedItem item = new WallnitMemberFeedItem();

                item.setMember_userid(feedObj.getString("member_user_id"));
                item.setMember_username(feedObj.getString("member_username"));
                String tagname = feedObj.getString("member_hashtagname");

                if(tagname.equals(""))
                {
                    tagname = "null";
                    item.setMember_tagname(tagname);
                } else {
                    item.setMember_tagname(tagname);
                }

                item.setMember_profile(feedObj.getString("member_profile_image"));

                wallnitMemberFeedItems.add(item);
            }
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

}
