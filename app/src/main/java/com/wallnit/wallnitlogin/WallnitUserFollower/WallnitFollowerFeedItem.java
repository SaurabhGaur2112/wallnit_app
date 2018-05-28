package com.wallnit.wallnitlogin.WallnitUserFollower;

/**
 * Created by Saurabh Gaur on 1/16/2017.
 */
public class WallnitFollowerFeedItem {

    private String follower_profile;
    private String follower_username;
    private String follower_tagname;
    private String follower_userid;

    public WallnitFollowerFeedItem(){

    }

    public WallnitFollowerFeedItem(String follower_profile, String follower_username, String follower_tagname, String follower_userid){
        super();
        this.follower_profile = follower_profile;
        this.follower_username = follower_username;
        this.follower_tagname = follower_tagname;
        this.follower_userid = follower_userid;
    }

    public String getFollower_profile() {
        return follower_profile;
    }

    public void setFollower_profile(String follower_profile) {
        this.follower_profile = follower_profile;
    }

    public String getFollower_username() {
        return follower_username;
    }

    public void setFollower_username(String follower_username) {
        this.follower_username = follower_username;
    }

    public String getFollower_tagname() {
        return follower_tagname;
    }

    public void setFollower_tagname(String follower_tagname) {
        this.follower_tagname = follower_tagname;
    }

    public String getFollower_userid() {
        return follower_userid;
    }

    public void setFollower_userid(String follower_userid) {
        this.follower_userid = follower_userid;
    }
}
