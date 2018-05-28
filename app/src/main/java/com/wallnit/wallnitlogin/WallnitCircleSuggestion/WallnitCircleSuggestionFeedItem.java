package com.wallnit.wallnitlogin.WallnitCircleSuggestion;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitCircleSuggestionFeedItem {

    private String circleSuggestionCircleId,circleSuggestionCirclename,circleSuggestionProfile;

    public WallnitCircleSuggestionFeedItem(){

    }

    public WallnitCircleSuggestionFeedItem(String circleSuggestionCircleId,String circleSuggestionCirclename,String circleSuggestionProfile){
        super();
        this.circleSuggestionCircleId = circleSuggestionCircleId;
        this.circleSuggestionCirclename = circleSuggestionCirclename;
        this.circleSuggestionProfile = circleSuggestionProfile;
    }

    public String getCircleSuggestionCircleId() {
        return circleSuggestionCircleId;
    }

    public void setCircleSuggestionCircleId(String circleSuggestionCircleId) {
        this.circleSuggestionCircleId = circleSuggestionCircleId;
    }

    public String getCircleSuggestionCirclename() {
        return circleSuggestionCirclename;
    }

    public void setCircleSuggestionCirclename(String circleSuggestionCirclename) {
        this.circleSuggestionCirclename = circleSuggestionCirclename;
    }

    public String getCircleSuggestionProfile() {
        return circleSuggestionProfile;
    }

    public void setCircleSuggestionProfile(String circleSuggestionProfile) {
        this.circleSuggestionProfile = circleSuggestionProfile;
    }
}
