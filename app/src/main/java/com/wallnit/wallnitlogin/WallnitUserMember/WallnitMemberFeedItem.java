package com.wallnit.wallnitlogin.WallnitUserMember;

/**
 * Created by Saurabh Gaur on 1/17/2017.
 */
public class WallnitMemberFeedItem {

    private String member_userid,member_username,member_tagname,member_profile;

    public WallnitMemberFeedItem(){

    }

    public WallnitMemberFeedItem(String member_userid,String member_username,String member_tagname,String member_profile){
        super();
        this.member_userid = member_userid;
        this.member_username = member_username;
        this.member_tagname = member_tagname;
        this.member_profile = member_profile;
    }

    public String getMember_userid() {
        return member_userid;
    }

    public void setMember_userid(String member_userid) {
        this.member_userid = member_userid;
    }

    public String getMember_username() {
        return member_username;
    }

    public void setMember_username(String member_username) {
        this.member_username = member_username;
    }

    public String getMember_tagname() {
        return member_tagname;
    }

    public void setMember_tagname(String member_tagname) {
        this.member_tagname = member_tagname;
    }

    public String getMember_profile() {
        return member_profile;
    }

    public void setMember_profile(String member_profile) {
        this.member_profile = member_profile;
    }
}
