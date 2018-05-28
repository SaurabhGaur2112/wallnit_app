package com.wallnit.wallnitlogin.WallnitUserOwnProfilePost;

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
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;
import com.wallnit.wallnitlogin.WallnitUserEditBlog;
import com.wallnit.wallnitlogin.WallnitUserEditLink;
import com.wallnit.wallnitlogin.WallnitUserEditPhotos;
import com.wallnit.wallnitlogin.WallnitUserEditText;
import com.wallnit.wallnitlogin.WallnitUserOwnPostDelete.WallnitUserPostDelete;
import com.wallnit.wallnitlogin.WallnitUserPostLike.WallnitUserAllPostLike;
import com.wallnit.wallnitlogin.WallnitUserPostLike.WallnitUserAllPostUnlike;
import com.wallnit.wallnitlogin.WallnitUserPostLikeList;
import com.wallnit.wallnitlogin.WallnitUserPostReport;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Saurabh Gaur on 1/16/2017.
 */
public class WallnitProfileFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitProfileFeedItem> wallnitProfileFeedItems;
    WallnitUserAllPostLike wallnitUserAllPostLike;
    WallnitUserAllPostUnlike wallnitUserAllPostUnlike;
    WallnitUserPostDelete wallnitUserPostDelete;


    public WallnitProfileFeedListAdapter(Activity activity, List<WallnitProfileFeedItem> wallnitProfileFeedItems){
        this.activity = activity;
        this.wallnitProfileFeedItems = wallnitProfileFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitProfileFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitProfileFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_profileown_post, null);

        final LinearLayout layoutSet_profile = (LinearLayout) convertView.findViewById(R.id.layoutset_profile);
        TextView txtPost = (TextView) convertView.findViewById(R.id.txtPost_profile);
        TextView txtLink = (TextView) convertView.findViewById(R.id.txtLink_profile);
        TextView txtLinkDesc = (TextView) convertView.findViewById(R.id.txtLinkDesc_profile);
        TextView txtBlogTitle = (TextView) convertView.findViewById(R.id.txtBlogTitle_profile);
        TextView txtBlogBody = (TextView) convertView.findViewById(R.id.txtBlogBody_profile);
        ImageView firstImagePost = (ImageView) convertView.findViewById(R.id.firstPostImage_profile);
        ImageView secondImagePost = (ImageView) convertView.findViewById(R.id.secondPostImage_profile);
        ImageView thirdImagePost = (ImageView) convertView.findViewById(R.id.thirdPostImage_profile);
        ImageView fourthImageView = (ImageView) convertView.findViewById(R.id.fourthPostImage_profile);
        ImageView fifthImageView = (ImageView) convertView.findViewById(R.id.fifthPostImage_profile);
        ImageView sixthImageView = (ImageView) convertView.findViewById(R.id.sixthPostImage_profile);
        ImageView seventhImageView = (ImageView) convertView.findViewById(R.id.seventhPostImage_profile);
        ImageView eighthImageView = (ImageView) convertView.findViewById(R.id.eighthPostImage_profile);
        ImageView ninthImageView = (ImageView) convertView.findViewById(R.id.ninthPostImage_profile);
        ImageView tenthImageView = (ImageView) convertView.findViewById(R.id.tenthPostImage_profile);

        TextView txtCaption = (TextView) convertView.findViewById(R.id.txtCaption_profile);
        final TextView txtTotalNumLike = (TextView) convertView.findViewById(R.id.profile_totalNumLike);

        final ImageView postUnlike = (ImageView) convertView.findViewById(R.id.profile_postunlike);
        final ImageView postLike = (ImageView) convertView.findViewById(R.id.profile_postlike);
        ImageView postComment = (ImageView) convertView.findViewById(R.id.profile_postcomment);
        ImageView postDelete = (ImageView) convertView.findViewById(R.id.profile_postdelete);
        ImageView postEdit = (ImageView) convertView.findViewById(R.id.profile_postedit);
        ImageView postReport = (ImageView) convertView.findViewById(R.id.profile_postreport);

        wallnitUserAllPostLike = new WallnitUserAllPostLike();
        wallnitUserAllPostUnlike = new WallnitUserAllPostUnlike();
        wallnitUserPostDelete = new WallnitUserPostDelete();

        final WallnitProfileFeedItem wallnitProfileFeedItem = wallnitProfileFeedItems.get(position);

        if(!wallnitProfileFeedItem.getTextPost().equals("null"))
        {
            txtPost.setText(wallnitProfileFeedItem.getTextPost());
            txtPost.setVisibility(View.VISIBLE);

            postDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layoutSet_profile.setVisibility(View.GONE);
                    wallnitUserPostDelete.userPostDeleteNumber(wallnitProfileFeedItem.getPostNumber());
                }
            });

            postEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, WallnitUserEditText.class);
                    String postNumber = wallnitProfileFeedItem.getPostNumber();
                    i.putExtra("edittext_number_post",postNumber);
                    activity.startActivity(i);
                }
            });

        } else {
            txtPost.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getLinkPost().equals("null"))
        {
            txtLink.setText(wallnitProfileFeedItem.getLinkPost());
            txtLinkDesc.setText(wallnitProfileFeedItem.getLinkDescPost());
            txtLink.setVisibility(View.VISIBLE);
            txtLinkDesc.setVisibility(View.VISIBLE);

            postDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layoutSet_profile.setVisibility(View.GONE);
                    wallnitUserPostDelete.userPostDeleteNumber(wallnitProfileFeedItem.getPostNumber());
                }
            });

            postEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, WallnitUserEditLink.class);
                    String postNumber = wallnitProfileFeedItem.getPostNumber();
                    i.putExtra("editlink_number_post",postNumber);
                    activity.startActivity(i);
                }
            });


        } else {
            txtLink.setVisibility(View.GONE);
            txtLinkDesc.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getBlogPostBody().equals("null"))
        {
            txtBlogTitle.setText(wallnitProfileFeedItem.getBlogPostTitle());
            txtBlogBody.setText(wallnitProfileFeedItem.getBlogPostBody());
            txtBlogTitle.setVisibility(View.VISIBLE);
            txtBlogBody.setVisibility(View.VISIBLE);

            postDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layoutSet_profile.setVisibility(View.GONE);
                    wallnitUserPostDelete.userPostDeleteNumber(wallnitProfileFeedItem.getPostNumber());
                }
            });

            postEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, WallnitUserEditBlog.class);
                    String postNumber = wallnitProfileFeedItem.getPostNumber();
                    i.putExtra("editblog_number_post",postNumber);
                    activity.startActivity(i);
                }
            });

        } else {
            txtBlogTitle.setVisibility(View.GONE);
            txtBlogBody.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getFirstImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getFirstImage(),firstImagePost);
            firstImagePost.setVisibility(View.VISIBLE);

            postDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layoutSet_profile.setVisibility(View.GONE);
                    wallnitUserPostDelete.userPostDeleteNumber(wallnitProfileFeedItem.getPostNumber());
                }
            });

            postEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, WallnitUserEditPhotos.class);
                    String postNumber = wallnitProfileFeedItem.getPostNumber();
                    i.putExtra("editphotos_number_post",postNumber);
                    activity.startActivity(i);
                }
            });

        } else {
            firstImagePost.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getSecondImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getSecondImage(),secondImagePost);
            secondImagePost.setVisibility(View.VISIBLE);
        } else {
            secondImagePost.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getThirdImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getThirdImage(),thirdImagePost);
            thirdImagePost.setVisibility(View.VISIBLE);
        } else {
            thirdImagePost.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getFourthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getFourthImage(),fourthImageView);
            fourthImageView.setVisibility(View.VISIBLE);
        } else {
            fourthImageView.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getFifthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getFifthImage(),fifthImageView);
            fifthImageView.setVisibility(View.VISIBLE);
        } else {
            fifthImageView.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getSixthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getSixthImage(),sixthImageView);
            sixthImageView.setVisibility(View.VISIBLE);
        } else {
            sixthImageView.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getSeventhImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getSeventhImage(),seventhImageView);
            seventhImageView.setVisibility(View.VISIBLE);
        } else {
            seventhImageView.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getEighthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getEighthImage(),eighthImageView);
            eighthImageView.setVisibility(View.VISIBLE);
        } else {
            eighthImageView.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getNinthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getNinthImage(),ninthImageView);
            ninthImageView.setVisibility(View.VISIBLE);
        } else {
            ninthImageView.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getTenthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallnitProfileFeedItem.getTenthImage(),tenthImageView);
            tenthImageView.setVisibility(View.VISIBLE);
        } else {
            tenthImageView.setVisibility(View.GONE);
        }

        if(!wallnitProfileFeedItem.getImageCaption().equals("null"))
        {
            txtCaption.setText(wallnitProfileFeedItem.getImageCaption());
            txtCaption.setVisibility(View.VISIBLE);
        } else {
            txtCaption.setVisibility(View.GONE);
        }

        txtTotalNumLike.setText(wallnitProfileFeedItem.getTotalNumberPostLike());

        if(wallnitProfileFeedItem.getPostLikeOrNot().equals("not like"))
        {
            postUnlike.setVisibility(View.VISIBLE);
            postLike.setVisibility(View.GONE);
        } else {
            postUnlike.setVisibility(View.GONE);
            postLike.setVisibility(View.VISIBLE);
        }

        if(wallnitProfileFeedItem.getOwnuserid().equals("user_not_login"))
        {
            postUnlike.setVisibility(View.GONE);
            postLike.setVisibility(View.GONE);
            postComment.setVisibility(View.GONE);
            postDelete.setVisibility(View.GONE);
            postEdit.setVisibility(View.GONE);
            postReport.setVisibility(View.GONE);
        }
        if(!wallnitProfileFeedItem.getOwnuserid().equals(wallnitProfileFeedItem.getOtheruserid()))
        {
            postDelete.setVisibility(View.GONE);
            postEdit.setVisibility(View.GONE);
        }
        if(wallnitProfileFeedItem.getOwnuserid().equals(wallnitProfileFeedItem.getOtheruserid()))
        {
            postReport.setVisibility(View.GONE);
        }

        postLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLike.setVisibility(View.GONE);
                postUnlike.setVisibility(View.VISIBLE);

                Integer total_lk = Integer.parseInt(txtTotalNumLike.getText().toString());
                Integer value_get_totallike = likePostSub(total_lk);
                txtTotalNumLike.setText(Integer.toString(value_get_totallike));

                wallnitUserAllPostUnlike.userPostUnlike(wallnitProfileFeedItem.getPostNumber(),wallnitProfileFeedItem.getOwnuserid());
            }
        });

        postUnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postUnlike.setVisibility(View.GONE);
                postLike.setVisibility(View.VISIBLE);

                Integer total_lk = Integer.parseInt(txtTotalNumLike.getText().toString());
                Integer value_get_totallike = likePostAdd(total_lk);
                txtTotalNumLike.setText(Integer.toString(value_get_totallike));

                wallnitUserAllPostLike.userPostLike(wallnitProfileFeedItem.getPostNumber(),wallnitProfileFeedItem.getOwnuserid());
            }
        });

        postReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getintentPostNumber = new Intent(activity, WallnitUserPostReport.class);
                String postNumber = wallnitProfileFeedItem.getPostNumber();
                getintentPostNumber.putExtra("repost_number_post",postNumber);
                activity.startActivity(getintentPostNumber);
            }
        });

        txtTotalNumLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getintentPostNumber = new Intent(activity, WallnitUserPostLikeList.class);
                String postNumber = wallnitProfileFeedItem.getPostNumber();
                getintentPostNumber.putExtra("likelist_number_post",postNumber);
                activity.startActivity(getintentPostNumber);
            }
        });


        return convertView;
    }

    public Integer likePostAdd(Integer totallke)
    {
        return totallke+1;
    }

    public Integer likePostSub(Integer totalLike)
    {
        return totalLike-1;
    }

}
