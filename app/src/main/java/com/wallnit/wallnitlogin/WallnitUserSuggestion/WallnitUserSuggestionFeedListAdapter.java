package com.wallnit.wallnitlogin.WallnitUserSuggestion;

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
import com.wallnit.wallnitlogin.WallnitOtherUserProfile;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;

import java.util.List;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitUserSuggestionFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitUserSuggestionFeedItem> wallnitUserSuggestionFeedItems;

    public WallnitUserSuggestionFeedListAdapter(Activity activity, List<WallnitUserSuggestionFeedItem> wallnitUserSuggestionFeedItems){
        this.activity = activity;
        this.wallnitUserSuggestionFeedItems = wallnitUserSuggestionFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitUserSuggestionFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitUserSuggestionFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.user_search_suggestion_list, null);

        LinearLayout layoutUserSuggestion = (LinearLayout) convertView.findViewById(R.id.suggestionList_layout);
        TextView username = (TextView) convertView.findViewById(R.id.userSearchSuggestion_username);
        TextView tagname = (TextView) convertView.findViewById(R.id.userSearchSuggestion_tagname);
        ImageView profileImg = (ImageView) convertView.findViewById(R.id.userSearchSuggestion_profile);

        final WallnitUserSuggestionFeedItem item = wallnitUserSuggestionFeedItems.get(position);

        username.setText(item.getUserSuggestionUsername());
        if(!item.getUserSuggestionTagname().equals("null"))
        {
            tagname.setText(item.getUserSuggestionTagname());
            tagname.setVisibility(View.VISIBLE);
        } else {
            tagname.setVisibility(View.GONE);
        }

        PicassoClient.downloadImgae(activity,item.getUserSuggestionProfile(),profileImg);

        layoutUserSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                String followUserId = item.getUserSuggestionUserid();
                i.putExtra("otheruser_profile_userid",followUserId);
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
