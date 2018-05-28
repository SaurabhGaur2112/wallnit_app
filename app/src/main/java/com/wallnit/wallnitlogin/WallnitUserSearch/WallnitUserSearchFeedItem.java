package com.wallnit.wallnitlogin.WallnitUserSearch;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitUserSearchFeedItem {

    private String searchUserUsername,searchUserUserId,searchUserTagname,searchUserProfile;

    public WallnitUserSearchFeedItem(){

    }

    public WallnitUserSearchFeedItem(String searchUserUsername,String searchUserUserId, String searchUserTagname,String searchUserProfile){
        super();
        this.searchUserUsername = searchUserUsername;
        this.searchUserUserId = searchUserUserId;
        this.searchUserTagname = searchUserTagname;
        this.searchUserProfile = searchUserProfile;
    }

    public String getSearchUserUsername() {
        return searchUserUsername;
    }

    public void setSearchUserUsername(String searchUserUsername) {
        this.searchUserUsername = searchUserUsername;
    }

    public String getSearchUserUserId() {
        return searchUserUserId;
    }

    public void setSearchUserUserId(String searchUserUserId) {
        this.searchUserUserId = searchUserUserId;
    }

    public String getSearchUserTagname() {
        return searchUserTagname;
    }

    public void setSearchUserTagname(String searchUserTagname) {
        this.searchUserTagname = searchUserTagname;
    }

    public String getSearchUserProfile() {
        return searchUserProfile;
    }

    public void setSearchUserProfile(String searchUserProfile) {
        this.searchUserProfile = searchUserProfile;
    }
}
