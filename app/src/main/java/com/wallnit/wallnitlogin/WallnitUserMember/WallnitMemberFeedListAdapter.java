package com.wallnit.wallnitlogin.WallnitUserMember;

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
public class WallnitMemberFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitMemberFeedItem> wallnitMemberFeedItems;

    public WallnitMemberFeedListAdapter(Activity activity, List<WallnitMemberFeedItem> wallnitMemberFeedItems){
        this.activity = activity;
        this.wallnitMemberFeedItems = wallnitMemberFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitMemberFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitMemberFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_member, null);

        LinearLayout layoutMember = (LinearLayout) convertView.findViewById(R.id.memberlist_userlayout);
        TextView memberUsername = (TextView) convertView.findViewById(R.id.member_username);
        TextView memberTagname = (TextView) convertView.findViewById(R.id.member_tagname);
        ImageView memberProfile = (ImageView) convertView.findViewById(R.id.member_profile);

        final WallnitMemberFeedItem item = wallnitMemberFeedItems.get(position);


        memberUsername.setText(item.getMember_username());
        if(!item.getMember_tagname().equals("null"))
        {
            memberTagname.setText(item.getMember_tagname());
            memberTagname.setVisibility(View.VISIBLE);
        } else {
            memberTagname.setVisibility(View.GONE);
        }

        PicassoClient.downloadImgae(activity,item.getMember_profile(),memberProfile);

        layoutMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                String followUserId = item.getMember_userid();
                i.putExtra("otheruser_profile_userid",followUserId);
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
