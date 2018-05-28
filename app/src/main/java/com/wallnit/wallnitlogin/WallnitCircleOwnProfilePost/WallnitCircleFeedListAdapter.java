package com.wallnit.wallnitlogin.WallnitCircleOwnProfilePost;

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
import android.widget.Toast;

import com.wallnit.wallnitlogin.R;
import com.wallnit.wallnitlogin.WallnitCircleEditBlog;
import com.wallnit.wallnitlogin.WallnitCircleEditPhotos;
import com.wallnit.wallnitlogin.WallnitCircleEditQuote;
import com.wallnit.wallnitlogin.WallnitPicassoLibrary.PicassoClient;
import com.wallnit.wallnitlogin.WallnitUserEditText;
import com.wallnit.wallnitlogin.WallnitUserOwnPostDelete.WallnitUserPostDelete;
import com.wallnit.wallnitlogin.WallnitUserPostLike.WallnitUserAllPostLike;
import com.wallnit.wallnitlogin.WallnitUserPostLike.WallnitUserAllPostUnlike;
import com.wallnit.wallnitlogin.WallnitUserPostLikeList;
import com.wallnit.wallnitlogin.WallnitUserPostReport;

import java.util.List;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitCircleFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallnitCircleFeedItem> wallnitCircleFeedItems;
    WallnitUserAllPostUnlike wallnitUserAllPostUnlike;
    WallnitUserAllPostLike wallnitUserAllPostLike;
    WallnitUserPostDelete wallnitUserPostDelete;

    public WallnitCircleFeedListAdapter(Activity activity,List<WallnitCircleFeedItem> wallnitCircleFeedItems){
        this.activity = activity;
        this.wallnitCircleFeedItems = wallnitCircleFeedItems;
    }

    @Override
    public int getCount() {
        return wallnitCircleFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallnitCircleFeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_circlepost_ownprofile, null);

        final LinearLayout layoutSetWall = (LinearLayout) convertView.findViewById(R.id.layoutset_wall);
        TextView usernameCirclenameTxt = (TextView) convertView.findViewById(R.id.usernameCirclename_circle);
        TextView hashTagname = (TextView) convertView.findViewById(R.id.hashtagname_circle);
        ImageView profileImg = (ImageView) convertView.findViewById(R.id.profilePic_circle);
        ImageView imageAnonymous = (ImageView) convertView.findViewById(R.id.profilePic_circle_anonymous);
        TextView txtQuotePost = (TextView) convertView.findViewById(R.id.quotePost_circle);
        TextView txtBlogTitlePost = (TextView) convertView.findViewById(R.id.txtBlogTitle_circle);
        TextView txtBlogBodyPost = (TextView) convertView.findViewById(R.id.txtBlogBody_circle);
        ImageView firstImgPost = (ImageView) convertView.findViewById(R.id.firstPostImage_circle);
        ImageView secondImgPost = (ImageView) convertView.findViewById(R.id.secondPostImage_circle);
        ImageView thirdImgPost = (ImageView) convertView.findViewById(R.id.thirdPostImage_circle);
        ImageView fourthImgPost = (ImageView) convertView.findViewById(R.id.fourthPostImage_circle);
        ImageView fifthImgPost = (ImageView) convertView.findViewById(R.id.fifthPostImage_circle);
        ImageView sixthImgPost = (ImageView) convertView.findViewById(R.id.sixthPostImage_circle);
        ImageView seventhImgPost = (ImageView) convertView.findViewById(R.id.seventhPostImage_circle);
        ImageView eighthImgPost = (ImageView) convertView.findViewById(R.id.eighthPostImage_circle);
        ImageView ninthImgPost = (ImageView) convertView.findViewById(R.id.ninthPostImage_circle);
        ImageView tenthImgPost = (ImageView) convertView.findViewById(R.id.tenthPostImage_circle);

        TextView txtCaptionPost = (TextView) convertView.findViewById(R.id.txtCaption_circle);
        final TextView txtTotalNumLike = (TextView) convertView.findViewById(R.id.circle_totalNumLike);

        final ImageView postUnlike = (ImageView) convertView.findViewById(R.id.circle_postunlike);
        final ImageView postLike = (ImageView) convertView.findViewById(R.id.circle_postlike);
        ImageView postComment = (ImageView) convertView.findViewById(R.id.circle_postcomment);
        ImageView postDelete = (ImageView) convertView.findViewById(R.id.circle_postdelete);
        ImageView postEdit = (ImageView) convertView.findViewById(R.id.circle_postedit);
        ImageView postReport = (ImageView) convertView.findViewById(R.id.circle_postreport);

        wallnitUserAllPostUnlike = new WallnitUserAllPostUnlike();
        wallnitUserAllPostLike = new WallnitUserAllPostLike();
        wallnitUserPostDelete = new WallnitUserPostDelete();

        final WallnitCircleFeedItem item = wallnitCircleFeedItems.get(position);

        if(item.getCirclePostCircleUser().equals("0"))
        {
            usernameCirclenameTxt.setText(item.getCirclename());
            PicassoClient.downloadImgae(activity,item.getCircleProfile(),profileImg);

            usernameCirclenameTxt.setVisibility(View.VISIBLE);
            profileImg.setVisibility(View.VISIBLE);
            hashTagname.setVisibility(View.GONE);
            imageAnonymous.setVisibility(View.GONE);
        } else {
            if(item.getCirclePostAnonymoys().equals("0"))
            {
                usernameCirclenameTxt.setText(item.getUsername());
                PicassoClient.downloadImgae(activity,item.getUserProfile(),profileImg);
                hashTagname.setText(item.getTagname());

                usernameCirclenameTxt.setVisibility(View.VISIBLE);
                profileImg.setVisibility(View.VISIBLE);
                hashTagname.setVisibility(View.VISIBLE);
                imageAnonymous.setVisibility(View.GONE);
            } else {
                usernameCirclenameTxt.setText("Anonymous");
                imageAnonymous.setVisibility(View.VISIBLE);

                hashTagname.setVisibility(View.GONE);
                profileImg.setVisibility(View.GONE);
            }
        }

        if(!item.getCirclePostQuote().equals("null"))
        {
            txtQuotePost.setText(item.getCirclePostQuote());
            txtQuotePost.setVisibility(View.VISIBLE);

            postDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layoutSetWall.setVisibility(View.GONE);
                    wallnitUserPostDelete.userPostDeleteNumber(item.getCirclePostNumber());
                }
            });

            postEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, WallnitCircleEditQuote.class);
                    String postNumber = item.getCirclePostNumber();
                    i.putExtra("circle_editQuote_number_post",postNumber);
                    activity.startActivity(i);
                }
            });

        } else {
            txtQuotePost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostBlogBody().equals("null"))
        {
            txtBlogTitlePost.setText(item.getCirclePostBlogTitle());
            txtBlogBodyPost.setText(item.getCirclePostBlogBody());
            txtBlogTitlePost.setVisibility(View.VISIBLE);
            txtBlogBodyPost.setVisibility(View.VISIBLE);

            postDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layoutSetWall.setVisibility(View.GONE);
                    wallnitUserPostDelete.userPostDeleteNumber(item.getCirclePostNumber());
                }
            });

            postEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, WallnitCircleEditBlog.class);
                    String postNumber = item.getCirclePostNumber();
                    i.putExtra("circle_editBlog_number_post",postNumber);
                    activity.startActivity(i);
                }
            });

        } else {
            txtBlogTitlePost.setVisibility(View.GONE);
            txtBlogBodyPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostFirstImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostFirstImage(),firstImgPost);
            firstImgPost.setVisibility(View.VISIBLE);

            postDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layoutSetWall.setVisibility(View.GONE);
                    wallnitUserPostDelete.userPostDeleteNumber(item.getCirclePostNumber());
                }
            });

            postEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, WallnitCircleEditPhotos.class);
                    String postNumber = item.getCirclePostNumber();
                    i.putExtra("circle_editPhotos_number_post",postNumber);
                    activity.startActivity(i);
                }
            });

        } else {
            firstImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostSecondImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostSecondImage(),secondImgPost);
            secondImgPost.setVisibility(View.VISIBLE);
        } else {
            secondImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostThirdImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostThirdImage(),thirdImgPost);
            thirdImgPost.setVisibility(View.VISIBLE);
        } else {
            thirdImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostFourthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostFourthImage(),fourthImgPost);
            fourthImgPost.setVisibility(View.VISIBLE);
        } else {
            fourthImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostFifthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostFifthImage(),fifthImgPost);
            fifthImgPost.setVisibility(View.VISIBLE);
        } else {
            fifthImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostSixthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostSixthImage(),sixthImgPost);
            sixthImgPost.setVisibility(View.VISIBLE);
        } else {
            sixthImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostSeventhImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostSeventhImage(),seventhImgPost);
            seventhImgPost.setVisibility(View.VISIBLE);
        } else {
            seventhImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostEighthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostEighthImage(),eighthImgPost);
            eighthImgPost.setVisibility(View.VISIBLE);
        } else {
            eighthImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostNinthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostNinthImage(),ninthImgPost);
            ninthImgPost.setVisibility(View.VISIBLE);
        } else {
            ninthImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostTenthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,item.getCirclePostTenthImage(),tenthImgPost);
            tenthImgPost.setVisibility(View.VISIBLE);
        } else {
            tenthImgPost.setVisibility(View.GONE);
        }

        if(!item.getCirclePostCaption().equals("null"))
        {
            txtCaptionPost.setText(item.getCirclePostCaption());
            txtCaptionPost.setVisibility(View.VISIBLE);
        } else {
            txtCaptionPost.setVisibility(View.GONE);
        }

        txtTotalNumLike.setText(item.getCirclePostTotalNumLike());

        if(!item.getCirclePostLikeOrNot().equals("not like"))
        {
            postLike.setVisibility(View.VISIBLE);
            postUnlike.setVisibility(View.GONE);
        } else {
            postLike.setVisibility(View.GONE);
            postUnlike.setVisibility(View.VISIBLE);
        }

        if(item.getCircleOtherId().equals("circle_not_login"))
        {
            postDelete.setVisibility(View.GONE);
            postEdit.setVisibility(View.GONE);
        } else {
            if(item.getCircleOwnId().equals(item.getCircleOtherId()))
            {
                postLike.setVisibility(View.GONE);
                postUnlike.setVisibility(View.GONE);
                postComment.setVisibility(View.GONE);
                postReport.setVisibility(View.GONE);
            } else {
                postLike.setVisibility(View.GONE);
                postUnlike.setVisibility(View.GONE);
                postComment.setVisibility(View.GONE);
                postDelete.setVisibility(View.GONE);
                postReport.setVisibility(View.GONE);
                postEdit.setVisibility(View.GONE);
            }
        }

        postLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLike.setVisibility(View.GONE);
                postUnlike.setVisibility(View.VISIBLE);

                Integer total_lk = Integer.parseInt(txtTotalNumLike.getText().toString());
                Integer value_get_totallike = likePostSub(total_lk);
                txtTotalNumLike.setText(Integer.toString(value_get_totallike));

                wallnitUserAllPostUnlike.userPostUnlike(item.getCirclePostNumber(),item.getCirclePostUserOwnId());
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

                wallnitUserAllPostLike.userPostLike(item.getCirclePostNumber(),item.getCirclePostUserOwnId());

            }
        });

        postReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getintentPostNumber = new Intent(activity, WallnitUserPostReport.class);
                String postNumber = item.getCirclePostNumber();
                getintentPostNumber.putExtra("repost_number_post",postNumber);
                activity.startActivity(getintentPostNumber);
            }
        });

        txtTotalNumLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getintentPostNumber = new Intent(activity, WallnitUserPostLikeList.class);
                String postNumber = item.getCirclePostNumber();
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
