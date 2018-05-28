package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wallnit.wallnitlogin.WallnitCacheFile.AppController;
import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitImageFindExtension.ImageExtensionGet;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;
import com.wallnit.wallnitlogin.WallnitUserBackgroundImageGet.WallnitUserBackgroundGet;
import com.wallnit.wallnitlogin.WallnitUserNotificationGet.WallnitUserNotificationArrivedOrNot;
import com.wallnit.wallnitlogin.WallnitUserWallPost.WallUserFeedItem;
import com.wallnit.wallnitlogin.WallnitUserWallPost.WallUserFeedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Wall extends AppCompatActivity {

    private static final String TAG = Wall.class.getSimpleName();
    private ListView wallListView;
    private WallUserFeedListAdapter listAdapter;
    private List<WallUserFeedItem> wallUserFeedItems;
    private String URL_FEED = "http://www.wallnit.com/wallnit_android/wallnit_android_user_newsfeed_showpost.php?userid=";
    ProgressBar wallProgress;

    FloatingActionButton fab_uploadoption,fab_uploadlink,fab_uploadstatus,fab_uploadblog,fab_uploadphotos,fab_uploadtext;
    Animation FabOpen,FabClose,FabRClockwise,FabRanticlockwise;
    boolean isOpen = false;
    ImageView home_icon,select_home_icon,backgroundIcon,searchIcon,moreIcon;
    ImageView notificationIcon,notificationArrivedIcon;

    Session session;
    String sessionUserid;

    String getNotifi;

    WallnitUserNotificationArrivedOrNot wallnitUserNotificationArrivedOrNot;

    ImageView profile_backgroundimage;
    WebView profile_background_gifimage;
    String getBackground,background_imageType;

    WallnitUserBackgroundGet wallnitUserBackgroundGet;
    ImageExtensionGet imageExtensionGet;

    private static final String SERVER_ADDRESS = "http://www.wallnit.com/";
    SwipeRefreshLayout refreshLayout;
    ConnectionDetector cd;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wall);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        session = new Session(this);
        sessionUserid = session.getUserSession();

        cd = new ConnectionDetector(this);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.wall_swipeRefresh);
        profile_backgroundimage = (ImageView) findViewById(R.id.wall_backgroundimage_set);
        profile_background_gifimage = (WebView) findViewById(R.id.wall_backgroundimage_gif_set);

        wallnitUserBackgroundGet = new WallnitUserBackgroundGet();
        wallnitUserNotificationArrivedOrNot = new WallnitUserNotificationArrivedOrNot();

        if(cd.isConnected())
        {
            getBackground = wallnitUserBackgroundGet.userBackground(sessionUserid);
            getNotifi = wallnitUserNotificationArrivedOrNot.userNotificationGet(sessionUserid);

        } else {
            getBackground = "color1/color1.jpg";
            getNotifi = "not arrived";
            Toast.makeText(Wall.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }

        imageExtensionGet = new ImageExtensionGet();

        background_imageType = imageExtensionGet.getImageName(getBackground);

        if(background_imageType.equals("jpg") || background_imageType.equals("png") || background_imageType.equals("jpeg"))
        {
            profile_backgroundimage.setVisibility(View.VISIBLE);
            if(getBackground.equals("color1/color1.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_first);
            }
            else if(getBackground.equals("color2/color2.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_second);
            }
            else if(getBackground.equals("color3/color3.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_third);
            }
            else if(getBackground.equals("color4/color4.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_fourth);
            }
            else if(getBackground.equals("color5/color5.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_fifth);
            }
            else if(getBackground.equals("color6/color6.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_sixth);
            }
            else if(getBackground.equals("color7/color7.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_seventh);
            }
            else if(getBackground.equals("color8/color8.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_eighth);
            }
            else if(getBackground.equals("color9/color9.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_ninth);
            }
            else if(getBackground.equals("color10/color10.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_tenth);
            }
            else if(getBackground.equals("color11/color11.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_eleventh);
            }
            else if(getBackground.equals("color12/color12.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_twelth);
            }
            else if(getBackground.equals("color13/color13.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_thirteen);
            }
            else if(getBackground.equals("theme1/theme1 main.jpg"))
            {
                String backgroundImage = "first_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme2/theme2 main.jpg"))
            {
                String backgroundImage = "second_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme3/theme3 main.jpg"))
            {
                String backgroundImage = "third_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme4/theme4 main.jpg"))
            {
                String backgroundImage = "fourth_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme5/theme5 main.jpg"))
            {
                String backgroundImage = "fifth_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme6/theme6 main.jpg"))
            {
                String backgroundImage = "sixth_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme7/theme7 main.jpg"))
            {
                String backgroundImage = "seventh_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme8/theme8 main.jpg"))
            {
                String backgroundImage = "eighth_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme9/theme9 main.jpg"))
            {
                String backgroundImage = "ninth_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme10/theme10 main.jpg"))
            {
                String backgroundImage = "tenth_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme11/theme11 main.jpg"))
            {
                String backgroundImage = "eleventh_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme12/theme12 main.jpg"))
            {
                String backgroundImage = "twelth_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme13/theme13 main.jpg"))
            {
                String backgroundImage = "thirteen_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme14/theme14 main.jpg"))
            {
                String backgroundImage = "fourteen_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme15/theme15 main.jpg"))
            {
                String backgroundImage = "fifteen_background.jpg";
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else
            {
                PicassoClient.downloadImgae(Wall.this,SERVER_ADDRESS + "upload_user_image/" + getBackground,profile_backgroundimage);
            }
        }
        if(background_imageType.equals("gif"))
        {
            assert profile_background_gifimage != null;
            profile_background_gifimage.setVisibility(View.VISIBLE);
            profile_background_gifimage.loadUrl(SERVER_ADDRESS + "wallnit_android/wallnit_android_user_backgroundimage_set.php?backgroundimage="+getBackground);
        }

        fab_uploadoption=(FloatingActionButton) findViewById(R.id.fab_user_all_uploadoption);
        fab_uploadlink=(FloatingActionButton) findViewById(R.id.fab_user_link_uploadoption);
        fab_uploadstatus=(FloatingActionButton) findViewById(R.id.fab_user_status_uploadoption);
        fab_uploadblog=(FloatingActionButton) findViewById(R.id.fab_user_blog_uploadoption);
        fab_uploadphotos=(FloatingActionButton) findViewById(R.id.fab_user_photo_uploadoption);
        fab_uploadtext=(FloatingActionButton) findViewById(R.id.fab_user_text_uploadoption);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        home_icon = (ImageView) findViewById(R.id.homeicon);
        select_home_icon = (ImageView) findViewById(R.id.select_homeicon);
        notificationIcon = (ImageView) findViewById(R.id.notificationicon);
        notificationArrivedIcon = (ImageView) findViewById(R.id.notification_select_icon);
        backgroundIcon = (ImageView) findViewById(R.id.backgroundicon);
        searchIcon = (ImageView) findViewById(R.id.searchicon);
        moreIcon = (ImageView) findViewById(R.id.moreicon);



        home_icon.setVisibility(View.GONE);
        select_home_icon.setVisibility(View.VISIBLE);

        select_home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallListView.setSelectionAfterHeaderView();
            }
        });


        if(getNotifi.equals("not arrived"))
        {
            notificationIcon.setVisibility(View.VISIBLE);
            notificationArrivedIcon.setVisibility(View.GONE);
        }
        if(getNotifi.equals("arrived"))
        {
            notificationArrivedIcon.setVisibility(View.VISIBLE);
            notificationIcon.setVisibility(View.GONE);
        }


        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this,WallnitUserLikeNotification.class));
            }
        });


        notificationArrivedIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this,WallnitUserLikeNotification.class));
            }
        });


        backgroundIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this,WallnitUserBackgroundOption.class));
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this,WallnitUserSuggestionList.class));
            }
        });

        moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this,WallnitUserMoreOption.class));
            }
        });



        fab_uploadoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isOpen)
                {
                    fab_uploadlink.startAnimation(FabClose);
                    fab_uploadstatus.startAnimation(FabClose);
                    fab_uploadblog.startAnimation(FabClose);
                    fab_uploadphotos.startAnimation(FabClose);
                    fab_uploadtext.startAnimation(FabClose);
                    fab_uploadoption.startAnimation(FabRanticlockwise);
                    fab_uploadlink.setClickable(false);
                    fab_uploadstatus.setClickable(false);
                    fab_uploadblog.setClickable(false);
                    fab_uploadphotos.setClickable(false);
                    fab_uploadtext.setClickable(false);
                    isOpen = false;
                }
                else
                {
                    fab_uploadlink.startAnimation(FabOpen);
                    fab_uploadstatus.startAnimation(FabOpen);
                    fab_uploadblog.startAnimation(FabOpen);
                    fab_uploadphotos.startAnimation(FabOpen);
                    fab_uploadtext.startAnimation(FabOpen);
                    fab_uploadoption.startAnimation(FabRClockwise);
                    fab_uploadlink.setClickable(true);
                    fab_uploadstatus.setClickable(true);
                    fab_uploadblog.setClickable(true);
                    fab_uploadphotos.setClickable(true);
                    fab_uploadtext.setClickable(true);
                    isOpen = true;
                }
            }
        });

        fab_uploadtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this,WallnitUserUploadText.class));
            }
        });

        fab_uploadphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this, WallnitUserUploadPhotos.class));
            }
        });

        fab_uploadblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this,WallnitUserUploadBlog.class));
            }
        });

        fab_uploadstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this,WallnitUserUploadStatus.class));
            }
        });

        fab_uploadlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wall.this,WallnitUserUploadLink.class));
            }
        });


        wallListView = (ListView) findViewById(R.id.wallListView);
        wallProgress = (ProgressBar) findViewById(R.id.wall_progressBar);

        wallUserFeedItems = new ArrayList<WallUserFeedItem>();

        listAdapter = new WallUserFeedListAdapter(this, wallUserFeedItems,refreshLayout);
        wallListView.setAdapter(listAdapter);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + String.valueOf(sessionUserid));

        if(cd.isConnected())
        {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED + String.valueOf(sessionUserid), null, new Response.Listener<JSONObject>() {

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


    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                WallUserFeedItem wallUserFeedItem = new WallUserFeedItem();

                String postAnonymous = feedObj.getString("post_post_anonymous");

                if(postAnonymous.equals("0"))
                {
                    wallUserFeedItem.setPostUsername(feedObj.getString("post_username"));
                    String tagname = feedObj.getString("post_hashtagname");

                    if(tagname.equals(""))
                    {
                        tagname = "null";
                        wallUserFeedItem.setPostHashtagname(tagname);
                    } else {
                        wallUserFeedItem.setPostHashtagname(tagname);
                    }

                    wallUserFeedItem.setPostProfileImage(feedObj.getString("post_profile_image"));
                    wallUserFeedItem.setPostAnonymous("null");

                } else {
                    wallUserFeedItem.setPostUsername("Anonymous");
                    wallUserFeedItem.setPostHashtagname("null");
                    wallUserFeedItem.setPostAnonymous("anonymous");
                }

                wallUserFeedItem.setPostTextPost(feedObj.getString("post_text_post"));
                wallUserFeedItem.setPostQuote(feedObj.getString("post_quote"));
                wallUserFeedItem.setPostLink(feedObj.getString("post_link"));
                wallUserFeedItem.setPostLinkDesc(feedObj.getString("post_link_desc"));

                wallUserFeedItem.setPostBlogPostTitle(feedObj.getString("post_blog_post_title"));
                wallUserFeedItem.setPostBlogPostBody(feedObj.getString("post_blog_post_body"));

                wallUserFeedItem.setPostFirstImage(feedObj.getString("post_first_image"));
                wallUserFeedItem.setPostSecondImage(feedObj.getString("post_second_image"));
                wallUserFeedItem.setPostThirdImage(feedObj.getString("post_third_image"));
                wallUserFeedItem.setPostFourthImage(feedObj.getString("post_fourth_image"));
                wallUserFeedItem.setPostFifthImage(feedObj.getString("post_fifth_image"));
                wallUserFeedItem.setPostSixthImage(feedObj.getString("post_sixth_image"));
                wallUserFeedItem.setPostSeventhImage(feedObj.getString("post_seventh_image"));
                wallUserFeedItem.setPostEighthImage(feedObj.getString("post_eighth_image"));
                wallUserFeedItem.setPostNinthImage(feedObj.getString("post_ninth_image"));
                wallUserFeedItem.setPostTenthImage(feedObj.getString("post_tenth_image"));

                String firstImgNull = feedObj.getString("post_first_image_null");
                String secondImgNull = feedObj.getString("post_second_image_null");
                String thirdImgNull = feedObj.getString("post_third_image_null");
                String fourthImgNull = feedObj.getString("post_fourth_image_null");
                String fifthImgNull = feedObj.getString("post_fifth_image_null");
                String sixthImgNull = feedObj.getString("post_sixth_image_null");
                String seventhImgNull = feedObj.getString("post_seventh_image_null");
                String eighthImgNull = feedObj.getString("post_eighth_image_null");
                String ninthImgNull = feedObj.getString("post_ninth_image_null");
                String tenthImgNull = feedObj.getString("post_tenth_image_null");


                if(firstImgNull.equals(""))
                {
                    firstImgNull = "null";
                    wallUserFeedItem.setPostFirstImageNull(firstImgNull);
                } else {
                    wallUserFeedItem.setPostFirstImageNull(firstImgNull);
                }

                if(secondImgNull.equals(""))
                {
                    secondImgNull = "null";
                    wallUserFeedItem.setPostSecondImageNull(secondImgNull);
                } else {
                    wallUserFeedItem.setPostSecondImageNull(secondImgNull);
                }

                if(thirdImgNull.equals(""))
                {
                    thirdImgNull = "null";
                    wallUserFeedItem.setPostThirdImageNull(thirdImgNull);
                } else {
                    wallUserFeedItem.setPostThirdImageNull(thirdImgNull);
                }

                if(fourthImgNull.equals(""))
                {
                    fourthImgNull = "null";
                    wallUserFeedItem.setPostFourthImageNull(fourthImgNull);
                } else {
                    wallUserFeedItem.setPostFourthImageNull(fourthImgNull);
                }

                if(fifthImgNull.equals(""))
                {
                    fifthImgNull = "null";
                    wallUserFeedItem.setPostFifthImageNull(fifthImgNull);
                } else {
                    wallUserFeedItem.setPostFifthImageNull(fifthImgNull);
                }

                if(sixthImgNull.equals(""))
                {
                    sixthImgNull = "null";
                    wallUserFeedItem.setPostSixthImageNull(sixthImgNull);
                } else {
                    wallUserFeedItem.setPostSixthImageNull(sixthImgNull);
                }

                if(seventhImgNull.equals(""))
                {
                    seventhImgNull = "null";
                    wallUserFeedItem.setPostSeventhImageNull(seventhImgNull);
                } else {
                    wallUserFeedItem.setPostSeventhImageNull(seventhImgNull);
                }

                if(eighthImgNull.equals(""))
                {
                    eighthImgNull = "null";
                    wallUserFeedItem.setPostEighthImageNull(eighthImgNull);
                } else {
                    wallUserFeedItem.setPostEighthImageNull(eighthImgNull);
                }

                if(ninthImgNull.equals(""))
                {
                    ninthImgNull = "null";
                    wallUserFeedItem.setPostNinthImageNull(ninthImgNull);
                } else {
                    wallUserFeedItem.setPostNinthImageNull(ninthImgNull);
                }

                if(tenthImgNull.equals(""))
                {
                    tenthImgNull = "null";
                    wallUserFeedItem.setPostTenthImageNull(tenthImgNull);
                } else {
                    wallUserFeedItem.setPostTenthImageNull(tenthImgNull);
                }

                String txtCaption = feedObj.getString("post_image_caption");

                if(txtCaption.equals(""))
                {
                    wallUserFeedItem.setPostImageCaption("null");
                } else {
                    wallUserFeedItem.setPostImageCaption(txtCaption);
                }

                wallUserFeedItem.setPostTotalNumLike(feedObj.getString("post_total_numlike"));
                wallUserFeedItem.setPostYouLikeOrNot(feedObj.getString("post_post_youlike_not"));
                wallUserFeedItem.setPostOwnFollowCircle(feedObj.getString("post_own_follow_circle"));
                wallUserFeedItem.setPostNumber(feedObj.getString("post_number"));
                wallUserFeedItem.setPostOwnUserId(feedObj.getString("ownuserid"));
                wallUserFeedItem.setPostUserId(feedObj.getString("post_userid"));

                wallUserFeedItems.add(wallUserFeedItem);
            }

            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
