package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitImageFindExtension.ImageExtensionGet;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;
import com.wallnit.wallnitlogin.WallnitUserFollowOrUnfollow.WallnitUserFollow;
import com.wallnit.wallnitlogin.WallnitUserFollowOrUnfollow.WallnitUserFollowUnfollowCheck;
import com.wallnit.wallnitlogin.WallnitUserFollowOrUnfollow.WallnitUserUnfollow;
import com.wallnit.wallnitlogin.WallnitUserProfileFetchDetails.WallnitUserProfileGetValues;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class WallnitOtherUserProfile extends AppCompatActivity {


    Session session;

    String ownCircleId,otherUserId,circleIdGet,ownUserId;
    String profile_imageFind;

    ImageView otherprofile_backgroundimage,otherprofile_imageset;
    WebView otherprofile_background_gifimage,otherprofile_gif_imageset;

    ImageExtensionGet imageExtensionGet;
    WallnitUserProfileGetValues wallnitUserProfileGetValues;
    String getBackground,background_imageType;
    private static final String SERVER_ADDRESS = "http://www.wallnit.com/";

    TextView userprofilename_txt,usertagname_txt,userstatus_txt,usergender_txt,userdob_txt,userplace_txt,userwork_txt,userdescription_txt;
    String get_details[];
    String username,tagname,profile_img,gender,month,day,year,place,work,user_desc,user_status,userpost_ornot;

    FrameLayout profile_frameLayout;
    TextView userotherFollwer,userotherFollower_number,userotherFollowing_number,userotherFollowing;
    ImageView followbtn,unfollowbtn;
    WallnitUserFollowUnfollowCheck wallnitUserFollowUnfollowCheck;
    WallnitUserFollow wallnitUserFollow;
    WallnitUserUnfollow wallnitUserUnfollow;

    String followernum,followingnum;
    Button seepost_btn;
    ConnectionDetector cd;
    ImageView home_icon,notificationIcon,backgroundIcon,searchIcon,moreIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_other_user_profile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        home_icon = (ImageView) findViewById(R.id.homeicon);
        notificationIcon = (ImageView) findViewById(R.id.notificationicon);
        backgroundIcon = (ImageView) findViewById(R.id.backgroundicon);
        searchIcon = (ImageView) findViewById(R.id.searchicon);
        moreIcon = (ImageView) findViewById(R.id.moreicon);


        cd = new ConnectionDetector(this);
        Intent getOtheridIntent = getIntent();
        otherUserId = getOtheridIntent.getStringExtra("otheruser_profile_userid");

        userotherFollwer = (TextView) findViewById(R.id.other_profileno_follower);
        userotherFollwer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followerIntent = new Intent(getApplicationContext(),WallnitUserFollowerList.class);
                followerIntent.putExtra("follower_user_id",otherUserId);
                startActivity(followerIntent);
            }
        });

        userotherFollower_number = (TextView) findViewById(R.id.other_profileno_follower_number);
        userotherFollower_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followerIntent = new Intent(getApplicationContext(),WallnitUserFollowerList.class);
                followerIntent.putExtra("follower_user_id",otherUserId);
                startActivity(followerIntent);
            }
        });

        userotherFollowing = (TextView) findViewById(R.id.other_profileno_following);
        userotherFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followingIntent = new Intent(getApplicationContext(),WallnitUserFollowingList.class);
                followingIntent.putExtra("following_user_id",otherUserId);
                startActivity(followingIntent);
            }
        });


        userotherFollowing_number = (TextView) findViewById(R.id.other_profileno_following_number);
        userotherFollowing_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followingIntent = new Intent(getApplicationContext(),WallnitUserFollowingList.class);
                followingIntent.putExtra("following_user_id",otherUserId);
                startActivity(followingIntent);
            }
        });


        session = new Session(this);
        ownCircleId = session.getCircleSession();
        ownUserId = session.getUserSession();

        if(ownCircleId.equals("circle_not_login"))
        {
            home_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,Wall.class));
                }
            });

            notificationIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,WallnitUserLikeNotification.class));
                }
            });

            backgroundIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,WallnitUserBackgroundOption.class));
                }
            });

            searchIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,WallnitUserSuggestionList.class));
                }
            });

            moreIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,WallnitUserMoreOption.class));
                }
            });
        } else {
            home_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,WallnitCircleProfile.class));
                }
            });

            notificationIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,WallnitCircleLikeNotification.class));
                }
            });

            backgroundIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,WallnitCircleBackgroundOption.class));
                }
            });

            searchIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,WallnitCircleSuggestionList.class));
                }
            });

            moreIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitOtherUserProfile.this,WallnitCircleMoreOption.class));
                }
            });
        }

        followbtn = (ImageView) findViewById(R.id.profile_user_followbtn);
        unfollowbtn = (ImageView) findViewById(R.id.profile_user_unfollowbtn);

        if(ownCircleId.equals("circle_not_login"))
        {
            if(!otherUserId.equals(ownUserId))
            {
                String getUserFollowOrUn;
                wallnitUserFollowUnfollowCheck = new WallnitUserFollowUnfollowCheck();
                getUserFollowOrUn = wallnitUserFollowUnfollowCheck.wallnitUserFollowUnfollow(ownUserId,otherUserId);

                if(getUserFollowOrUn.equals("follow"))
                {
                    followbtn.setVisibility(View.VISIBLE);
                    unfollowbtn.setVisibility(View.GONE);
                }
                if(getUserFollowOrUn.equals("unfollow"))
                {
                    unfollowbtn.setVisibility(View.VISIBLE);
                    followbtn.setVisibility(View.GONE);
                }
            }
        }


        followbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallnitUserFollow = new WallnitUserFollow();

                if(cd.isConnected())
                {
                    wallnitUserFollow.wallnitFollow(ownUserId,otherUserId);
                    followbtn.setVisibility(View.GONE);
                    unfollowbtn.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(WallnitOtherUserProfile.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        unfollowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallnitUserUnfollow = new WallnitUserUnfollow();

                if(cd.isConnected())
                {
                    wallnitUserUnfollow.wallnitUnfollow(ownUserId,otherUserId);
                    unfollowbtn.setVisibility(View.GONE);
                    followbtn.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(WallnitOtherUserProfile.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        otherprofile_backgroundimage = (ImageView) findViewById(R.id.otherprofile_backgroundimage_set);
        otherprofile_background_gifimage = (WebView) findViewById(R.id.otherprofile_backgroundimage_gif_set);

        imageExtensionGet = new ImageExtensionGet();

        otherprofile_imageset = (ImageView) findViewById(R.id.other_profile_image);
        otherprofile_gif_imageset = (WebView) findViewById(R.id.other_profile_profileimage_gif_set);

        userprofilename_txt = (TextView) findViewById(R.id.other_profile_username);
        usertagname_txt = (TextView) findViewById(R.id.other_profile_tagname);
        userstatus_txt = (TextView) findViewById(R.id.other_user_startuswrite_show);
        usergender_txt = (TextView) findViewById(R.id.other_profile_gender);
        userdob_txt = (TextView) findViewById(R.id.other_profile_dob);
        userplace_txt = (TextView) findViewById(R.id.other_profile_place);
        userwork_txt = (TextView) findViewById(R.id.other_profile_work);
        userdescription_txt = (TextView) findViewById(R.id.other_profile_description);


        wallnitUserProfileGetValues = new WallnitUserProfileGetValues();

        if(cd.isConnected())
        {
            get_details = wallnitUserProfileGetValues.getvaluesProfile(otherUserId);
            username = get_details[0];
            tagname = get_details[1];
            profile_img = get_details[2];
            gender = get_details[3];
            month = get_details[4];
            day = get_details[5];
            year = get_details[6];
            place = get_details[7];
            work = get_details[8];
            user_desc = get_details[9];
            user_status = get_details[10];
            getBackground = get_details[11];
            followernum = get_details[12];
            followingnum = get_details[13];
            userpost_ornot = get_details[14];
        } else {
            username = "null";
            tagname = "null";
            profile_img = "profile_image.png";
            gender = "null";
            month = "null";
            day = "null";
            year = "null";
            place = "null";
            work = "null";
            user_desc = "null";
            user_status = "null";
            getBackground = "color1/color1.jpg";
            followernum = "";
            followingnum = "";
            userpost_ornot = "no post";
            Toast.makeText(WallnitOtherUserProfile.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        userotherFollower_number.setText(followernum);
        userotherFollowing_number.setText(followingnum);

        if(!username.equals("null"))
        {
            userprofilename_txt.setVisibility(View.VISIBLE);
            userprofilename_txt.setText(username);
        }
        if(!tagname.equals("null"))
        {
            usertagname_txt.setVisibility(View.VISIBLE);
            usertagname_txt.setText(tagname);
        }
        if(!gender.equals("null"))
        {
            usergender_txt.setVisibility(View.VISIBLE);
            usergender_txt.setText(gender);
        }
        if(!month.equals("null") && !day.equals("null") && !year.equals("null"))
        {
            userdob_txt.setVisibility(View.VISIBLE);
            String dob = month+" "+day+","+year;
            userdob_txt.setText(dob);
        }
        if(!place.equals("null"))
        {
            userplace_txt.setVisibility(View.VISIBLE);
            userplace_txt.setText(place);
        }
        if(!work.equals("null"))
        {
            userwork_txt.setVisibility(View.VISIBLE);
            userwork_txt.setText(work);
        }
        if(!user_desc.equals("null"))
        {
            userdescription_txt.setVisibility(View.VISIBLE);
            userdescription_txt.setText(user_desc);
        }
        if(!user_status.equals("null"))
        {
            userstatus_txt.setVisibility(View.VISIBLE);
            userstatus_txt.setText(user_status);
        }

        seepost_btn = (Button) findViewById(R.id.postseebtn_other);

        if(userpost_ornot.equals("yes post"))
        {
            seepost_btn.setVisibility(View.VISIBLE);
        }
        if(userpost_ornot.equals("no post"))
        {
            seepost_btn.setVisibility(View.GONE);
        }

        seepost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postIntent = new Intent(getApplicationContext(),WallnitOwnProfilePost.class);
                postIntent.putExtra("profile_post_user_id",otherUserId);
                startActivity(postIntent);
            }
        });

        background_imageType = imageExtensionGet.getImageName(getBackground);

        if(background_imageType.equals("jpg") || background_imageType.equals("png") || background_imageType.equals("jpeg"))
        {
            otherprofile_backgroundimage.setVisibility(View.VISIBLE);
            if(getBackground.equals("color1/color1.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_first);
            }
            else if(getBackground.equals("color2/color2.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_second);
            }
            else if(getBackground.equals("color3/color3.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_third);
            }
            else if(getBackground.equals("color4/color4.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_fourth);
            }
            else if(getBackground.equals("color5/color5.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_fifth);
            }
            else if(getBackground.equals("color6/color6.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_sixth);
            }
            else if(getBackground.equals("color7/color7.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_seventh);
            }
            else if(getBackground.equals("color8/color8.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_eighth);
            }
            else if(getBackground.equals("color9/color9.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_ninth);
            }
            else if(getBackground.equals("color10/color10.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_tenth);
            }
            else if(getBackground.equals("color11/color11.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_eleventh);
            }
            else if(getBackground.equals("color12/color12.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_twelth);
            }
            else if(getBackground.equals("color13/color13.jpg"))
            {
                otherprofile_backgroundimage.setImageResource(R.drawable.color_thirteen);
            }
            else if(getBackground.equals("theme1/theme1 main.jpg"))
            {
                String backgroundImage = "first_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme2/theme2 main.jpg"))
            {
                String backgroundImage = "second_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme3/theme3 main.jpg"))
            {
                String backgroundImage = "third_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme4/theme4 main.jpg"))
            {
                String backgroundImage = "fourth_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme5/theme5 main.jpg"))
            {
                String backgroundImage = "fifth_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme6/theme6 main.jpg"))
            {
                String backgroundImage = "sixth_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme7/theme7 main.jpg"))
            {
                String backgroundImage = "seventh_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme8/theme8 main.jpg"))
            {
                String backgroundImage = "eighth_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme9/theme9 main.jpg"))
            {
                String backgroundImage = "ninth_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme10/theme10 main.jpg"))
            {
                String backgroundImage = "tenth_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme11/theme11 main.jpg"))
            {
                String backgroundImage = "eleventh_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme12/theme12 main.jpg"))
            {
                String backgroundImage = "twelth_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme13/theme13 main.jpg"))
            {
                String backgroundImage = "thirteen_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme14/theme14 main.jpg"))
            {
                String backgroundImage = "fourteen_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else if(getBackground.equals("theme15/theme15 main.jpg"))
            {
                String backgroundImage = "fifteen_background.jpg";
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,otherprofile_backgroundimage);
            }
            else
            {
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "upload_user_image/" + getBackground,otherprofile_backgroundimage);
            }
        }
        if(background_imageType.equals("gif"))
        {
            assert otherprofile_background_gifimage != null;
            otherprofile_background_gifimage.setVisibility(View.VISIBLE);
            otherprofile_background_gifimage.loadUrl(SERVER_ADDRESS + "wallnit_android/wallnit_android_user_backgroundimage_set.php?backgroundimage="+getBackground);
        }

        profile_imageFind = imageExtensionGet.getImageName(profile_img);

        if(profile_imageFind.equals("jpg") || profile_imageFind.equals("png") || profile_imageFind.equals("jpeg"))
        {
            otherprofile_imageset.setVisibility(View.VISIBLE);
            if(profile_img.equals("profile_image.png"))
            {
                otherprofile_imageset.setImageResource(R.drawable.profile_image);
            }
            if(!profile_img.equals("profile_image.png"))
            {
                PicassoClient.downloadImgae(WallnitOtherUserProfile.this,SERVER_ADDRESS + "uploadUserprofileImage/" + profile_img,otherprofile_imageset);
            }
        }
        if(profile_imageFind.equals("gif"))
        {
            assert otherprofile_gif_imageset != null;
            profile_frameLayout.setVisibility(View.VISIBLE);
            otherprofile_gif_imageset.setVisibility(View.VISIBLE);
            otherprofile_gif_imageset.loadUrl(SERVER_ADDRESS + "wallnit_android/wallnit_android_user_profileimage.php?userprofileimage="+profile_img);
        }
    }
}
