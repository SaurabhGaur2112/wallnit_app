package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitImageFindExtension.ImageExtensionGet;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;
import com.wallnit.wallnitlogin.WallnitUserMoreOptionValueFetch.WallnitUserGetValueOnMore;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WallnitUserMoreOption extends AppCompatActivity {
    ImageView profileImageCLick;
    LinearLayout profile_layout,account_setting_layout,logout_layout;
    ImageView header_iconmore,header_iconmore_select;
    ImageView home_icon,notification_icon,background_icon,search_icon;
    Session session;
    String get_moreoption_values[];
    WallnitUserGetValueOnMore wallnitUserGetValueOnMore;
    String sessionUserId;
    TextView usernameTxt,hashtagTxt,emailTxt;

    String getBackground,background_imageType;
    String username_txt,hashtagname_txt,emailid_txt,userprofile_image,profile_imageFind;
    ImageExtensionGet imageExtensionGet;

    private static final String SERVER_ADDRESS = "http://www.wallnit.com/";
    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_user_more_option);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        cd = new ConnectionDetector(this);
        profile_layout = (LinearLayout) findViewById(R.id.profile_layout);
        account_setting_layout = (LinearLayout) findViewById(R.id.more_accountsetting_layout);
        logout_layout = (LinearLayout) findViewById(R.id.more_logout_layout);
        header_iconmore = (ImageView) findViewById(R.id.moreicon);
        header_iconmore_select = (ImageView) findViewById(R.id.more_select_icon);
        home_icon = (ImageView) findViewById(R.id.homeicon);
        notification_icon = (ImageView) findViewById(R.id.notificationicon);
        background_icon = (ImageView) findViewById(R.id.backgroundicon);
        search_icon = (ImageView) findViewById(R.id.searchicon);

        usernameTxt = (TextView) findViewById(R.id.more_Userprofilename);
        hashtagTxt = (TextView) findViewById(R.id.more_userhashtag_name);
        emailTxt = (TextView) findViewById(R.id.moreoption_emailtxt);
        profileImageCLick = (ImageView) findViewById(R.id.userprofile_imageclick);


        imageExtensionGet = new ImageExtensionGet();
        session = new Session(this);

        sessionUserId = session.getUserSession();

        wallnitUserGetValueOnMore = new WallnitUserGetValueOnMore();

        header_iconmore.setVisibility(View.GONE);
        header_iconmore_select.setVisibility(View.VISIBLE);



        profileImageCLick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserMoreOption.this,WallnitUploadUserImage.class));
            }
        });

        profile_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserMoreOption.this,WallnitUserProfile.class));
            }
        });



        account_setting_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserMoreOption.this,WallnitUserAccountSetting.class));
            }
        });

        logout_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setLoggedin(false);
                session.setUserSession(null);
                session.setCircleSession(null);
                Intent intent = new Intent(getApplicationContext(), WallnitStart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserMoreOption.this,Wall.class));
            }
        });

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserMoreOption.this,WallnitUserLikeNotification.class));
            }
        });

        background_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserMoreOption.this,WallnitUserBackgroundOption.class));
            }
        });

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitUserMoreOption.this,WallnitUserSuggestionList.class));
            }
        });

        if(cd.isConnected())
        {
            get_moreoption_values = wallnitUserGetValueOnMore.getvaluesUserMore(sessionUserId);
            username_txt = get_moreoption_values[0];
            hashtagname_txt = get_moreoption_values[1];
            emailid_txt = get_moreoption_values[2];
            userprofile_image = get_moreoption_values[3];
        } else {
            username_txt = "Profile";
            hashtagname_txt = "";
            emailid_txt = "";
            userprofile_image = "profile_image.png";
            Toast.makeText(WallnitUserMoreOption.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        profile_imageFind = imageExtensionGet.getImageName(userprofile_image);

        usernameTxt.setText(username_txt);

        if(!hashtagname_txt.equals("null"))
        {
            hashtagTxt.setText(hashtagname_txt);
        } else {
            hashtagTxt.setText("");
        }

        if(!emailid_txt.equals("null"))
        {
            emailTxt.setText(emailid_txt);
        } else {
            emailTxt.setText("");
        }

        if(profile_imageFind.equals("jpg") || profile_imageFind.equals("png") || profile_imageFind.equals("jpeg") || profile_imageFind.equals("gif"))
        {
            if(userprofile_image.equals("profile_image.png"))
            {
                profileImageCLick.setImageResource(R.drawable.profile_image);
            }
            if(!userprofile_image.equals("profile_image.png"))
            {
                PicassoClient.downloadImgae(WallnitUserMoreOption.this,SERVER_ADDRESS + "uploadUserprofileImage/" + userprofile_image,profileImageCLick);
            }
        }

    }
}
