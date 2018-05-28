package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitCircleMoreOptionValueFetch.WallnitCircleGetValueOnMore;
import com.wallnit.wallnitlogin.WallnitImageFindExtension.ImageExtensionGet;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;

import de.hdodenhof.circleimageview.CircleImageView;

public class WallnitCircleMoreOption extends AppCompatActivity {
    ImageView profileImageClick;
    ImageView home_icon,notification_icon,background_icon,search_icon,more_icon,more_select_icon;
    LinearLayout accountLayout,circleLogout;
    Session session;
    private static final String SERVER_ADDRESS = "http://www.wallnit.com/";
    ConnectionDetector cd;
    String sessionCircleId;
    String get_moreoption_values[];
    WallnitCircleGetValueOnMore wallnitCircleGetValueOnMore;
    ImageExtensionGet imageExtensionGet;
    String circlename,emailadd,profileImg,profile_imageFind;
    TextView circleProfilename,circleProfileemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_circle_more_option);

        session = new Session(this);
        cd = new ConnectionDetector(this);
        sessionCircleId = session.getCircleSession();

        profileImageClick = (ImageView) findViewById(R.id.circleprofile_changeclick);
        home_icon = (ImageView) findViewById(R.id.profile_homeicon);
        notification_icon = (ImageView) findViewById(R.id.profile_notificationicon);
        background_icon = (ImageView) findViewById(R.id.profile_backgroundicon);
        search_icon = (ImageView) findViewById(R.id.profile_searchicon);
        more_icon = (ImageView) findViewById(R.id.profile_moreicon);
        more_select_icon = (ImageView) findViewById(R.id.profile_more_selecticon);
        accountLayout = (LinearLayout) findViewById(R.id.circle_accountSetting_layout);
        circleLogout = (LinearLayout) findViewById(R.id.circle_logoutLayout);
        circleProfilename = (TextView) findViewById(R.id.more_Userprofilename);
        circleProfileemail = (TextView) findViewById(R.id.circleProfile_emailid);

        wallnitCircleGetValueOnMore = new WallnitCircleGetValueOnMore();
        imageExtensionGet = new ImageExtensionGet();

        more_icon.setVisibility(View.GONE);
        more_select_icon.setVisibility(View.VISIBLE);

        if(cd.isConnected())
        {
            get_moreoption_values = wallnitCircleGetValueOnMore.getvaluesCircleMore(sessionCircleId);
            circlename = get_moreoption_values[0];
            emailadd = get_moreoption_values[1];
            profileImg = get_moreoption_values[2];

        } else {
            circlename = "Profile";
            emailadd = "";
            profileImg = "profile_image.png";
            Toast.makeText(WallnitCircleMoreOption.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        circleProfilename.setText(circlename);
        circleProfileemail.setText(emailadd);

        profile_imageFind = imageExtensionGet.getImageName(profileImg);

        if(profile_imageFind.equals("jpg") || profile_imageFind.equals("png") || profile_imageFind.equals("jpeg") || profile_imageFind.equals("gif"))
        {
            if(profileImg.equals("profile_image.png"))
            {
                profileImageClick.setImageResource(R.drawable.profile_image);
            }
            if(!profileImg.equals("profile_image.png"))
            {
                PicassoClient.downloadImgae(WallnitCircleMoreOption.this,SERVER_ADDRESS + "uploadCircleProfileImage/" + profileImg,profileImageClick);
            }
        }

        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleMoreOption.this,WallnitCircleProfile.class));
            }
        });

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleMoreOption.this,WallnitCircleLikeNotification.class));
            }
        });

        background_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleMoreOption.this,WallnitCircleBackgroundOption.class));
            }
        });

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleMoreOption.this,WallnitCircleSuggestionList.class));
            }
        });



        profileImageClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleMoreOption.this,WallnitUploadCircleImage.class));
            }
        });

        accountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitCircleMoreOption.this,WallnitCircleAccountSetting.class));
            }
        });

        circleLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setCircleLoggedin(false);
                session.setUserSession(null);
                session.setCircleSession(null);
                Intent intent = new Intent(getApplicationContext(), WallnitStart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
