package com.wallnit.wallnitlogin;

import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.wallnit.wallnitlogin.WallnitCircleLikeNotificationGet.WallnitCircleLikeNotificationCardAdapter;
import com.wallnit.wallnitlogin.WallnitCircleLikeNotificationGet.WallnitCircleLikeNotificationConfig;
import com.wallnit.wallnitlogin.WallnitCircleLikeNotificationGet.WallnitCircleLikeNotificationList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WallnitCircleLikeNotification extends AppCompatActivity {

    private List<WallnitCircleLikeNotificationList> wallnitCircleLikeNotificationLists;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private RequestQueue requestQueue;

    private int requestCount = 1;

    Session session;
    String sessionCircleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallnit_circle_like_notification);
        session = new Session(this);

        sessionCircleId = session.getCircleSession();

        recyclerView = (RecyclerView) findViewById(R.id.circle_notificationLike_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        wallnitCircleLikeNotificationLists = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        getData();

        adapter = new WallnitCircleLikeNotificationCardAdapter(wallnitCircleLikeNotificationLists, this);

        recyclerView.setAdapter(adapter);
    }

    private JsonArrayRequest getDataFromServer(int requestCount){
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.circle_notificationLike_progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000, 0x303F9F ));
        progressBar.setVisibility(View.VISIBLE);
        setProgressBarIndeterminateVisibility(true);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(WallnitCircleLikeNotificationConfig.DATA_URL + String.valueOf(requestCount)+WallnitCircleLikeNotificationConfig.DATA_URL_SEND_ID + String.valueOf(sessionCircleId), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseData(response);
                progressBar.setVisibility(View.GONE);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(WallnitCircleLikeNotification.this, "no data more", Toast.LENGTH_SHORT).show();
                    }
                });
        return jsonArrayRequest;
    }

    private void getData(){
        requestQueue.add(getDataFromServer(requestCount));
        requestCount++;
    }

    private void parseData(JSONArray array){
        for(int i=0;i<array.length();i++){
            WallnitCircleLikeNotificationList wallnitCircleLikeNotificationList = new WallnitCircleLikeNotificationList();
            JSONObject json = null;
            try{
                json = array.getJSONObject(i);

                wallnitCircleLikeNotificationList.setNotification_profile(json.getString(WallnitCircleLikeNotificationConfig.TAG_IMAGE_URL));
                wallnitCircleLikeNotificationList.setNotification_username(json.getString(WallnitCircleLikeNotificationConfig.TAG_USERNAME));
                wallnitCircleLikeNotificationList.setNotification_datetime(json.getString(WallnitCircleLikeNotificationConfig.TAG_DATETIME));
                wallnitCircleLikeNotificationList.setNotification_numlike(json.getString(WallnitCircleLikeNotificationConfig.TAG_NUM_LIKE));
                wallnitCircleLikeNotificationList.setNotification_totalNumLike(json.getString(WallnitCircleLikeNotificationConfig.TAG_TOTAL_NUM_LIKE));


            }catch (JSONException e){
                e.printStackTrace();
            }
            wallnitCircleLikeNotificationLists.add(wallnitCircleLikeNotificationList);
        }
        adapter.notifyDataSetChanged();
    }
}
