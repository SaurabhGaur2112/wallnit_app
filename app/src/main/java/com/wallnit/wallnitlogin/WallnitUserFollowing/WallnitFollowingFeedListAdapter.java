package com.wallnit.wallnitlogin.WallnitUserFollowing;

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
public class WallnitFollowingFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitFollowingFeedItem> wallnitFollowingFeedItems;

    public WallnitFollowingFeedListAdapter(Activity activity, List<WallnitFollowingFeedItem> wallnitFollowingFeedItems){
        this.activity = activity;
        this.wallnitFollowingFeedItems = wallnitFollowingFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitFollowingFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitFollowingFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_following, null);

        LinearLayout layout_following = (LinearLayout) convertView.findViewById(R.id.followinglist_layout);
        TextView txtUsername = (TextView) convertView.findViewById(R.id.following_username);
        TextView txtHashtagname = (TextView) convertView.findViewById(R.id.following_tagname);
        ImageView profileImg = (ImageView) convertView.findViewById(R.id.following_profile);

        final WallnitFollowingFeedItem wallnitFollowingFeedItem = wallnitFollowingFeedItems.get(position);

        txtUsername.setText(wallnitFollowingFeedItem.getFollowing_username());
        if(!wallnitFollowingFeedItem.getFollowing_tagname().equals("null"))
        {
            txtHashtagname.setText(wallnitFollowingFeedItem.getFollowing_tagname());
            txtHashtagname.setVisibility(View.VISIBLE);
        } else {
            txtHashtagname.setVisibility(View.GONE);
        }

        PicassoClient.downloadImgae(activity,wallnitFollowingFeedItem.getFollowing_profile(),profileImg);

        layout_following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                String followUserId = wallnitFollowingFeedItem.getFollowing_userid();
                i.putExtra("otheruser_profile_userid",followUserId);
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
