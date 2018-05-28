package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
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
import com.wallnit.wallnitlogin.WallnitCircleOwnProfilePost.WallnitCircleFeedItem;
import com.wallnit.wallnitlogin.WallnitCircleOwnProfilePost.WallnitCircleFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WallnitCircleOwnOtherPost extends AppCompatActivity {

    private static final String TAG = WallnitCircleOwnOtherPost.class.getSimpleName();
    private ListView listView;
    private WallnitCircleFeedListAdapter wallnitCircleFeedListAdapter;
    private List<WallnitCircleFeedItem> wallnitCircleFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_circleown_newsfeed_post.php?circleid=";
    private String URL_SEND_TYPE = "&circletype=";
    private String URL_SEND_USERID = "&userid=";
    private String URL_SEND_CIRCLEOTHERID = "&circleotherid=";
    ProgressBar wallProgress;
    Session session;
    String circleOwnId,circleOwnType,userOwnId,circleOtherId;
    ConnectionDetector cd;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_own_other_post);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        session = new Session(this);
        userOwnId = session.getUserSession();
        circleOtherId = session.getCircleSession();
        cd = new ConnectionDetector(this);

        Intent getvalueIntent = getIntent();
        Bundle extras = getvalueIntent.getExtras();
        circleOwnId = extras.getString("circleId");
        circleOwnType = extras.getString("circleType");

        listView = (ListView) findViewById(R.id.listView_circlepost);
        wallProgress = (ProgressBar) findViewById(R.id.circlepost_progressBar);

        wallnitCircleFeedItems = new ArrayList<WallnitCircleFeedItem>();

        wallnitCircleFeedListAdapter = new WallnitCircleFeedListAdapter(this, wallnitCircleFeedItems);
        listView.setAdapter(wallnitCircleFeedListAdapter);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(circleOwnId)+ URL_SEND_TYPE + String.valueOf(circleOwnType) + URL_SEND_USERID + String.valueOf(userOwnId) + URL_SEND_CIRCLEOTHERID + String.valueOf(circleOtherId));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(circleOwnId)+ URL_SEND_TYPE + String.valueOf(circleOwnType) + URL_SEND_USERID + String.valueOf(userOwnId) + URL_SEND_CIRCLEOTHERID + String.valueOf(circleOtherId), null, new Response.Listener<JSONObject>() {

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

                WallnitCircleFeedItem item = new WallnitCircleFeedItem();

                item.setCirclePostNumber(feedObj.getString("circle_post_number"));
                item.setCirclePostCircleUser(feedObj.getString("circle_post_circle_user"));
                item.setCirclename(feedObj.getString("circle_post_circlename"));
                item.setCircleProfile(feedObj.getString("circle_post_circle_profile"));
                item.setUsername(feedObj.getString("circle_post_username"));
                item.setTagname(feedObj.getString("circle_post_user_tagname"));
                item.setUserProfile(feedObj.getString("circle_post_user_profile_image"));
                item.setCirclePostAnonymoys(feedObj.getString("circle_post_post_anonymous"));
                String quotePost = feedObj.getString("circle_post_quote");

                if(quotePost.equals(""))
                {
                    quotePost = "null";
                    item.setCirclePostQuote(quotePost);
                } else {
                    item.setCirclePostQuote(quotePost);
                }

                item.setCirclePostBlogTitle(feedObj.getString("circle_post_blog_title"));
                String blogBody = feedObj.getString("circle_post_blog_body");

                if(blogBody.equals(""))
                {
                    blogBody = "null";
                    item.setCirclePostBlogBody(blogBody);
                } else {
                    item.setCirclePostBlogBody(blogBody);
                }

                item.setCirclePostFirstImage(feedObj.getString("circle_post_first_image"));
                item.setCirclePostSecondImage(feedObj.getString("circle_post_second_image"));
                item.setCirclePostThirdImage(feedObj.getString("circle_post_third_image"));
                item.setCirclePostFourthImage(feedObj.getString("circle_post_fourth_image"));
                item.setCirclePostFifthImage(feedObj.getString("circle_post_fifth_image"));
                item.setCirclePostSixthImage(feedObj.getString("circle_post_sixth_image"));
                item.setCirclePostSeventhImage(feedObj.getString("circle_post_seventh_image"));
                item.setCirclePostEighthImage(feedObj.getString("circle_post_eighth_image"));
                item.setCirclePostNinthImage(feedObj.getString("circle_post_ninth_image"));
                item.setCirclePostTenthImage(feedObj.getString("circle_post_tenth_image"));

                String firstImgNull = feedObj.getString("circle_post_first_image_null");
                String secondImgNull = feedObj.getString("circle_post_second_image_null");
                String thirdImgNull = feedObj.getString("circle_post_third_image_null");
                String fourthImgNull = feedObj.getString("circle_post_fourth_image_null");
                String fifthImgNull = feedObj.getString("circle_post_fifth_image_null");
                String sixthImgNull = feedObj.getString("circle_post_sixth_image_null");
                String seventhImgNull = feedObj.getString("circle_post_seventh_image_null");
                String eighthImgNull = feedObj.getString("circle_post_eighth_image_null");
                String ninthImgNull = feedObj.getString("circle_post_ninth_image_null");
                String tenthImgNull = feedObj.getString("circle_post_tenth_image_null");

                if(firstImgNull.equals(""))
                {
                    firstImgNull = "null";
                    item.setCirclePostFirstImageNull(firstImgNull);
                } else {
                    item.setCirclePostFirstImageNull(firstImgNull);
                }

                if(secondImgNull.equals(""))
                {
                    secondImgNull = "null";
                    item.setCirclePostSecondImageNull(secondImgNull);
                } else {
                    item.setCirclePostSecondImageNull(secondImgNull);
                }

                if(thirdImgNull.equals(""))
                {
                    thirdImgNull = "null";
                    item.setCirclePostThirdImageNull(thirdImgNull);
                } else {
                    item.setCirclePostThirdImageNull(thirdImgNull);
                }

                if(fourthImgNull.equals(""))
                {
                    fourthImgNull = "null";
                    item.setCirclePostFourthImageNull(fourthImgNull);
                } else {
                    item.setCirclePostFourthImageNull(fourthImgNull);
                }

                if(fifthImgNull.equals(""))
                {
                    fifthImgNull = "null";
                    item.setCirclePostFifthImageNull(fifthImgNull);
                } else {
                    item.setCirclePostFifthImageNull(fifthImgNull);
                }

                if(sixthImgNull.equals(""))
                {
                    sixthImgNull = "null";
                    item.setCirclePostSixthImageNull(sixthImgNull);
                } else {
                    item.setCirclePostSixthImageNull(sixthImgNull);
                }

                if(seventhImgNull.equals(""))
                {
                    seventhImgNull = "null";
                    item.setCirclePostSeventhImageNull(seventhImgNull);
                } else {
                    item.setCirclePostSeventhImageNull(seventhImgNull);
                }

                if(eighthImgNull.equals(""))
                {
                    eighthImgNull = "null";
                    item.setCirclePostEighthImageNull(eighthImgNull);
                } else {
                    item.setCirclePostEighthImageNull(eighthImgNull);
                }

                if(ninthImgNull.equals(""))
                {
                    ninthImgNull = "null";
                    item.setCirclePostNinthImageNull(ninthImgNull);
                } else {
                    item.setCirclePostNinthImageNull(ninthImgNull);
                }

                if(tenthImgNull.equals(""))
                {
                    tenthImgNull = "null";
                    item.setCirclePostTenthImageNull(tenthImgNull);
                } else {
                    item.setCirclePostTenthImageNull(tenthImgNull);
                }

                String txtCaption = feedObj.getString("circle_post_image_caption");

                if(txtCaption.equals(""))
                {
                    txtCaption = "null";
                    item.setCirclePostCaption(txtCaption);
                } else {
                    item.setCirclePostCaption(txtCaption);
                }

                item.setCirclePostTotalNumLike(feedObj.getString("circle_post_total_numlike"));
                item.setCirclePostLikeOrNot(feedObj.getString("circle_post_like_or_not"));
                item.setCircleOtherId(feedObj.getString("circle_post_otherid"));
                item.setCircleOwnId(feedObj.getString("circle_post_ownid"));
                item.setCirclePostUserOwnId(feedObj.getString("circle_post_userownid"));

                wallnitCircleFeedItems.add(item);
            }

            wallnitCircleFeedListAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
