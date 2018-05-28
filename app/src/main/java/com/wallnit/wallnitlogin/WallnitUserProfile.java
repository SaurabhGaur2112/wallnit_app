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
import com.wallnit.wallnitlogin.WallnitUserProfileFetchDetails.WallnitUserProfileGetValues;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class WallnitUserProfile extends AppCompatActivity {


    Session session;
    String userid;

    ImageView profile_backgroundimage,profile_imageset;
    WebView profile_background_gifimage,profile_gif_imageset;

    FrameLayout profile_frameLayout;
    ImageExtensionGet imageExtensionGet;
    String getBackground,background_imageType;
    private static final String SERVER_ADDRESS = "http://www.wallnit.com/";
    TextView userprofilename_txt,usertagname_txt,userstatus_txt,usergender_txt,userdob_txt,userplace_txt,userwork_txt,userdescription_txt;

    String get_details[];
    String username,tagname,profile_img,gender,month,day,year,place,work,user_desc,user_status,userpost_ornot;
    WallnitUserProfileGetValues wallnitUserProfileGetValues;
    String profile_imageFind;
    TextView userownFollwer,userownFollower_number;
    TextView userownFollowing,userownFollowing_number;
    String followernum,followingnum;
    Button seepost_btn;
    ConnectionDetector cd;
    ImageView home_icon,notificationIcon,backgroundIcon,searchIcon,moreIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_user_profile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        session = new Session(this);
        userid = session.getUserSession();
        cd = new ConnectionDetector(this);

        home_icon = (ImageView) findViewById(R.id.homeicon);
        notificationIcon = (ImageView) findViewById(R.id.notificationicon);
        backgroundIcon = (ImageView) findViewById(R.id.backgroundicon);
        searchIcon = (ImageView) findViewById(R.id.searchicon);
        moreIcon = (ImageView) findViewById(R.id.moreicon);

        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserProfile.this,Wall.class));
            }
        });

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserProfile.this,WallnitUserLikeNotification.class));
            }
        });

        backgroundIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserProfile.this,WallnitUserBackgroundOption.class));
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserProfile.this,WallnitUserSuggestionList.class));
            }
        });

        moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserProfile.this,WallnitUserMoreOption.class));
            }
        });


        userownFollwer = (TextView) findViewById(R.id.profileno_follower);
        userownFollower_number = (TextView) findViewById(R.id.profileno_follower_number);

        userownFollwer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followerIntent = new Intent(getApplicationContext(),WallnitUserFollowerList.class);
                followerIntent.putExtra("follower_user_id",userid);
                startActivity(followerIntent);
            }
        });

        userownFollower_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followerIntent = new Intent(getApplicationContext(),WallnitUserFollowerList.class);
                followerIntent.putExtra("follower_user_id",userid);
                startActivity(followerIntent);
            }
        });

        userownFollowing = (TextView) findViewById(R.id.profileno_following);
        userownFollowing_number = (TextView) findViewById(R.id.profileno_following_number);

        userownFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followingIntent = new Intent(getApplicationContext(),WallnitUserFollowingList.class);
                followingIntent.putExtra("following_user_id",userid);
                startActivity(followingIntent);
            }
        });

        userownFollowing_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followingIntent = new Intent(getApplicationContext(),WallnitUserFollowingList.class);
                followingIntent.putExtra("following_user_id",userid);
                startActivity(followingIntent);
            }
        });

        profile_backgroundimage = (ImageView) findViewById(R.id.profile_backgroundimage_set);
        profile_background_gifimage = (WebView) findViewById(R.id.profile_backgroundimage_gif_set);


        imageExtensionGet = new ImageExtensionGet();


        profile_imageset = (ImageView) findViewById(R.id.profile_image);
        profile_gif_imageset = (WebView) findViewById(R.id.profile_profileimage_gif_set);

        profile_frameLayout = (FrameLayout) findViewById(R.id.profile_framelayout_set);

        userprofilename_txt = (TextView) findViewById(R.id.profile_username);
        usertagname_txt = (TextView) findViewById(R.id.profile_tagname);
        userstatus_txt = (TextView) findViewById(R.id.user_startuswrite_show);
        usergender_txt = (TextView) findViewById(R.id.profile_gender);
        userdob_txt = (TextView) findViewById(R.id.profile_dob);
        userplace_txt = (TextView) findViewById(R.id.profile_place);
        userwork_txt = (TextView) findViewById(R.id.profile_work);
        userdescription_txt = (TextView) findViewById(R.id.profile_description);

        wallnitUserProfileGetValues = new WallnitUserProfileGetValues();

        if(cd.isConnected())
        {
            get_details = wallnitUserProfileGetValues.getvaluesProfile(userid);
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
            Toast.makeText(WallnitUserProfile.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }

        userownFollower_number.setText(followernum);
        userownFollowing_number.setText(followingnum);

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

        seepost_btn = (Button) findViewById(R.id.postseebtn_own);

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
                postIntent.putExtra("profile_post_user_id",userid);
                startActivity(postIntent);
            }
        });

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
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme2/theme2 main.jpg"))
            {
                String backgroundImage = "second_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme3/theme3 main.jpg"))
            {
                String backgroundImage = "third_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme4/theme4 main.jpg"))
            {
                String backgroundImage = "fourth_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme5/theme5 main.jpg"))
            {
                String backgroundImage = "fifth_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme6/theme6 main.jpg"))
            {
                String backgroundImage = "sixth_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme7/theme7 main.jpg"))
            {
                String backgroundImage = "seventh_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme8/theme8 main.jpg"))
            {
                String backgroundImage = "eighth_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme9/theme9 main.jpg"))
            {
                String backgroundImage = "ninth_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme10/theme10 main.jpg"))
            {
                String backgroundImage = "tenth_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme11/theme11 main.jpg"))
            {
                String backgroundImage = "eleventh_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme12/theme12 main.jpg"))
            {
                String backgroundImage = "twelth_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme13/theme13 main.jpg"))
            {
                String backgroundImage = "thirteen_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme14/theme14 main.jpg"))
            {
                String backgroundImage = "fourteen_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(getBackground.equals("theme15/theme15 main.jpg"))
            {
                String backgroundImage = "fifteen_background.jpg";
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else
            {
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "upload_user_image/" + getBackground,profile_backgroundimage);
            }
        }
        if(background_imageType.equals("gif"))
        {
            assert profile_background_gifimage != null;
            profile_background_gifimage.setVisibility(View.VISIBLE);
            profile_background_gifimage.loadUrl(SERVER_ADDRESS + "wallnit_android/wallnit_android_user_backgroundimage_set.php?backgroundimage="+getBackground);
        }

        profile_imageFind = imageExtensionGet.getImageName(profile_img);

        if(profile_imageFind.equals("jpg") || profile_imageFind.equals("png") || profile_imageFind.equals("jpeg"))
        {
            profile_imageset.setVisibility(View.VISIBLE);
            if(profile_img.equals("profile_image.png"))
            {
                profile_imageset.setImageResource(R.drawable.profile_image);
            }
            if(!profile_img.equals("profile_image.png"))
            {
                PicassoClient.downloadImgae(WallnitUserProfile.this,SERVER_ADDRESS + "uploadUserprofileImage/" + profile_img,profile_imageset);
            }
        }
        if(profile_imageFind.equals("gif"))
        {
            assert profile_gif_imageset != null;
            profile_frameLayout.setVisibility(View.VISIBLE);
            profile_gif_imageset.setVisibility(View.VISIBLE);
            profile_gif_imageset.loadUrl(SERVER_ADDRESS + "wallnit_android/wallnit_android_user_profileimage.php?userprofileimage="+profile_img);
        }

    }
}
