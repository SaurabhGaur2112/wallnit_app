package com.wallnit.wallnitlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitUserBackgroundUpdate.WallnitUserColorImageThemeUpdate;

public class WallnitUserBackgroundImageTheme extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText image_edit;
    Session session;
    WallnitUserColorImageThemeUpdate wallnitUserColorImageThemeUpdate;

    ImageView img_theme1,img_theme2,img_theme3,img_theme4,img_theme5,img_theme6,img_theme7,img_theme8,
            img_theme9,img_theme10,img_theme11,img_theme12,img_theme13,img_theme14,img_theme15,img_theme_sel1,
            img_theme_sel2,img_theme_sel3,img_theme_sel4,img_theme_sel5,img_theme_sel6,img_theme_sel7,
            img_theme_sel8,img_theme_sel9,img_theme_sel10,img_theme_sel11,img_theme_sel12,img_theme_sel13,
            img_theme_sel14,img_theme_sel15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_background_image_theme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        session = new Session(this);
        wallnitUserColorImageThemeUpdate = new WallnitUserColorImageThemeUpdate();
        image_edit = (EditText) findViewById(R.id.background_imagewrite);

        img_theme1=(ImageView) findViewById(R.id.user_imagetheme_first);
        img_theme_sel1=(ImageView) findViewById(R.id.user_imagetheme_first_select);

        img_theme2=(ImageView) findViewById(R.id.user_imagetheme_second);
        img_theme_sel2=(ImageView) findViewById(R.id.user_imagetheme_second_select);

        img_theme3=(ImageView) findViewById(R.id.user_imagetheme_third);
        img_theme_sel3=(ImageView) findViewById(R.id.user_imagetheme_third_select);

        img_theme4=(ImageView) findViewById(R.id.user_imagetheme_fourth);
        img_theme_sel4=(ImageView) findViewById(R.id.user_imagetheme_fourth_select);

        img_theme5=(ImageView) findViewById(R.id.user_imagetheme_fifth);
        img_theme_sel5=(ImageView) findViewById(R.id.user_imagetheme_fifth_select);

        img_theme6=(ImageView) findViewById(R.id.user_imagetheme_sixth);
        img_theme_sel6=(ImageView) findViewById(R.id.user_imagetheme_sixth_select);

        img_theme7=(ImageView) findViewById(R.id.user_imagetheme_seventh);
        img_theme_sel7=(ImageView) findViewById(R.id.user_imagetheme_seventh_select);

        img_theme8=(ImageView) findViewById(R.id.user_imagetheme_eighth);
        img_theme_sel8=(ImageView) findViewById(R.id.user_imagetheme_eighth_select);

        img_theme9=(ImageView) findViewById(R.id.user_imagetheme_ninth);
        img_theme_sel9=(ImageView) findViewById(R.id.user_imagetheme_ninth_select);

        img_theme10=(ImageView) findViewById(R.id.user_imagetheme_tenth);
        img_theme_sel10=(ImageView) findViewById(R.id.user_imagetheme_tenth_select);

        img_theme11=(ImageView) findViewById(R.id.user_imagetheme_eleventh);
        img_theme_sel11=(ImageView) findViewById(R.id.user_imagetheme_eleventh_select);

        img_theme12=(ImageView) findViewById(R.id.user_imagetheme_twelth);
        img_theme_sel12=(ImageView) findViewById(R.id.user_imagetheme_twelth_select);

        img_theme13=(ImageView) findViewById(R.id.user_imagetheme_thirteen);
        img_theme_sel13=(ImageView) findViewById(R.id.user_imagetheme_thirteen_select);

        img_theme14=(ImageView) findViewById(R.id.user_imagetheme_fourteen);
        img_theme_sel14=(ImageView) findViewById(R.id.user_imagetheme_fourteen_select);

        img_theme15=(ImageView) findViewById(R.id.user_imagetheme_fifteen);
        img_theme_sel15=(ImageView) findViewById(R.id.user_imagetheme_fifteen_select);

        img_theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme1.setVisibility(View.GONE);
                img_theme_sel1.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme1/theme1 main.jpg");
            }
        });

        img_theme_sel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                image_edit.setText("");

            }
        });



        img_theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme2.setVisibility(View.GONE);
                img_theme_sel2.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme2/theme2 main.jpg");
            }
        });

        img_theme_sel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme3.setVisibility(View.GONE);
                img_theme_sel3.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme3/theme3 main.jpg");
            }
        });

        img_theme_sel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme4.setVisibility(View.GONE);
                img_theme_sel4.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme4/theme4 main.jpg");
            }
        });

        img_theme_sel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme5.setVisibility(View.GONE);
                img_theme_sel5.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme5/theme5 main.jpg");
            }
        });

        img_theme_sel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme6.setVisibility(View.GONE);
                img_theme_sel6.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme6/theme6 main.jpg");
            }
        });

        img_theme_sel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme7.setVisibility(View.GONE);
                img_theme_sel7.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme7/theme7 main.jpg");
            }
        });

        img_theme_sel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme8.setVisibility(View.GONE);
                img_theme_sel8.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme8/theme8 main.jpg");
            }
        });

        img_theme_sel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme9.setVisibility(View.GONE);
                img_theme_sel9.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme9/theme9 main.jpg");
            }
        });

        img_theme_sel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme10.setVisibility(View.GONE);
                img_theme_sel10.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme10/theme10 main.jpg");
            }
        });

        img_theme_sel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme11.setVisibility(View.GONE);
                img_theme_sel11.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme11/theme11 main.jpg");
            }
        });

        img_theme_sel11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme12.setVisibility(View.GONE);
                img_theme_sel12.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme12/theme12 main.jpg");
            }
        });

        img_theme_sel12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme13.setVisibility(View.GONE);
                img_theme_sel13.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme13/theme13 main.jpg");
            }
        });

        img_theme_sel13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme14.setVisibility(View.GONE);
                img_theme_sel14.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("theme14/theme14 main.jpg");
            }
        });

        img_theme_sel14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        img_theme15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme15.setVisibility(View.GONE);
                img_theme_sel15.setVisibility(View.VISIBLE);
                img_theme_sel2.setVisibility(View.GONE);
                img_theme2.setVisibility(View.VISIBLE);
                img_theme_sel3.setVisibility(View.GONE);
                img_theme3.setVisibility(View.VISIBLE);
                img_theme_sel4.setVisibility(View.GONE);
                img_theme4.setVisibility(View.VISIBLE);
                img_theme_sel5.setVisibility(View.GONE);
                img_theme5.setVisibility(View.VISIBLE);
                img_theme_sel6.setVisibility(View.GONE);
                img_theme6.setVisibility(View.VISIBLE);
                img_theme_sel7.setVisibility(View.GONE);
                img_theme7.setVisibility(View.VISIBLE);
                img_theme_sel8.setVisibility(View.GONE);
                img_theme8.setVisibility(View.VISIBLE);
                img_theme_sel9.setVisibility(View.GONE);
                img_theme9.setVisibility(View.VISIBLE);
                img_theme_sel10.setVisibility(View.GONE);
                img_theme10.setVisibility(View.VISIBLE);
                img_theme_sel11.setVisibility(View.GONE);
                img_theme11.setVisibility(View.VISIBLE);
                img_theme_sel12.setVisibility(View.GONE);
                img_theme12.setVisibility(View.VISIBLE);
                img_theme_sel13.setVisibility(View.GONE);
                img_theme13.setVisibility(View.VISIBLE);
                img_theme_sel14.setVisibility(View.GONE);
                img_theme14.setVisibility(View.VISIBLE);
                img_theme_sel1.setVisibility(View.GONE);
                img_theme1.setVisibility(View.VISIBLE);
                image_edit.setText("theme15/theme15 main.jpg");
            }
        });

        img_theme_sel15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_theme_sel15.setVisibility(View.GONE);
                img_theme15.setVisibility(View.VISIBLE);
                image_edit.setText("");
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_account_information_change, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.account_infochange)
        {
            String userSessionid = session.getUserSession();
            String imageGetTheme = image_edit.getText().toString();
            if(imageGetTheme.trim().length()>0)
            {
                new BackgroundThemeChange(userSessionid,imageGetTheme).execute();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public class BackgroundThemeChange extends AsyncTask<Void,Void,Void>{

        String userIdGet,imageThemeGet;
        ProgressDialog progressDialog;

        public BackgroundThemeChange(String userIdGet,String imageThemeGet){
            this.userIdGet = userIdGet;
            this.imageThemeGet = imageThemeGet;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitUserBackgroundImageTheme.this,"","Change Background...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            startActivity(new Intent(WallnitUserBackgroundImageTheme.this,Wall.class));
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wallnitUserColorImageThemeUpdate.userColorThemeUpdate(userIdGet,imageThemeGet);
            return null;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_img_home) {
            startActivity(new Intent(WallnitUserBackgroundImageTheme.this,Wall.class));
            finish();
        }  else if (id == R.id.nav_img_color_theme) {
            startActivity(new Intent(WallnitUserBackgroundImageTheme.this,WallnitUserBackgroundOption.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
