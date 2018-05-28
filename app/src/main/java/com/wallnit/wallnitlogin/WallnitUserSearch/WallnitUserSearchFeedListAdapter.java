package com.wallnit.wallnitlogin.WallnitUserSearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wallnit.wallnitlogin.R;
import com.wallnit.wallnitlogin.WallnitOtherUserProfile;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitUserSearchFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitUserSearchFeedItem> wallnitUserSearchFeedItems;

    public WallnitUserSearchFeedListAdapter(Activity activity,List<WallnitUserSearchFeedItem> wallnitUserSearchFeedItems){
        this.activity = activity;
        this.wallnitUserSearchFeedItems = wallnitUserSearchFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitUserSearchFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitUserSearchFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.wallnit_user_search_list, null);

        LinearLayout layout_searchuser = (LinearLayout) convertView.findViewById(R.id.searchuser_layout);
        TextView username = (TextView) convertView.findViewById(R.id.userSearch_username);
        TextView tagname = (TextView) convertView.findViewById(R.id.userSearch_tagname);
        ImageView profileImg = (ImageView) convertView.findViewById(R.id.userSearch_profile);

        final WallnitUserSearchFeedItem item = wallnitUserSearchFeedItems.get(position);

        username.setText(item.getSearchUserUsername());

        if(!item.getSearchUserTagname().equals("null"))
        {
            tagname.setText(item.getSearchUserTagname());
            tagname.setVisibility(View.VISIBLE);
        } else {
            tagname.setVisibility(View.GONE);
        }

        PicassoClient.downloadImgae(activity,item.getSearchUserProfile(),profileImg);

        layout_searchuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                String followUserId = item.getSearchUserUserId();
                i.putExtra("otheruser_profile_userid",followUserId);
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
