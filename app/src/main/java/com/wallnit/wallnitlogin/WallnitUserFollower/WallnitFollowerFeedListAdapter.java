package com.wallnit.wallnitlogin.WallnitUserFollower;

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
 * Created by Saurabh Gaur on 1/16/2017.
 */
public class WallnitFollowerFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitFollowerFeedItem> wallnitFollowerFeedItems;

    public WallnitFollowerFeedListAdapter(Activity activity, List<WallnitFollowerFeedItem> wallnitFollowerFeedItems){
        this.activity = activity;
        this.wallnitFollowerFeedItems = wallnitFollowerFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitFollowerFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitFollowerFeedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(converView == null)
            converView = inflater.inflate(R.layout.list_follower, null);

        LinearLayout layoutFollower = (LinearLayout) converView.findViewById(R.id.followerlist_layout);
        TextView username = (TextView) converView.findViewById(R.id.follower_username);
        TextView hashTagname = (TextView) converView.findViewById(R.id.follower_tagname);
        ImageView profileImg = (ImageView) converView.findViewById(R.id.follower_profile);

        final WallnitFollowerFeedItem item = wallnitFollowerFeedItems.get(position);

        username.setText(item.getFollower_username());
        if(!item.getFollower_tagname().equals("null"))
        {
            hashTagname.setText(item.getFollower_tagname());
            hashTagname.setVisibility(View.VISIBLE);
        } else {
            hashTagname.setVisibility(View.GONE);
        }

        PicassoClient.downloadImgae(activity,item.getFollower_profile(),profileImg);

        layoutFollower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                String followUserId = item.getFollower_userid();
                i.putExtra("otheruser_profile_userid",followUserId);
                activity.startActivity(i);
            }
        });

        return converView;
    }
}
