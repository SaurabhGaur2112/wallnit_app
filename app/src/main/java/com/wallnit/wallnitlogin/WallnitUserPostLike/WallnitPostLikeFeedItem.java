package com.wallnit.wallnitlogin.WallnitUserPostLike;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitPostLikeFeedItem {

    private String postlike_userid,postlike_username,postlike_tagname,postlike_profile;

    public WallnitPostLikeFeedItem(){

    }

    public WallnitPostLikeFeedItem(String postlike_userid,String postlike_username,String postlike_tagname,String postlike_profile){
        super();
        this.postlike_userid = postlike_userid;
        this.postlike_username = postlike_username;
        this.postlike_tagname = postlike_tagname;
        this.postlike_profile = postlike_profile;
    }

    public String getPostlike_userid() {
        return postlike_userid;
    }

    public void setPostlike_userid(String postlike_userid) {
        this.postlike_userid = postlike_userid;
    }

    public String getPostlike_username() {
        return postlike_username;
    }

    public void setPostlike_username(String postlike_username) {
        this.postlike_username = postlike_username;
    }

    public String getPostlike_tagname() {
        return postlike_tagname;
    }

    public void setPostlike_tagname(String postlike_tagname) {
        this.postlike_tagname = postlike_tagname;
    }

    public String getPostlike_profile() {
        return postlike_profile;
    }

    public void setPostlike_profile(String postlike_profile) {
        this.postlike_profile = postlike_profile;
    }
}
