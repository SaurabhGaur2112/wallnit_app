package com.wallnit.wallnitlogin.WallnitCircleLikeNotificationGet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.wallnit.wallnitlogin.R;

import java.util.List;

/**
 * Created by Saurabh Gaur on 12/31/2016.
 */
public class WallnitCircleLikeNotificationCardAdapter extends RecyclerView.Adapter<WallnitCircleLikeNotificationCardAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;

    List<WallnitCircleLikeNotificationList> wallnitCircleLikeNotificationLists;

    public WallnitCircleLikeNotificationCardAdapter(List<WallnitCircleLikeNotificationList> wallnitCircleLikeNotificationLists,Context context) {
        super();
        this.wallnitCircleLikeNotificationLists = wallnitCircleLikeNotificationLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.like_circle_notificationlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final WallnitCircleLikeNotificationList wallnitCircleLikeNotificationList = wallnitCircleLikeNotificationLists.get(position);

        imageLoader = WallnitCircleLikeNotificationCustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(wallnitCircleLikeNotificationList.getNotification_profile(),ImageLoader.getImageListener(holder.imageViewProfile, R.drawable.post_image_background,android.R.drawable.ic_dialog_alert));

        holder.imageViewProfile.setImageUrl(wallnitCircleLikeNotificationList.getNotification_profile(), imageLoader);
        holder.notificationDateTime.setText(wallnitCircleLikeNotificationList.getNotification_datetime());

        if(wallnitCircleLikeNotificationList.getNotification_numlike().equals("more like"))
        {
            holder.notificationUsername.setText(wallnitCircleLikeNotificationList.getNotification_username()+" & "+wallnitCircleLikeNotificationList.getNotification_totalNumLike()+" liked your post");
        }
        if(wallnitCircleLikeNotificationList.getNotification_numlike().equals("single like"))
        {
            holder.notificationUsername.setText(wallnitCircleLikeNotificationList.getNotification_username()+" liked your post");
        }
    }

    @Override
    public int getItemCount() {
        return wallnitCircleLikeNotificationLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public NetworkImageView imageViewProfile;
        public TextView notificationUsername,notificationDateTime;

        public ViewHolder(View itemView){
            super(itemView);

            imageViewProfile = (NetworkImageView) itemView.findViewById(R.id.circleLike_notification_imageView);
            notificationUsername = (TextView) itemView.findViewById(R.id.circleLike_notification_username);
            notificationDateTime = (TextView) itemView.findViewById(R.id.circleLike_notification_date);

        }
    }
}

