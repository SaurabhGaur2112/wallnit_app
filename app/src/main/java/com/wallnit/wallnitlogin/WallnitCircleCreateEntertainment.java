package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class WallnitCircleCreateEntertainment extends AppCompatActivity{
    private EditText createCirclename,circleDescription,contentToPost,categoryToPost;
    Spinner circleContentSp,circleCategorySp;
    Session session;
    CircleCreate circleCreate;
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_create_entertainment);
        createCirclename = (EditText) findViewById(R.id.entertainment_circlename);
        circleDescription = (EditText) findViewById(R.id.circle_description_writecreate);
        contentToPost = (EditText) findViewById(R.id.entertainment_contenttopost);
        categoryToPost = (EditText) findViewById(R.id.entertainment_categorytopost);
        circleContentSp = (Spinner) findViewById(R.id.content_to_post_spinner);
        circleCategorySp = (Spinner) findViewById(R.id.category_to_post_spinner);
        session = new Session(this);
        circleCreate = new CircleCreate();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent getbyintent = getIntent();
        String circlenametxt = getbyintent.getStringExtra("putcirclename");
        createCirclename.setText(circlenametxt);

        circleContentSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv1 = (TextView)view;
                contentToPost.setText(tv1.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        circleCategorySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv2 = (TextView)view;
                categoryToPost.setText(tv2.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.publish_entertainment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.entertainment_publishbtn)
        {
            String circleContentToPost,circleCategoryToPost,circleDescriptionWrite;
            circleContentToPost = contentToPost.getText().toString();
            circleCategoryToPost = categoryToPost.getText().toString();
            circleDescriptionWrite = circleDescription.getText().toString();
            String circlesessionid = session.getCircleSession();

            circleCreate.createCircleSendValues(circlesessionid,circleContentToPost,circleCategoryToPost,circleDescriptionWrite);

            session.setCircleCreateCircle("1");
            Intent intent = new Intent(getApplicationContext(), WallnitCircleProfile.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }
        return false;
    }

}
