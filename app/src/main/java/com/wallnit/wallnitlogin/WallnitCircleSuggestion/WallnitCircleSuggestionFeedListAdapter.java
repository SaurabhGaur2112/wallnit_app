package com.wallnit.wallnitlogin.WallnitCircleSuggestion;

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
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;

import java.util.List;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitCircleSuggestionFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitCircleSuggestionFeedItem> wallnitCircleSuggestionFeedItems;

    public WallnitCircleSuggestionFeedListAdapter(Activity activity, List<WallnitCircleSuggestionFeedItem> wallnitCircleSuggestionFeedItems){
        this.activity = activity;
        this.wallnitCircleSuggestionFeedItems = wallnitCircleSuggestionFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitCircleSuggestionFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitCircleSuggestionFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.circle_search_suggestion_list, null);

        LinearLayout layoutCircle = (LinearLayout) convertView.findViewById(R.id.circleSearchSuggestionLayout);
        TextView circlename = (TextView) convertView.findViewById(R.id.circleSuggestionSearch_username);
        ImageView profileImg = (ImageView) convertView.findViewById(R.id.circleSuggestionSearch_profile);

        final WallnitCircleSuggestionFeedItem item = wallnitCircleSuggestionFeedItems.get(position);

        circlename.setText(item.getCircleSuggestionCirclename());

        PicassoClient.downloadImgae(activity,item.getCircleSuggestionProfile(),profileImg);

        layoutCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, WallnitCircleOtherProfile.class);
                String circleIdGet = item.getCircleSuggestionCircleId();
                i.putExtra("othercircle_profile_userid",circleIdGet);
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
