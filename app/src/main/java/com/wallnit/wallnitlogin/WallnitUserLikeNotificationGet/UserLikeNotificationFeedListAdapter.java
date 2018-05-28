package com.wallnit.wallnitlogin.WallnitUserLikeNotificationGet;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wallnit.wallnitlogin.R;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;

import java.util.List;

/**
 * Created by Saurabh Gaur on 1/19/2017.
 */
public class UserLikeNotificationFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<UserLikeNotificationFeedItem> userLikeNotificationFeedItems;

    public UserLikeNotificationFeedListAdapter(Activity activity, List<UserLikeNotificationFeedItem> userLikeNotificationFeedItems){
        this.activity = activity;
        this.userLikeNotificationFeedItems = userLikeNotificationFeedItems;
    }

    @Override
    public int getCount() {
        return userLikeNotificationFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return userLikeNotificationFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.like_user_notificationlist, null);

        TextView txtUsername = (TextView) convertView.findViewById(R.id.likeNotification_username);
        TextView txtDateTime = (TextView) convertView.findViewById(R.id.likeNotification_dateTime);
        ImageView profileImg = (ImageView) convertView.findViewById(R.id.likeNotification_profile);

        UserLikeNotificationFeedItem item = userLikeNotificationFeedItems.get(position);

        PicassoClient.downloadImgae(activity,item.getProfileImg(),profileImg);
        txtDateTime.setText(item.getDateTime());

        if(item.getNumLike().equals("single like"))
        {
            txtUsername.setText(item.getUsername() + " liked your post");

        } else {
            txtUsername.setText(item.getUsername() + " liked your post");
        }

        return convertView;
    }
}
