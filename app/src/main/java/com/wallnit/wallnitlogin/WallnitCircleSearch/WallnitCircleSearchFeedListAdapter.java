package com.wallnit.wallnitlogin.WallnitCircleSearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wallnit.wallnitlogin.R;
import com.wallnit.wallnitlogin.WallnitCircleOtherProfile;
import com.wallnit.wallnitlogin.WallnitOtherUserProfile;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;

import java.util.List;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitCircleSearchFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitCircleSearchFeedItem> wallnitCircleSearchFeedItems;

    public WallnitCircleSearchFeedListAdapter(Activity activity, List<WallnitCircleSearchFeedItem> wallnitCircleSearchFeedItems){
        this.activity = activity;
        this.wallnitCircleSearchFeedItems = wallnitCircleSearchFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitCircleSearchFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitCircleSearchFeedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.wallnit_circle_search_list, null);

        LinearLayout circleLayout = (LinearLayout) convertView.findViewById(R.id.circleSearchLayout);
        TextView circlename = (TextView) convertView.findViewById(R.id.circleSearch_username);
        ImageView profileImg = (ImageView) convertView.findViewById(R.id.circleSearch_profile);

        final WallnitCircleSearchFeedItem item = wallnitCircleSearchFeedItems.get(position);

        circlename.setText(item.getCircleSearchCirclename());
        PicassoClient.downloadImgae(activity,item.getCircleSearchProfile(),profileImg);

        circleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, WallnitCircleOtherProfile.class);
                String circleIdGet = item.getCircleSearchCircleId();
                i.putExtra("othercircle_profile_userid",circleIdGet);
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
