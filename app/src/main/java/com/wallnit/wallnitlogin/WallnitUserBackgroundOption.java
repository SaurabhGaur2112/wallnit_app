package com.wallnit.wallnitlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitUserBackgroundUpdate.WallnitUserColorImageThemeUpdate;

public class WallnitUserBackgroundOption extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText color_edit;

    ImageView clr_img1,clr_img2,clr_img3,clr_img4,clr_img5,clr_img6,clr_img7,clr_img8,
    clr_img9,clr_img10,clr_img11,clr_img12,clr_img13,clr_img_tick1,clr_img_tick2,clr_img_tick3,
            clr_img_tick4,clr_img_tick5,clr_img_tick6,clr_img_tick7,clr_img_tick8,clr_img_tick9,
            clr_img_tick10,clr_img_tick11,clr_img_tick12,clr_img_tick13;

    Session session;
    WallnitUserColorImageThemeUpdate wallnitUserColorImageThemeUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_background_option);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session = new Session(this);
        wallnitUserColorImageThemeUpdate = new WallnitUserColorImageThemeUpdate();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        color_edit = (EditText) findViewById(R.id.background_colorwrite);

        clr_img1=(ImageView) findViewById(R.id.user_color_first);
        clr_img_tick1=(ImageView) findViewById(R.id.user_color_first_select);

        clr_img2=(ImageView) findViewById(R.id.user_color_second);
        clr_img_tick2=(ImageView) findViewById(R.id.user_color_second_select);

        clr_img3=(ImageView) findViewById(R.id.user_color_third);
        clr_img_tick3=(ImageView) findViewById(R.id.user_color_third_select);

        clr_img4=(ImageView) findViewById(R.id.user_color_fourth);
        clr_img_tick4=(ImageView) findViewById(R.id.user_color_fourth_select);

        clr_img5=(ImageView) findViewById(R.id.user_color_fifth);
        clr_img_tick5=(ImageView) findViewById(R.id.user_color_fifth_select);

        clr_img6=(ImageView) findViewById(R.id.user_color_sixth);
        clr_img_tick6=(ImageView) findViewById(R.id.user_color_sixth_select);

        clr_img7=(ImageView) findViewById(R.id.user_color_seventh);
        clr_img_tick7=(ImageView) findViewById(R.id.user_color_seventh_select);

        clr_img8=(ImageView) findViewById(R.id.user_color_eighth);
        clr_img_tick8=(ImageView) findViewById(R.id.user_color_eighth_select);

        clr_img9=(ImageView) findViewById(R.id.user_color_ninth);
        clr_img_tick9=(ImageView) findViewById(R.id.user_color_ninth_select);

        clr_img10=(ImageView) findViewById(R.id.user_color_tenth);
        clr_img_tick10=(ImageView) findViewById(R.id.user_color_tenth_select);

        clr_img11=(ImageView) findViewById(R.id.user_color_eleventh);
        clr_img_tick11=(ImageView) findViewById(R.id.user_color_eleventh_select);

        clr_img12=(ImageView) findViewById(R.id.user_color_twelth);
        clr_img_tick12=(ImageView) findViewById(R.id.user_color_twelth_select);

        clr_img13=(ImageView) findViewById(R.id.user_color_thirteen);
        clr_img_tick13=(ImageView) findViewById(R.id.user_color_thirteen_select);

        clr_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img1.setVisibility(View.GONE);
                clr_img_tick1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color1/color1.jpg");
            }
        });

        clr_img_tick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img2.setVisibility(View.GONE);
                clr_img_tick2.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color2/color2.jpg");
            }
        });

        clr_img_tick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img3.setVisibility(View.GONE);
                clr_img_tick3.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color3/color3.jpg");

            }
        });

        clr_img_tick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img4.setVisibility(View.GONE);
                clr_img_tick4.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color4/color4.jpg");
            }
        });

        clr_img_tick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img5.setVisibility(View.GONE);
                clr_img_tick5.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color5/color5.jpg");
            }
        });

        clr_img_tick5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img6.setVisibility(View.GONE);
                clr_img_tick6.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color6/color6.jpg");
            }
        });

        clr_img_tick6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img7.setVisibility(View.GONE);
                clr_img_tick7.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color7/color7.jpg");
            }
        });

        clr_img_tick7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img8.setVisibility(View.GONE);
                clr_img_tick8.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color8/color8.jpg");
            }
        });

        clr_img_tick8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img9.setVisibility(View.GONE);
                clr_img_tick9.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color9/color9.jpg");
            }
        });

        clr_img_tick9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img10.setVisibility(View.GONE);
                clr_img_tick10.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color10/color10.jpg");
            }
        });

        clr_img_tick10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img11.setVisibility(View.GONE);
                clr_img_tick11.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color11/color11.jpg");
            }
        });

        clr_img_tick11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });



        clr_img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img12.setVisibility(View.GONE);
                clr_img_tick12.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("color12/color12.jpg");
            }
        });

        clr_img_tick12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                color_edit.setText("");
            }
        });


        clr_img13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img13.setVisibility(View.GONE);
                clr_img_tick13.setVisibility(View.VISIBLE);
                clr_img_tick1.setVisibility(View.GONE);
                clr_img1.setVisibility(View.VISIBLE);
                clr_img_tick2.setVisibility(View.GONE);
                clr_img2.setVisibility(View.VISIBLE);
                clr_img_tick3.setVisibility(View.GONE);
                clr_img3.setVisibility(View.VISIBLE);
                clr_img_tick4.setVisibility(View.GONE);
                clr_img4.setVisibility(View.VISIBLE);
                clr_img_tick5.setVisibility(View.GONE);
                clr_img5.setVisibility(View.VISIBLE);
                clr_img_tick6.setVisibility(View.GONE);
                clr_img6.setVisibility(View.VISIBLE);
                clr_img_tick7.setVisibility(View.GONE);
                clr_img7.setVisibility(View.VISIBLE);
                clr_img_tick8.setVisibility(View.GONE);
                clr_img8.setVisibility(View.VISIBLE);
                clr_img_tick9.setVisibility(View.GONE);
                clr_img9.setVisibility(View.VISIBLE);
                clr_img_tick10.setVisibility(View.GONE);
                clr_img10.setVisibility(View.VISIBLE);
                clr_img_tick11.setVisibility(View.GONE);
                clr_img11.setVisibility(View.VISIBLE);
                clr_img_tick12.setVisibility(View.GONE);
                clr_img12.setVisibility(View.VISIBLE);
                color_edit.setText("color13/color13.jpg");
            }
        });

        clr_img_tick13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clr_img_tick13.setVisibility(View.GONE);
                clr_img13.setVisibility(View.VISIBLE);
                color_edit.setText("");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_account_information_change, menu);
        return true;
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
            String colorGetTheme = color_edit.getText().toString();
            if(colorGetTheme.trim().length()>0)
            {
                new BackgroundChangeUser(userSessionid,colorGetTheme).execute();

            }
        }

        return super.onOptionsItemSelected(item);
    }

    public class BackgroundChangeUser extends AsyncTask<Void,Void,Void>{

        String useridGet,colorGet;
        ProgressDialog progressDialog;

        public BackgroundChangeUser(String useridGet,String colorGet){
            this.useridGet = useridGet;
            this.colorGet = colorGet;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitUserBackgroundOption.this,"","Change background...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            startActivity(new Intent(WallnitUserBackgroundOption.this,Wall.class));
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wallnitUserColorImageThemeUpdate.userColorThemeUpdate(useridGet,colorGet);
            return null;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_clr_home) {
            startActivity(new Intent(WallnitUserBackgroundOption.this,Wall.class));
            finish();
        }
        else if (id == R.id.nav_clr_image_theme) {
            startActivity(new Intent(WallnitUserBackgroundOption.this,WallnitUserBackgroundImageTheme.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
