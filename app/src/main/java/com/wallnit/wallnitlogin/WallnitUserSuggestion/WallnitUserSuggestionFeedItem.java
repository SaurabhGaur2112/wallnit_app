package com.wallnit.wallnitlogin.WallnitUserSuggestion;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitUserSuggestionFeedItem {

    private String userSuggestionUsername,userSuggestionUserid,userSuggestionTagname,userSuggestionProfile;

    public WallnitUserSuggestionFeedItem(){

    }

    public WallnitUserSuggestionFeedItem(String userSuggestionUsername,String userSuggestionUserid,String userSuggestionTagname,String userSuggestionProfile){
        super();
        this.userSuggestionUsername = userSuggestionUsername;
        this.userSuggestionUserid = userSuggestionUserid;
        this.userSuggestionTagname = userSuggestionTagname;
        this.userSuggestionProfile = userSuggestionProfile;
    }

    public String getUserSuggestionUsername() {
        return userSuggestionUsername;
    }

    public void setUserSuggestionUsername(String userSuggestionUsername) {
        this.userSuggestionUsername = userSuggestionUsername;
    }

    public String getUserSuggestionUserid() {
        return userSuggestionUserid;
    }

    public void setUserSuggestionUserid(String userSuggestionUserid) {
        this.userSuggestionUserid = userSuggestionUserid;
    }

    public String getUserSuggestionTagname() {
        return userSuggestionTagname;
    }

    public void setUserSuggestionTagname(String userSuggestionTagname) {
        this.userSuggestionTagname = userSuggestionTagname;
    }

    public String getUserSuggestionProfile() {
        return userSuggestionProfile;
    }

    public void setUserSuggestionProfile(String userSuggestionProfile) {
        this.userSuggestionProfile = userSuggestionProfile;
    }
}
