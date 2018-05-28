package com.wallnit.wallnitlogin.WallnitUserWallPost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.R;
import com.wallnit.wallnitlogin.Wall;
import com.wallnit.wallnitlogin.WallnitCircleOtherProfile;
import com.wallnit.wallnitlogin.WallnitOtherUserProfile;
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

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Saurabh Gaur on 1/15/2017.
 */
public class WallUserFeedListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<WallUserFeedItem> wallUserFeedItems;
    WallnitUserAllPostLike wallnitUserAllPostLike;
    WallnitUserAllPostUnlike wallnitUserAllPostUnlike;
    WallnitUserPostDelete wallnitUserPostDelete;
    private SwipeRefreshLayout swipeRefreshLayout;
    int click_count = 0;

    public WallUserFeedListAdapter(Activity activity, List<WallUserFeedItem> wallUserFeedItems, SwipeRefreshLayout swipeRefreshLayout){
        this.activity = activity;
        this.wallUserFeedItems = wallUserFeedItems;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    public int getCount() {
        return wallUserFeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return wallUserFeedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView = inflater.inflate(R.layout.wallnit_wall_post, null);

        wallnitUserAllPostLike = new WallnitUserAllPostLike();
        wallnitUserAllPostUnlike = new WallnitUserAllPostUnlike();
        wallnitUserPostDelete = new WallnitUserPostDelete();

        final LinearLayout layoutWall = (LinearLayout) convertView.findViewById(R.id.layoutset_wall);
        LinearLayout layoutTotalNum = (LinearLayout) convertView.findViewById(R.id.totalNumLikeLayout);
        TextView username = (TextView) convertView.findViewById(R.id.usernameCirclename_Wall);
        TextView hashTagName = (TextView) convertView.findViewById(R.id.hashtagname_wall);
        ImageView wallprofile = (ImageView) convertView.findViewById(R.id.profilePic_Wall);
        ImageView wallProfileAnonymous = (ImageView) convertView.findViewById(R.id.profilePic_Wall_anonymous);
        final TextView txtPost = (TextView) convertView.findViewById(R.id.txtPost_Wall);
        TextView txtQuote = (TextView) convertView.findViewById(R.id.quotePost_Wall);
        TextView txtLink = (TextView) convertView.findViewById(R.id.txtLink_Wall);
        TextView txtLinkDesc = (TextView) convertView.findViewById(R.id.txtLinkDesc_Wall);
        TextView txtBlogTitle = (TextView) convertView.findViewById(R.id.txtBlogTitle_Wall);
        TextView txtBlogBody = (TextView) convertView.findViewById(R.id.txtBlogBody_Wall);

        ImageView firstImagePost = (ImageView) convertView.findViewById(R.id.firstPostImage_Wall);
        ImageView secondImagePost = (ImageView) convertView.findViewById(R.id.secondPostImage_Wall);
        ImageView thirdImagePost = (ImageView) convertView.findViewById(R.id.thirdPostImage_Wall);
        ImageView fourthImagePost = (ImageView) convertView.findViewById(R.id.fourthPostImage_Wall);
        ImageView fifthImagePost = (ImageView) convertView.findViewById(R.id.fifthPostImage_Wall);
        ImageView sixthImagePost = (ImageView) convertView.findViewById(R.id.sixthPostImage_Wall);
        ImageView seventhImagePost = (ImageView) convertView.findViewById(R.id.seventhPostImage_Wall);
        ImageView eighthImagePost = (ImageView) convertView.findViewById(R.id.eighthPostImage_Wall);
        ImageView ninthImagePost = (ImageView) convertView.findViewById(R.id.ninthPostImage_Wall);
        ImageView tenthImagePost = (ImageView) convertView.findViewById(R.id.tenthPostImage_Wall);
        TextView imageCaption = (TextView) convertView.findViewById(R.id.txtCaption);
        final TextView totalNumLike = (TextView) convertView.findViewById(R.id.wall_totalNumLike);
        final ImageView likePost = (ImageView) convertView.findViewById(R.id.likeOption_wallPost);
        final ImageView unlikePost = (ImageView) convertView.findViewById(R.id.unlikeOption_wallPost);
        final TextView postOwnOption = (TextView) convertView.findViewById(R.id.wallPost_optionsOwn);
        final TextView postFollowCircleOption = (TextView) convertView.findViewById(R.id.wallPost_optionFollowCircle);

        final WallUserFeedItem wallUserFeedItem = wallUserFeedItems.get(position);

        username.setText(wallUserFeedItem.getPostUsername());

        if(!wallUserFeedItem.getPostHashtagname().equals("null"))
        {
            hashTagName.setText(wallUserFeedItem.getPostHashtagname());
            hashTagName.setVisibility(View.VISIBLE);
        } else {
            hashTagName.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostAnonymous().equals("anonymous"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostProfileImage(),wallprofile);
            wallprofile.setVisibility(View.VISIBLE);
            wallProfileAnonymous.setVisibility(View.GONE);
        } else {
            wallprofile.setVisibility(View.GONE);
            wallProfileAnonymous.setVisibility(View.VISIBLE);
        }

        if(!wallUserFeedItem.getPostTextPost().equals("null"))
        {
            txtPost.setText(wallUserFeedItem.getPostTextPost());
            txtPost.setVisibility(View.VISIBLE);

            postOwnOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(activity, postOwnOption);
                    popupMenu.inflate(R.menu.wall_post_own_option);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.menu_item_edit:
                                    Intent i = new Intent(activity, WallnitUserEditText.class);
                                    String postNumber = wallUserFeedItem.getPostNumber();
                                    i.putExtra("edittext_number_post",postNumber);
                                    activity.startActivity(i);
                                    break;
                                case R.id.menu_item_delete:
                                    layoutWall.setVisibility(View.GONE);
                                    wallnitUserPostDelete.userPostDeleteNumber(wallUserFeedItem.getPostNumber());
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });

        } else {
            txtPost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostQuote().equals("null"))
        {
            txtQuote.setText(wallUserFeedItem.getPostQuote());
            txtQuote.setVisibility(View.VISIBLE);

        } else {
            txtQuote.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostLink().equals("null"))
        {
            txtLink.setText(Html.fromHtml("<a href=\""+wallUserFeedItem.getPostLink()+"\">" + wallUserFeedItem.getPostLink() + "</a> "));
            txtLink.setMovementMethod(LinkMovementMethod.getInstance());
            txtLinkDesc.setText(wallUserFeedItem.getPostLinkDesc());
            txtLink.setVisibility(View.VISIBLE);
            txtLinkDesc.setVisibility(View.VISIBLE);

            postOwnOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(activity, postOwnOption);
                    popupMenu.inflate(R.menu.wall_post_own_option);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.menu_item_edit:
                                    Intent i = new Intent(activity, WallnitUserEditLink.class);
                                    String postNumber = wallUserFeedItem.getPostNumber();
                                    i.putExtra("editlink_number_post",postNumber);
                                    activity.startActivity(i);
                                    break;
                                case R.id.menu_item_delete:
                                    layoutWall.setVisibility(View.GONE);
                                    wallnitUserPostDelete.userPostDeleteNumber(wallUserFeedItem.getPostNumber());
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });

        } else {
            txtLink.setVisibility(View.GONE);
            txtLinkDesc.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostBlogPostBody().equals("null"))
        {
            txtBlogTitle.setText(wallUserFeedItem.getPostBlogPostTitle());
            txtBlogBody.setText(wallUserFeedItem.getPostBlogPostBody());
            txtBlogTitle.setVisibility(View.VISIBLE);
            txtBlogBody.setVisibility(View.VISIBLE);


            postOwnOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(activity, postOwnOption);
                    popupMenu.inflate(R.menu.wall_post_own_option);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.menu_item_edit:
                                    Intent i = new Intent(activity, WallnitUserEditBlog.class);
                                    String postNumber = wallUserFeedItem.getPostNumber();
                                    i.putExtra("editblog_number_post",postNumber);
                                    activity.startActivity(i);
                                    break;
                                case R.id.menu_item_delete:
                                    layoutWall.setVisibility(View.GONE);
                                    wallnitUserPostDelete.userPostDeleteNumber(wallUserFeedItem.getPostNumber());
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });

        } else {
            txtBlogTitle.setVisibility(View.GONE);
            txtBlogBody.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostFirstImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostFirstImage(),firstImagePost);
            firstImagePost.setVisibility(View.VISIBLE);

            postOwnOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(activity, postOwnOption);
                    popupMenu.inflate(R.menu.wall_post_own_option);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.menu_item_edit:
                                    Intent i = new Intent(activity, WallnitUserEditPhotos.class);
                                    String postNumber = wallUserFeedItem.getPostNumber();
                                    i.putExtra("editphotos_number_post",postNumber);
                                    activity.startActivity(i);
                                    break;
                                case R.id.menu_item_delete:
                                    layoutWall.setVisibility(View.GONE);
                                    wallnitUserPostDelete.userPostDeleteNumber(wallUserFeedItem.getPostNumber());
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });

        } else {
            firstImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostSecondImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostSecondImage(),secondImagePost);
            secondImagePost.setVisibility(View.VISIBLE);
        } else {
            secondImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostThirdImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostThirdImage(),thirdImagePost);
            thirdImagePost.setVisibility(View.VISIBLE);
        } else {
            thirdImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostFourthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostFourthImage(),fourthImagePost);
            fourthImagePost.setVisibility(View.VISIBLE);
        } else {
            fourthImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostFifthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostFifthImage(),fifthImagePost);
            fifthImagePost.setVisibility(View.VISIBLE);
        } else {
            fifthImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostSixthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostSixthImage(),sixthImagePost);
            sixthImagePost.setVisibility(View.VISIBLE);
        } else {
            sixthImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostSeventhImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostSeventhImage(),seventhImagePost);
            seventhImagePost.setVisibility(View.VISIBLE);
        } else {
            seventhImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostEighthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostEighthImage(),eighthImagePost);
            eighthImagePost.setVisibility(View.VISIBLE);
        } else {
            eighthImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostNinthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostNinthImage(),ninthImagePost);
            ninthImagePost.setVisibility(View.VISIBLE);
        } else {
            ninthImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostTenthImageNull().equals("null"))
        {
            PicassoClient.downloadImgae(activity,wallUserFeedItem.getPostTenthImage(),tenthImagePost);
            tenthImagePost.setVisibility(View.VISIBLE);
        } else {
            tenthImagePost.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostImageCaption().equals("null"))
        {
            imageCaption.setText(wallUserFeedItem.getPostImageCaption());
            imageCaption.setVisibility(View.VISIBLE);

        } else {
            imageCaption.setVisibility(View.GONE);
        }

        if(!wallUserFeedItem.getPostYouLikeOrNot().equals("not like"))
        {
            likePost.setVisibility(View.VISIBLE);
            unlikePost.setVisibility(View.GONE);
        } else {
            unlikePost.setVisibility(View.VISIBLE);
            likePost.setVisibility(View.GONE);
        }

        totalNumLike.setText(wallUserFeedItem.getPostTotalNumLike());

        if(wallUserFeedItem.getPostOwnFollowCircle().equals("own"))
        {

            wallprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                    String userid = wallUserFeedItem.getPostUserId();
                    i.putExtra("otheruser_profile_userid",userid);
                    activity.startActivity(i);
                }
            });

            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                    String userid = wallUserFeedItem.getPostUserId();
                    i.putExtra("otheruser_profile_userid",userid);
                    activity.startActivity(i);
                }
            });

        }
        if(wallUserFeedItem.getPostOwnFollowCircle().equals("follow"))
        {
            wallprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!wallUserFeedItem.getPostAnonymous().equals("anonymous"))
                    {
                        Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                        String followUserId = wallUserFeedItem.getPostUserId();
                        i.putExtra("otheruser_profile_userid",followUserId);
                        activity.startActivity(i);
                    }
                }
            });

            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!wallUserFeedItem.getPostAnonymous().equals("anonymous"))
                    {
                        Intent i = new Intent(activity, WallnitOtherUserProfile.class);
                        String followUserId = wallUserFeedItem.getPostUserId();
                        i.putExtra("otheruser_profile_userid",followUserId);
                        activity.startActivity(i);
                    }
                }
            });
        }

        if(wallUserFeedItem.getPostOwnFollowCircle().equals("own"))
        {
            postOwnOption.setVisibility(View.VISIBLE);
            postFollowCircleOption.setVisibility(View.GONE);
        }
        if(wallUserFeedItem.getPostOwnFollowCircle().equals("follow"))
        {
            postOwnOption.setVisibility(View.GONE);
            postFollowCircleOption.setVisibility(View.VISIBLE);
        }
        if(wallUserFeedItem.getPostOwnFollowCircle().equals("circle"))
        {
            postOwnOption.setVisibility(View.GONE);
            postFollowCircleOption.setVisibility(View.VISIBLE);
        }

        postFollowCircleOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(activity, postFollowCircleOption);
                popupMenu.inflate(R.menu.wall_post_follow_circle_option);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_item_report:
                                Intent getintentPostNumber = new Intent(activity, WallnitUserPostReport.class);
                                String postNumber = wallUserFeedItem.getPostNumber();
                                getintentPostNumber.putExtra("repost_number_post",postNumber);
                                activity.startActivity(getintentPostNumber);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });


        likePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likePost.setVisibility(View.GONE);
                unlikePost.setVisibility(View.VISIBLE);

                Integer total_lk = Integer.parseInt(totalNumLike.getText().toString());
                Integer value_get_totallike = likePostSub(total_lk);
                totalNumLike.setText(Integer.toString(value_get_totallike));

                wallnitUserAllPostUnlike.userPostUnlike(wallUserFeedItem.getPostNumber(),wallUserFeedItem.getPostOwnUserId());
            }
        });


        unlikePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likePost.setVisibility(View.VISIBLE);
                unlikePost.setVisibility(View.GONE);

                Integer total_lk = Integer.parseInt(totalNumLike.getText().toString());
                Integer value_get_totallike = likePostAdd(total_lk);
                totalNumLike.setText(Integer.toString(value_get_totallike));

                wallnitUserAllPostLike.userPostLike(wallUserFeedItem.getPostNumber(),wallUserFeedItem.getPostOwnUserId());
            }
        });




        if(wallUserFeedItem.getPostOwnFollowCircle().equals("circle"))
        {
            wallprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent getintentCircleNumber = new Intent(activity, WallnitCircleOtherProfile.class);
                    String circleid = wallUserFeedItem.getPostUserId();
                    getintentCircleNumber.putExtra("othercircle_profile_userid",circleid);
                    activity.startActivity(getintentCircleNumber);
                }
            });

            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent getintentCircleNumber = new Intent(activity, WallnitCircleOtherProfile.class);
                    String circleid = wallUserFeedItem.getPostUserId();
                    getintentCircleNumber.putExtra("othercircle_profile_userid",circleid);
                    activity.startActivity(getintentCircleNumber);
                }
            });

        }


        layoutTotalNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getintentPostNumber = new Intent(activity, WallnitUserPostLikeList.class);
                String postNumber = wallUserFeedItem.getPostNumber();
                getintentPostNumber.putExtra("likelist_number_post",postNumber);
                activity.startActivity(getintentPostNumber);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        return convertView;

    }

    private void refresh(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(activity,Wall.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                activity.finish();
                swipeRefreshLayout.setRefreshing(false);
            }
        },3000);
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
