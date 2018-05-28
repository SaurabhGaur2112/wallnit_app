package com.wallnit.wallnitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WallnitUserGeneralInformation extends AppCompatActivity implements View.OnClickListener{
    Spinner sp_month,sp_day,sp_year;
    String month[]={"January","Feburary","March","April","May","June","July","August","September","Octuber","November","December"};
    String day[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String year[]={"2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997","1996","1995",
            "1994","1993","1992","1991","1990","1989","1988","1987","1986","1985","1984","1983","1982","1981","1980","1979","1978","1977","1976","1975","1974","1973","1972",
            "1971","1970","1969","1968","1967","1966","1965","1964","1963","1962","1961","1960","1959","1958","1957","1956","1955","1954","1953","1952","1951","1950","1949",
            "1948","1947","1946","1945","1944","1943","1942","1941","1940","1939","1938","1937","1936","1935","1934","1933","1932","1931","1930","1929","1928","1927","1926",
            "1925","1924","1923","1922","1921","1920"};

    RadioButton rd_male,rd_female,rd_other;
    EditText language_ed,website_ed,gender_ed,month_ed,day_ed,year_ed;
    Session session;
    InputStream is = null;
    String line = null;
    String result = null;
    String temp_gender = "",temp_month = "",temp_day = "",temp_year = "",temp_lang = "",temp_web = "";
    String[] arr_gender,arr_month,arr_day,arr_year,arr_lang,arr_web;
    String set_gender,set_month,set_day,set_year,set_lang,set_web;
    WallnitUserAccountGeneralInfoUpdate wallnitUserAccountGeneralInfoUpdate;
    String sessionuserid;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_user_general_information);
        sp_month = (Spinner) findViewById(R.id.generalinfo_month);
        sp_day = (Spinner) findViewById(R.id.generalinfo_day);
        sp_year = (Spinner) findViewById(R.id.generalinfo_year);
        rd_male = (RadioButton) findViewById(R.id.radio_male);
        rd_female = (RadioButton) findViewById(R.id.radio_female);
        rd_other = (RadioButton) findViewById(R.id.radio_other);
        language_ed = (EditText) findViewById(R.id.language_info);
        website_ed = (EditText) findViewById(R.id.website_info);
        gender_ed = (EditText) findViewById(R.id.set_gender);
        month_ed = (EditText) findViewById(R.id.set_month);
        day_ed = (EditText) findViewById(R.id.set_day);
        year_ed = (EditText) findViewById(R.id.set_year);
        session = new Session(this);

        cd = new ConnectionDetector(this);
        wallnitUserAccountGeneralInfoUpdate = new WallnitUserAccountGeneralInfoUpdate();


        ArrayAdapter<String> month_ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,month);
        ArrayAdapter<String> day_ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,day);
        final ArrayAdapter<String> year_ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,year);
        sp_month.setAdapter(month_ad);
        sp_day.setAdapter(day_ad);
        sp_year.setAdapter(year_ad);

        rd_male.setOnClickListener(this);
        rd_female.setOnClickListener(this);
        rd_other.setOnClickListener(this);
        sp_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month_ed.setText(month[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day_ed.setText(day[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year_ed.setText(year[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sessionuserid = session.getUserSession();
        List<NameValuePair> sendUserId = new ArrayList<NameValuePair>(1);
        sendUserId.add(new BasicNameValuePair("webusersession", sessionuserid));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_user_account_generalinfo_get.php");
            httpPost.setEntity(new UrlEncodedFormEntity(sendUserId));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null)
                sb.append(line + "\n");
            result = sb.toString();
            is.close();
        } catch (Exception e) {

        }
        try {
            JSONArray jArray = new JSONArray(result);
            int count = jArray.length();

            for (int i = 0; i < count; i++) {

                JSONObject json_data = jArray.getJSONObject(i);
                temp_gender += json_data.getString("gender") + ":";
                temp_month += json_data.getString("bmonth") + ":";
                temp_day += json_data.getString("bday") + ":";
                temp_year += json_data.getString("byear") + ":";
                temp_lang += json_data.getString("user_language") + ":";
                temp_web += json_data.getString("user_website") + ":";
            }

            arr_gender = temp_gender.split(":");
            arr_month = temp_month.split(":");
            arr_day = temp_day.split(":");
            arr_year = temp_year.split(":");
            arr_lang = temp_lang.split(":");
            arr_web = temp_web.split(":");

            set_gender = Arrays.toString(arr_gender).replace("[", "").replace("]", "");
            set_month = Arrays.toString(arr_month).replace("[", "").replace("]", "");
            set_day = Arrays.toString(arr_day).replace("[", "").replace("]", "");
            set_year = Arrays.toString(arr_year).replace("[", "").replace("]", "");
            set_lang = Arrays.toString(arr_lang).replace("[", "").replace("]", "");
            set_web = Arrays.toString(arr_web).replace("[", "").replace("]", "");

            gender_ed.setText(set_gender);

            if(set_gender.equals("male"))
            {
                rd_male.setChecked(true);
            }
            else if(set_gender.equals("female"))
            {
                rd_female.setChecked(true);
            }
            else if(set_gender.equals("other"))
            {
                rd_other.setChecked(true);
            }
            else
            {
                rd_male.setChecked(false);
                rd_female.setChecked(false);
                rd_male.setChecked(false);
            }

            month_ed.setText(set_month);
            day_ed.setText(set_day);
            year_ed.setText(set_year);

            language_ed.setText(set_lang);
            website_ed.setText(set_web);


        } catch (Exception e) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_account_information_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.account_infochange)
        {
            if(cd.isConnected())
            {
                String gender,d_month,d_day,d_year,language,website;
                gender = gender_ed.getText().toString();
                d_month = month_ed.getText().toString();
                d_day = day_ed.getText().toString();
                d_year = year_ed.getText().toString();
                language = language_ed.getText().toString();
                website = website_ed.getText().toString();

                wallnitUserAccountGeneralInfoUpdate.userAccountGeneralInfoUpdate(gender,d_month,d_day,d_year,language,website,sessionuserid);
                startActivity(new Intent(WallnitUserGeneralInformation.this,WallnitUserAccountSetting.class));
                finish();
            } else {
                Toast.makeText(WallnitUserGeneralInformation.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }


        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.radio_male:
                gender_ed.setText("male");
                break;
            case R.id.radio_female:
                gender_ed.setText("female");
                break;
            case R.id.radio_other:
                gender_ed.setText("other");
                break;
        }
    }

}
