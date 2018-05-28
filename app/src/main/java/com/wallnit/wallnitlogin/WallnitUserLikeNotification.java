package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
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
import com.wallnit.wallnitlogin.WallnitUserLikeNotificationGet.UserLikeNotificationFeedItem;
import com.wallnit.wallnitlogin.WallnitUserLikeNotificationGet.UserLikeNotificationFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitUserLikeNotification extends AppCompatActivity {

    Session session;
    String sessionUserId;
    private static final String TAG = WallnitUserLikeNotification.class.getSimpleName();
    private ListView listView;
    private ProgressBar progressBar;
    private UserLikeNotificationFeedListAdapter listAdapter;
    private List<UserLikeNotificationFeedItem> userLikeNotificationFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_user_like_notification.php?signup_userid=";
    ConnectionDetector cd;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_like_notification);
        session = new Session(this);
        sessionUserId = session.getUserSession();

        cd = new ConnectionDetector(this);

        listView = (ListView) findViewById(R.id.listNotificationLike);
        progressBar = (ProgressBar) findViewById(R.id.user_notificationLike_progressBar);

        userLikeNotificationFeedItems = new ArrayList<UserLikeNotificationFeedItem>();

        listAdapter = new UserLikeNotificationFeedListAdapter(this, userLikeNotificationFeedItems);
        listView.setAdapter(listAdapter);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(sessionUserId));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(sessionUserId), null, new Response.Listener<JSONObject>() {

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

                UserLikeNotificationFeedItem item = new UserLikeNotificationFeedItem();

                item.setUsername(feedObj.getString("post_username"));
                item.setDateTime(feedObj.getString("post_datetime"));
                item.setProfileImg(feedObj.getString("post_profile_image"));
                item.setNumLike(feedObj.getString("post_numlike"));


                userLikeNotificationFeedItems.add(item);
            }
            listAdapter.notifyDataSetInvalidated();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

}
