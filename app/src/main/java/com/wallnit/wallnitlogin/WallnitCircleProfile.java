package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.LightingColorFilter;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitCircleProfileFetchDetails.WallnitCircleProfileGetEmailid;
import com.wallnit.wallnitlogin.WallnitCircleProfileFetchDetails.WallnitCircleProfileGetValues;
import com.wallnit.wallnitlogin.WallnitImageFindExtension.ImageExtensionGet;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;
import com.wallnit.wallnitlogin.WallnitUserMember.WallnitTotalNumberMember;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WallnitCircleProfile extends AppCompatActivity {

    Session session;

    ImageView profile_backgroundimage;
    CircleImageView profile_image;
    WebView profile_background_gifimage,profile_gif_imageset;

    WallnitCircleProfileGetValues wallnitCircleProfileGetValues;
    WallnitCircleProfileGetEmailid wallnitCircleProfileGetEmailid;
    WallnitTotalNumberMember wallnitTotalNumberMember;

    String circleTotalNummember;

    ImageExtensionGet imageExtensionGet;
    FrameLayout circle_profile_frame;

    String circleid;
    String getCircleValuesDetails[];
    String circlename,circle_email,circle_profile,circle_category,circle_type,circle_background,circle_about_desc,circle_post_or_not;
    String background_imageType,profile_imageType;
    private static final String SERVER_ADDRESS = "http://www.wallnit.com/";

    TextView circle_nametxt,circle_emailtxt,circle_categorytxt,circle_descriptiontxt;

    FloatingActionButton quoteUpload_option,blogUpload_option,photosUpload_option;

    LinearLayout layout_member;

    TextView circle_totalNumMeber;

    String user_id;
    Button btnseepost;
    ConnectionDetector cd;
    ImageView homeIcon,notificationIcon,backgroundIcon,searchIcon,moreIcon,homeSelectIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_circle_profile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        session = new Session(this);
        circleid = session.getCircleSession();
        user_id = session.getUserSession();
        cd = new ConnectionDetector(this);

        homeIcon = (ImageView) findViewById(R.id.profile_homeicon);
        notificationIcon = (ImageView) findViewById(R.id.profile_notificationicon);
        backgroundIcon = (ImageView) findViewById(R.id.profile_backgroundicon);
        searchIcon = (ImageView) findViewById(R.id.profile_searchicon);
        moreIcon = (ImageView) findViewById(R.id.profile_moreicon);
        homeSelectIcon = (ImageView) findViewById(R.id.profile_homeicon_select);


        homeIcon.setVisibility(View.GONE);
        homeSelectIcon.setVisibility(View.VISIBLE);

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleProfile.this,WallnitCircleLikeNotification.class));
            }
        });

        backgroundIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleProfile.this, WallnitCircleBackgroundOption.class));
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleProfile.this,WallnitCircleSuggestionList.class));
            }
        });

        moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleProfile.this, WallnitCircleMoreOption.class));
            }
        });



        wallnitCircleProfileGetValues = new WallnitCircleProfileGetValues();
        wallnitCircleProfileGetEmailid = new WallnitCircleProfileGetEmailid();
        wallnitTotalNumberMember = new WallnitTotalNumberMember();

        circleTotalNummember = wallnitTotalNumberMember.circleTotalNumMember(circleid);

        layout_member = (LinearLayout) findViewById(R.id.layoutmember_get);
        layout_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getMemberIntent = new Intent(getApplicationContext(),WallnitCircleMemberList.class);
                getMemberIntent.putExtra("member_circleid",circleid);
                startActivity(getMemberIntent);
            }
        });

        circle_totalNumMeber = (TextView) findViewById(R.id.totalCircleMemberNumber);
        circle_totalNumMeber.setText(circleTotalNummember);

        quoteUpload_option = (FloatingActionButton) findViewById(R.id.fab_circle_quoteupload_option);
        blogUpload_option = (FloatingActionButton) findViewById(R.id.fab_circle_blogupload_option);
        photosUpload_option = (FloatingActionButton) findViewById(R.id.fab_circle_photosupload_option);

        imageExtensionGet = new ImageExtensionGet();

        profile_backgroundimage = (ImageView) findViewById(R.id.circleprofile_backgroundimage_set);
        profile_background_gifimage = (WebView) findViewById(R.id.circleprofile_backgroundimage_gif_set);

        profile_image = (CircleImageView) findViewById(R.id.circleprofile_image);
        profile_gif_imageset = (WebView) findViewById(R.id.circleprofile_image_gif);
        circle_profile_frame = (FrameLayout) findViewById(R.id.circleprofile_framelayout);

        circle_nametxt = (TextView) findViewById(R.id.circlename_txt);
        circle_emailtxt = (TextView) findViewById(R.id.circle_emailid_txt);
        circle_categorytxt = (TextView) findViewById(R.id.categoryname_txt);
        circle_descriptiontxt = (TextView) findViewById(R.id.circledescription_abouttxt);

        if(cd.isConnected())
        {
            getCircleValuesDetails = wallnitCircleProfileGetValues.getvaluesCircleProfile(circleid);

            circlename = getCircleValuesDetails[0];
            circle_profile = getCircleValuesDetails[1];
            circle_category = getCircleValuesDetails[2];
            circle_type = getCircleValuesDetails[3];
            circle_background = getCircleValuesDetails[4];
            circle_about_desc = getCircleValuesDetails[5];
            circle_post_or_not = getCircleValuesDetails[7];
        } else {
            circlename = "null";
            circle_profile = "profile_image.png";
            circle_category = "";
            circle_type = "null";
            circle_background = "color1/color1.jpg";
            circle_about_desc = "null";
            circle_post_or_not = "no post";
        }



        btnseepost = (Button) findViewById(R.id.circle_postsee_btnown);

        if(circle_post_or_not.equals("yes post"))
        {
            btnseepost.setVisibility(View.VISIBLE);
        } else {
            btnseepost.setVisibility(View.GONE);
        }

        btnseepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntentValue = new Intent(getApplicationContext(), WallnitCircleOwnOtherPost.class);
                Bundle extras = new Bundle();
                extras.putString("circleId",circleid);
                extras.putString("circleType",circle_type);
                sendIntentValue.putExtras(extras);
                startActivity(sendIntentValue);
            }
        });

        if(circle_type.equals("quote"))
        {
            quoteUpload_option.setVisibility(View.VISIBLE);
            quoteUpload_option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitCircleProfile.this,WallnitCircleUploadQuote.class));
                }
            });
        }
        if(circle_type.equals("blog"))
        {
            blogUpload_option.setVisibility(View.VISIBLE);
            blogUpload_option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitCircleProfile.this,WallnitCircleUploadBlog.class));
                }
            });
        }
        if(circle_type.equals("photo"))
        {
            photosUpload_option.setVisibility(View.VISIBLE);
            photosUpload_option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WallnitCircleProfile.this,WallnitCircleUploadPhotos.class));
                }
            });
        }

        circle_nametxt.setText(circlename);

        if(cd.isConnected())
        {
            circle_email = wallnitCircleProfileGetEmailid.circleEmailid(circleid);
        } else {
            circle_email = "null";
        }

        if(!circle_email.equals("null"))
        {
            circle_emailtxt.setVisibility(View.VISIBLE);
            circle_emailtxt.setText(circle_email);
        }
        circle_categorytxt.setText(circle_category);
        if(!circle_about_desc.equals("null"))
        {
            circle_descriptiontxt.setVisibility(View.VISIBLE);
            circle_descriptiontxt.setText(circle_about_desc);
        }

        background_imageType = imageExtensionGet.getImageName(circle_background);
        profile_imageType = imageExtensionGet.getImageName(circle_profile);

        if(profile_imageType.equals("jpg") || profile_imageType.equals("png") || profile_imageType.equals("jpeg"))
        {
            profile_image.setVisibility(View.VISIBLE);
            if(circle_profile.equals("profile_image.png"))
            {
                profile_image.setImageResource(R.drawable.profile_image);
            }
            if(!circle_profile.equals("profile_image.png"))
            {
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "uploadCircleProfileImage/" + circle_profile,profile_image);
            }
        }
        if(profile_imageType.equals("gif"))
        {
            assert profile_gif_imageset != null;
            circle_profile_frame.setVisibility(View.VISIBLE);
            profile_gif_imageset.setVisibility(View.VISIBLE);
            profile_gif_imageset.loadUrl(SERVER_ADDRESS + "wallnit_android/wallnit_android_circle_profileimage.php?circleprofileimage="+circle_profile);
        }


        if(background_imageType.equals("jpg") || background_imageType.equals("png") || background_imageType.equals("jpeg"))
        {
            profile_backgroundimage.setVisibility(View.VISIBLE);
            if(circle_background.equals("color1/color1.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_first);
            }
            else if(circle_background.equals("color2/color2.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_second);
            }
            else if(circle_background.equals("color3/color3.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_third);
            }
            else if(circle_background.equals("color4/color4.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_fourth);
            }
            else if(circle_background.equals("color5/color5.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_fifth);
            }
            else if(circle_background.equals("color6/color6.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_sixth);
            }
            else if(circle_background.equals("color7/color7.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_seventh);
            }
            else if(circle_background.equals("color8/color8.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_eighth);
            }
            else if(circle_background.equals("color9/color9.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_ninth);
            }
            else if(circle_background.equals("color10/color10.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_tenth);
            }
            else if(circle_background.equals("color11/color11.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_eleventh);
            }
            else if(circle_background.equals("color12/color12.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_twelth);
            }
            else if(circle_background.equals("color13/color13.jpg"))
            {
                profile_backgroundimage.setImageResource(R.drawable.color_thirteen);
            }
            else if(circle_background.equals("theme1/theme1 main.jpg"))
            {
                String backgroundImage = "first_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme2/theme2 main.jpg"))
            {
                String backgroundImage = "second_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme3/theme3 main.jpg"))
            {
                String backgroundImage = "third_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme4/theme4 main.jpg"))
            {
                String backgroundImage = "fourth_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme5/theme5 main.jpg"))
            {
                String backgroundImage = "fifth_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme6/theme6 main.jpg"))
            {
                String backgroundImage = "sixth_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme7/theme7 main.jpg"))
            {
                String backgroundImage = "seventh_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme8/theme8 main.jpg"))
            {
                String backgroundImage = "eighth_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme9/theme9 main.jpg"))
            {
                String backgroundImage = "ninth_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme10/theme10 main.jpg"))
            {
                String backgroundImage = "tenth_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme11/theme11 main.jpg"))
            {
                String backgroundImage = "eleventh_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme12/theme12 main.jpg"))
            {
                String backgroundImage = "twelth_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme13/theme13 main.jpg"))
            {
                String backgroundImage = "thirteen_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme14/theme14 main.jpg"))
            {
                String backgroundImage = "fourteen_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else if(circle_background.equals("theme15/theme15 main.jpg"))
            {
                String backgroundImage = "fifteen_background.jpg";
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "theme_background/" + backgroundImage,profile_backgroundimage);
            }
            else
            {
                PicassoClient.downloadImgae(WallnitCircleProfile.this,SERVER_ADDRESS + "upload_circle_image/" + circle_background,profile_backgroundimage);
            }
        }
        if(background_imageType.equals("gif"))
        {
            assert profile_background_gifimage != null;
            profile_background_gifimage.setVisibility(View.VISIBLE);
            profile_background_gifimage.loadUrl(SERVER_ADDRESS + "wallnit_android/wallnit_android_circle_backgroundimage_set.php?backgroundimage="+circle_background);
        }

    }

}
