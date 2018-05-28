package com.wallnit.wallnitlogin.WallnitUserFollowing;

/**
 * Created by Saurabh Gaur on 1/16/2017.
 */
public class WallnitFollowingFeedItem {

    private String following_userid,following_username,following_tagname,following_profile;

    public WallnitFollowingFeedItem(){

    }

    public WallnitFollowingFeedItem(String following_userid, String following_username, String following_tagname, String following_profile){
        super();
        this.following_userid = following_userid;
        this.following_username = following_username;
        this.following_tagname = following_tagname;
        this.following_profile = following_profile;
    }

    public String getFollowing_userid() {
        return following_userid;
    }

    public void setFollowing_userid(String following_userid) {
        this.following_userid = following_userid;
    }

    public String getFollowing_username() {
        return following_username;
    }

    public void setFollowing_username(String following_username) {
        this.following_username = following_username;
    }

    public String getFollowing_tagname() {
        return following_tagname;
    }

    public void setFollowing_tagname(String following_tagname) {
        this.following_tagname = following_tagname;
    }

    public String getFollowing_profile() {
        return following_profile;
    }

    public void setFollowing_profile(String following_profile) {
        this.following_profile = following_profile;
    }
}
