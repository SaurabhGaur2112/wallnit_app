package com.wallnit.wallnitlogin.WallnitUserLikeNotificationGet;

/**
 * Created by Saurabh Gaur on 1/19/2017.
 */
public class UserLikeNotificationFeedItem {

    private String username,dateTime,profileImg,totalNumLike,numLike;

    public UserLikeNotificationFeedItem(){

    }

    public UserLikeNotificationFeedItem(String username,String dateTime,String profileImg,String totalNumLike,String numLike){
        super();
        this.username = username;
        this.dateTime = dateTime;
        this.profileImg = profileImg;
        this.totalNumLike = totalNumLike;
        this.numLike = numLike;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getTotalNumLike() {
        return totalNumLike;
    }

    public void setTotalNumLike(String totalNumLike) {
        this.totalNumLike = totalNumLike;
    }

    public String getNumLike() {
        return numLike;
    }

    public void setNumLike(String numLike) {
        this.numLike = numLike;
    }
}
