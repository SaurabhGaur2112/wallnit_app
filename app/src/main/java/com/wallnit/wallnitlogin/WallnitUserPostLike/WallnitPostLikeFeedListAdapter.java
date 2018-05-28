package com.wallnit.wallnitlogin.WallnitUserPostLike;

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
public class WallnitPostLikeFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitPostLikeFeedItem> wallnitPostLikeFeedItems;

    public WallnitPostLikeFeedListAdapter(Activity activity, List<WallnitPostLikeFeedItem> wallnitPostLikeFeedItems){
        this.activity = activity;
        this.wallnitPostLikeFeedItems = wallnitPostLikeFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitPostLikeFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitPostLikeFeedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convetView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convetView == null)
            convetView = inflater.inflate(R.layout.userpostlike_list, null);

        LinearLayout layout_likelist = (LinearLayout) convetView.findViewById(R.id.likelist_layout);
        TextView username = (TextView) convetView.findViewById(R.id.likelist_username);
        TextView tagname = (TextView) convetView.findViewById(R.id.likelist_tagname);
        ImageView profileImg = (ImageView) convetView.findViewById(R.id.likelist_profile);

        final WallnitPostLikeFeedItem item = wallnitPostLikeFeedItems.get(position);

        username.setText(item.getPostlike_username());
        if(!item.getPostlike_tagname().equals("null"))
        {
            tagname.setText(item.getPostlike_tagname());
            tagname.setVisibility(View.VISIBLE);
        } else {
            tagname.setVisibility(View.GONE);
        }

        PicassoClient.downloadImgae(activity,item.getPostlike_profile(),profileImg);

        layout_likelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                String followUserId = item.getPostlike_userid();
                i.putExtra("otheruser_profile_userid",followUserId);
                activity.startActivity(i);
            }
        });


        return convetView;
    }
}
