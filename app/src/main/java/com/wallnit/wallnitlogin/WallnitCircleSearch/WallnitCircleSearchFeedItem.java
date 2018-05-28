package com.wallnit.wallnitlogin.WallnitCircleSearch;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitCircleSearchFeedItem {

    private String circleSearchCirclename,circleSearchCircleId,circleSearchProfile;

    public WallnitCircleSearchFeedItem(){

    }

    public WallnitCircleSearchFeedItem(String circleSearchCirclename,String circleSearchCircleId,String circleSearchProfile){
        super();
        this.circleSearchCirclename = circleSearchCirclename;
        this.circleSearchCircleId = circleSearchCircleId;
        this.circleSearchProfile = circleSearchProfile;
    }

    public String getCircleSearchCirclename() {
        return circleSearchCirclename;
    }

    public void setCircleSearchCirclename(String circleSearchCirclename) {
        this.circleSearchCirclename = circleSearchCirclename;
    }

    public String getCircleSearchCircleId() {
        return circleSearchCircleId;
    }

    public void setCircleSearchCircleId(String circleSearchCircleId) {
        this.circleSearchCircleId = circleSearchCircleId;
    }

    public String getCircleSearchProfile() {
        return circleSearchProfile;
    }

    public void setCircleSearchProfile(String circleSearchProfile) {
        this.circleSearchProfile = circleSearchProfile;
    }
}
