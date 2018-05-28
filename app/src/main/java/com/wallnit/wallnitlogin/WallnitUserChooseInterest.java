package com.wallnit.wallnitlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WallnitUserChooseInterest extends AppCompatActivity{

    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,
    img18,img19,img20,img21,img22,img23,img24,img25,img_sel1,img_sel2,img_sel3,img_sel4,img_sel5,img_sel6,
    img_sel7,img_sel8,img_sel9,img_sel10,img_sel11,img_sel12,img_sel13,img_sel14,img_sel15,img_sel16,img_sel17,
    img_sel18,img_sel19,img_sel20,img_sel21,img_sel22,img_sel23,img_sel24,img_sel25;

    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16,e17,e18,e19,e20,e21,e22,e23,e24,e25;
    Session session;

    Button chooseInterestButton;
    String usersessionidget;
    InputStream is = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_user_choose_interest);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        img1=(ImageView) findViewById(R.id.interest_movies);
        img_sel1=(ImageView) findViewById(R.id.interest_movies_select);
        e1=(EditText) findViewById(R.id.edittext_first);
        chooseInterestButton=(Button) findViewById(R.id.interest_next_option);
        session = new Session(this);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img1.setVisibility(View.GONE);
                img_sel1.setVisibility(View.VISIBLE);
                e1.setText("movies");
            }
        });



        img_sel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel1.setVisibility(View.GONE);
                img1.setVisibility(View.VISIBLE);
                e1.setText(null);
            }
        });

        img2=(ImageView) findViewById(R.id.interest_actors);
        img_sel2=(ImageView) findViewById(R.id.interest_actors_select);
        e2=(EditText) findViewById(R.id.edittext_second);

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img2.setVisibility(View.GONE);
                img_sel2.setVisibility(View.VISIBLE);
                e2.setText("actors");
            }
        });

        img_sel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel2.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);
                e2.setText(null);
            }
        });

        img3=(ImageView) findViewById(R.id.interest_artists);
        img_sel3=(ImageView) findViewById(R.id.interest_artists_select);
        e3=(EditText) findViewById(R.id.edittext_third);

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img3.setVisibility(View.GONE);
                img_sel3.setVisibility(View.VISIBLE);
                e3.setText("artists");
            }
        });

        img_sel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel3.setVisibility(View.GONE);
                img3.setVisibility(View.VISIBLE);
                e3.setText(null);
            }
        });

        img4=(ImageView) findViewById(R.id.interest_music);
        img_sel4=(ImageView) findViewById(R.id.interest_music_select);
        e4=(EditText) findViewById(R.id.edittext_fourth);

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img4.setVisibility(View.GONE);
                img_sel4.setVisibility(View.VISIBLE);
                e4.setText("music");
            }
        });

        img_sel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel4.setVisibility(View.GONE);
                img4.setVisibility(View.VISIBLE);
                e4.setText(null);
            }
        });

        img5=(ImageView) findViewById(R.id.interest_literature);
        img_sel5=(ImageView) findViewById(R.id.interest_literature_select);
        e5=(EditText) findViewById(R.id.edittext_fifth);

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img5.setVisibility(View.GONE);
                img_sel5.setVisibility(View.VISIBLE);
                e5.setText("literature");
            }
        });

        img_sel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel5.setVisibility(View.GONE);
                img5.setVisibility(View.VISIBLE);
                e5.setText(null);
            }
        });

        img6=(ImageView) findViewById(R.id.interest_dance);
        img_sel6=(ImageView) findViewById(R.id.interest_dance_select);
        e6=(EditText) findViewById(R.id.edittext_sixth);

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img6.setVisibility(View.GONE);
                img_sel6.setVisibility(View.VISIBLE);
                e6.setText("dance");
            }
        });

        img_sel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel6.setVisibility(View.GONE);
                img6.setVisibility(View.VISIBLE);
                e6.setText(null);
            }
        });

        img7=(ImageView) findViewById(R.id.interest_tv_episodes);
        img_sel7=(ImageView) findViewById(R.id.interest_tv_episodes_select);
        e7=(EditText) findViewById(R.id.edittext_seventh);

        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img7.setVisibility(View.GONE);
                img_sel7.setVisibility(View.VISIBLE);
                e7.setText("tv_episodes");
            }
        });

        img_sel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel7.setVisibility(View.GONE);
                img7.setVisibility(View.VISIBLE);
                e7.setText(null);
            }
        });

        img8=(ImageView) findViewById(R.id.interest_humor);
        img_sel8=(ImageView) findViewById(R.id.interest_humor_select);
        e8=(EditText) findViewById(R.id.edittext_eighth);

        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img8.setVisibility(View.GONE);
                img_sel8.setVisibility(View.VISIBLE);
                e8.setText("humor");
            }
        });

        img_sel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel8.setVisibility(View.GONE);
                img8.setVisibility(View.VISIBLE);
                e8.setText(null);
            }
        });

        img9=(ImageView) findViewById(R.id.interest_art);
        img_sel9=(ImageView) findViewById(R.id.interest_art_select);
        e9=(EditText) findViewById(R.id.edittext_ninth);

        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img9.setVisibility(View.GONE);
                img_sel9.setVisibility(View.VISIBLE);
                e9.setText("art");
            }
        });

        img_sel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel9.setVisibility(View.GONE);
                img9.setVisibility(View.VISIBLE);
                e9.setText(null);
            }
        });

        img10=(ImageView) findViewById(R.id.interest_comics);
        img_sel10=(ImageView) findViewById(R.id.interest_comics_select);
        e10=(EditText) findViewById(R.id.edittext_tenth);

        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img10.setVisibility(View.GONE);
                img_sel10.setVisibility(View.VISIBLE);
                e10.setText("comics");
            }
        });

        img_sel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel10.setVisibility(View.GONE);
                img10.setVisibility(View.VISIBLE);
                e10.setText(null);
            }
        });

        img11=(ImageView) findViewById(R.id.interest_yoga);
        img_sel11=(ImageView) findViewById(R.id.interest_yoga_select);
        e11=(EditText) findViewById(R.id.edittext_eleventh);

        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img11.setVisibility(View.GONE);
                img_sel11.setVisibility(View.VISIBLE);
                e11.setText("yoga");
            }
        });

        img_sel11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel11.setVisibility(View.GONE);
                img11.setVisibility(View.VISIBLE);
                e11.setText(null);
            }
        });

        img12=(ImageView) findViewById(R.id.interest_exercise);
        img_sel12=(ImageView) findViewById(R.id.interest_exercise_select);
        e12=(EditText) findViewById(R.id.edittext_twelth);

        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img12.setVisibility(View.GONE);
                img_sel12.setVisibility(View.VISIBLE);
                e12.setText("exercise");
            }
        });

        img_sel12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel12.setVisibility(View.GONE);
                img12.setVisibility(View.VISIBLE);
                e12.setText(null);
            }
        });

        img13=(ImageView) findViewById(R.id.interest_sports);
        img_sel13=(ImageView) findViewById(R.id.interest_sports_select);
        e13=(EditText) findViewById(R.id.edittext_thirteen);

        img13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img13.setVisibility(View.GONE);
                img_sel13.setVisibility(View.VISIBLE);
                e13.setText("sports");
            }
        });

        img_sel13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel13.setVisibility(View.GONE);
                img13.setVisibility(View.VISIBLE);
                e13.setText(null);
            }
        });

        img14=(ImageView) findViewById(R.id.interest_history);
        img_sel14=(ImageView) findViewById(R.id.interest_history_select);
        e14=(EditText) findViewById(R.id.edittext_fourteen);

        img14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img14.setVisibility(View.GONE);
                img_sel14.setVisibility(View.VISIBLE);
                e14.setText("history");
            }
        });

        img_sel14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel14.setVisibility(View.GONE);
                img14.setVisibility(View.VISIBLE);
                e14.setText(null);
            }
        });

        img15=(ImageView) findViewById(R.id.interest_magazines);
        img_sel15=(ImageView) findViewById(R.id.interest_magazines_select);
        e15=(EditText) findViewById(R.id.edittext_fifteen);

        img15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img15.setVisibility(View.GONE);
                img_sel15.setVisibility(View.VISIBLE);
                e15.setText("magazines");
            }
        });

        img_sel15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel15.setVisibility(View.GONE);
                img15.setVisibility(View.VISIBLE);
                e15.setText(null);
            }
        });

        img16=(ImageView) findViewById(R.id.interest_news);
        img_sel16=(ImageView) findViewById(R.id.interest_news_select);
        e16=(EditText) findViewById(R.id.edittext_sixteen);

        img16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img16.setVisibility(View.GONE);
                img_sel16.setVisibility(View.VISIBLE);
                e16.setText("news");
            }
        });

        img_sel16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel16.setVisibility(View.GONE);
                img16.setVisibility(View.VISIBLE);
                e16.setText(null);
            }
        });

        img17=(ImageView) findViewById(R.id.interest_education);
        img_sel17=(ImageView) findViewById(R.id.interest_education_select);
        e17=(EditText) findViewById(R.id.edittext_seventeen);

        img17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img17.setVisibility(View.GONE);
                img_sel17.setVisibility(View.VISIBLE);
                e17.setText("education");
            }
        });

        img_sel17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel17.setVisibility(View.GONE);
                img17.setVisibility(View.VISIBLE);
                e17.setText(null);
            }
        });

        img18=(ImageView) findViewById(R.id.interest_science);
        img_sel18=(ImageView) findViewById(R.id.interest_science_select);
        e18=(EditText) findViewById(R.id.edittext_eighteen);

        img18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img18.setVisibility(View.GONE);
                img_sel18.setVisibility(View.VISIBLE);
                e18.setText("science");
            }
        });

        img_sel18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel18.setVisibility(View.GONE);
                img18.setVisibility(View.VISIBLE);
                e18.setText(null);
            }
        });

        img19=(ImageView) findViewById(R.id.interest_technology);
        img_sel19=(ImageView) findViewById(R.id.interest_technology_select);
        e19=(EditText) findViewById(R.id.edittext_ninteen);

        img19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img19.setVisibility(View.GONE);
                img_sel19.setVisibility(View.VISIBLE);
                e19.setText("technology");
            }
        });

        img_sel19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel19.setVisibility(View.GONE);
                img19.setVisibility(View.VISIBLE);
                e19.setText(null);
            }
        });

        img20=(ImageView) findViewById(R.id.interest_startup);
        img_sel20=(ImageView) findViewById(R.id.interest_startup_select);
        e20=(EditText) findViewById(R.id.edittext_twenty);

        img20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img20.setVisibility(View.GONE);
                img_sel20.setVisibility(View.VISIBLE);
                e20.setText("startup");
            }
        });

        img_sel20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel20.setVisibility(View.GONE);
                img20.setVisibility(View.VISIBLE);
                e20.setText(null);
            }
        });

        img21=(ImageView) findViewById(R.id.interest_entrepreneur);
        img_sel21=(ImageView) findViewById(R.id.interest_entrepreneur_select);
        e21=(EditText) findViewById(R.id.edittext_twentyone);

        img21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img21.setVisibility(View.GONE);
                img_sel21.setVisibility(View.VISIBLE);
                e21.setText("entrepreneur");
            }
        });

        img_sel21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel21.setVisibility(View.GONE);
                img21.setVisibility(View.VISIBLE);
                e21.setText(null);
            }
        });

        img22=(ImageView) findViewById(R.id.interest_fashion);
        img_sel22=(ImageView) findViewById(R.id.interest_fashion_select);
        e22=(EditText) findViewById(R.id.edittext_twentytwo);

        img22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img22.setVisibility(View.GONE);
                img_sel22.setVisibility(View.VISIBLE);
                e22.setText("fashion");
            }
        });

        img_sel22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel22.setVisibility(View.GONE);
                img22.setVisibility(View.VISIBLE);
                e22.setText(null);
            }
        });

        img23=(ImageView) findViewById(R.id.interest_travel);
        img_sel23=(ImageView) findViewById(R.id.interest_travel_select);
        e23=(EditText) findViewById(R.id.edittext_twentythree);

        img23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img23.setVisibility(View.GONE);
                img_sel23.setVisibility(View.VISIBLE);
                e23.setText("travel");
            }
        });

        img_sel23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel23.setVisibility(View.GONE);
                img23.setVisibility(View.VISIBLE);
                e23.setText(null);
            }
        });

        img24=(ImageView) findViewById(R.id.interest_culture);
        img_sel24=(ImageView) findViewById(R.id.interest_culture_select);
        e24=(EditText) findViewById(R.id.edittext_twentyfour);

        img24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img24.setVisibility(View.GONE);
                img_sel24.setVisibility(View.VISIBLE);
                e24.setText("culture");
            }
        });

        img_sel24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel24.setVisibility(View.GONE);
                img24.setVisibility(View.VISIBLE);
                e24.setText(null);
            }
        });

        img25=(ImageView) findViewById(R.id.interest_photography);
        img_sel25=(ImageView) findViewById(R.id.interest_photography_select);
        e25=(EditText) findViewById(R.id.edittext_twentyfifth);

        img25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img25.setVisibility(View.GONE);
                img_sel25.setVisibility(View.VISIBLE);
                e25.setText("photography");
            }
        });

        img_sel25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_sel25.setVisibility(View.GONE);
                img25.setVisibility(View.VISIBLE);
                e25.setText(null);
            }
        });



        chooseInterestButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String interest1,interest2,interest3,interest4,interest5,interest6,interest7,interest8,interest9,interest10,interest11,interest12
                        ,interest13,interest14,interest15,interest16,interest17,interest18,interest19,interest20,interest21,interest22,interest23,interest24
                        ,interest25;

                interest1 = e1.getText().toString();
                interest2 = e2.getText().toString();
                interest3 = e3.getText().toString();
                interest4 = e4.getText().toString();
                interest5 = e5.getText().toString();
                interest6 = e6.getText().toString();
                interest7 = e7.getText().toString();
                interest8 = e8.getText().toString();
                interest9 = e9.getText().toString();
                interest10 = e10.getText().toString();
                interest11 = e11.getText().toString();
                interest12 = e12.getText().toString();
                interest13 = e13.getText().toString();
                interest14 = e14.getText().toString();
                interest15 = e15.getText().toString();
                interest16 = e16.getText().toString();
                interest17 = e17.getText().toString();
                interest18 = e18.getText().toString();
                interest19 = e19.getText().toString();
                interest20 = e20.getText().toString();
                interest21 = e21.getText().toString();
                interest22 = e22.getText().toString();
                interest23 = e23.getText().toString();
                interest24 = e24.getText().toString();
                interest25 = e25.getText().toString();

                String usersessionid = session.getUserSession();

                if(interest1.trim().length()==0 && interest2.trim().length()==0 && interest3.trim().length()==0 && interest4.trim().length()==0 && interest5.trim().length()==0 &&
                        interest6.trim().length()==0 && interest7.trim().length()==0 && interest8.trim().length()==0 && interest9.trim().length()==0 && interest10.trim().length()==0 &&
                        interest11.trim().length()==0 && interest12.trim().length()==0 && interest13.trim().length()==0 && interest14.trim().length()==0 &&
                        interest15.trim().length()==0 && interest16.trim().length()==0 && interest17.trim().length()==0 && interest18.trim().length()==0 &&
                        interest19.trim().length()==0 && interest20.trim().length()==0 && interest21.trim().length()==0 && interest22.trim().length()==0 &&
                        interest23.trim().length()==0 && interest24.trim().length()==0 && interest25.trim().length()==0)
                {
                    Toast.makeText(WallnitUserChooseInterest.this, "Please choose any one", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new SendInterest(usersessionid,interest1,interest2,interest3,interest4,interest5,interest6,interest7,interest8,interest9,interest10,interest11,interest12,interest13,interest14,
                            interest15,interest16,interest17,interest18,interest19,interest20,interest21,interest22,interest23,interest24,interest25).execute();

                }

            }
        });

    }
        class SendInterest extends AsyncTask<Void,Void,Void>{

            ProgressDialog loading;
            String userid,interest1,interest2,interest3,interest4,interest5,interest6,interest7,interest8,interest9,interest10,
            interest11,interest12,interest13,interest14,interest15,interest16,interest17,interest18,interest19,interest20,interest21,
            interest22,interest23,interest24,interest25;

            public SendInterest(String userid,String interest1,String interest2,String interest3,String interest4,String interest5,
                                          String interest6,String interest7,String interest8,String interest9,String interest10,String interest11,
                                          String interest12,String interest13,String interest14,String interest15,String interest16,String interest17,
                                          String interest18,String interest19,String interest20,String interest21,String interest22,String interest23,
                                          String interest24,String interest25){

                this.userid = userid;
                this.interest1 = interest1;
                this.interest2 = interest2;
                this.interest3 = interest3;
                this.interest4 = interest4;
                this.interest5 = interest5;
                this.interest6 = interest6;
                this.interest7 = interest7;
                this.interest8 = interest8;
                this.interest9 = interest9;
                this.interest10 = interest10;
                this.interest11 = interest11;
                this.interest12 = interest12;
                this.interest13 = interest13;
                this.interest14 = interest14;
                this.interest15 = interest15;
                this.interest16 = interest16;
                this.interest17 = interest17;
                this.interest18 = interest18;
                this.interest19 = interest19;
                this.interest20 = interest20;
                this.interest21 = interest21;
                this.interest22 = interest22;
                this.interest23 = interest23;
                this.interest24 = interest24;
                this.interest25 = interest25;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(WallnitUserChooseInterest.this,"","Please wait...",false,false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                List<NameValuePair> interestChooseNameSend = new ArrayList<NameValuePair>(1);
                interestChooseNameSend.add(new BasicNameValuePair("usersession",userid));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestmovies",interest1));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestactors",interest2));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestartists",interest3));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestmusic",interest4));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestliterature",interest5));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestdance",interest6));
                interestChooseNameSend.add(new BasicNameValuePair("txtinteresttvepisodes",interest7));
                interestChooseNameSend.add(new BasicNameValuePair("txtinteresthumor",interest8));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestart",interest9));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestcomics",interest10));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestyoga",interest11));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestexercise",interest12));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestsports",interest13));
                interestChooseNameSend.add(new BasicNameValuePair("txtinteresthistory",interest14));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestmagazine",interest15));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestnews",interest16));
                interestChooseNameSend.add(new BasicNameValuePair("txtinteresteducation",interest17));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestscience",interest18));
                interestChooseNameSend.add(new BasicNameValuePair("txtinteresttechnology",interest19));
                interestChooseNameSend.add(new BasicNameValuePair("txtintereststartup",interest20));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestentrepreneur",interest21));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestfashion",interest22));
                interestChooseNameSend.add(new BasicNameValuePair("txtinteresttravel",interest23));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestculture",interest24));
                interestChooseNameSend.add(new BasicNameValuePair("txtinterestphotography",interest25));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_choose_interest.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(interestChooseNameSend));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }catch (Exception e){

                }

                List<NameValuePair> circleJoinInterest = new ArrayList<NameValuePair>(1);
                circleJoinInterest.add(new BasicNameValuePair("usersession",userid));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_interest_previous.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(circleJoinInterest));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }catch (Exception e){

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute(avoid);
                loading.dismiss();
                session.setUserInterestChoose("1");
                Intent intent = new Intent(getApplicationContext(), Wall.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }
}
