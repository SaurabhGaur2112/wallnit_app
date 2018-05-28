package com.wallnit.wallnitlogin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Saurabh Gaur on 11/20/2016.
 */
public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    String userid,circleid,forget_userid,forget_circleid,user_conf,user_interest,circle_conf,circle_create;

    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("wallnitapplication", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean loggedin){
        editor.putBoolean("wallnitloggedIn",loggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("wallnitloggedIn", false);
    }

    public void setCircleLoggedin(boolean circleLoggedin){
        editor.putBoolean("wallnitCircleloggedIn",circleLoggedin);
        editor.commit();
    }

    public boolean circleloggedin(){
        return prefs.getBoolean("wallnitCircleloggedIn",false);
    }

    void setUserSession(String usersession)
    {
        userid=usersession;
        editor.putString("usersessionLoggedIn",userid);
        editor.commit();
    }

    String getUserSession()
    {
        return prefs.getString("usersessionLoggedIn",userid);
    }

    void setUserCheckConfirmation(String userconfirmation)
    {
        user_conf=userconfirmation;
        editor.putString("usercheckconfirmation",user_conf);
        editor.commit();
    }

    String getUserCheckConfirmation()
    {
        return prefs.getString("usercheckconfirmation",user_conf);
    }

    void setCircleCheckConfirmation(String circleconfirmation)
    {
        circle_conf=circleconfirmation;
        editor.putString("circlecheckconfirmation",circle_conf);
        editor.commit();
    }

    String getCircleCheckConfirmation()
    {
        return prefs.getString("circlecheckconfirmation",circle_conf);
    }

    void setUserInterestChoose(String userInterestChoose)
    {
        user_interest=userInterestChoose;
        editor.putString("usercheckinterestchoose",user_interest);
        editor.commit();
    }

    String getUserInterestChoose()
    {
        return prefs.getString("usercheckinterestchoose",user_interest);
    }

    void setCircleCreateCircle(String circleCreateCircle)
    {
        circle_create=circleCreateCircle;
        editor.putString("circlecheckcreatecircle",circle_create);
        editor.commit();
    }

    String getCircleCreateCircle()
    {
        return prefs.getString("circlecheckcreatecircle",circle_create);
    }

    void setCircleSession(String circlesession)
    {
        circleid=circlesession;
        editor.putString("circlesessionLoggedIn",circleid);
        editor.commit();
    }

    String getCircleSession()
    {
        return prefs.getString("circlesessionLoggedIn",circleid);
    }

    void setForget_UserSession(String forget_usersession)
    {
        forget_userid=forget_usersession;
        editor.putString("usersessionForget",forget_userid);
        editor.commit();
    }

    String getForget_UserSession()
    {
        return prefs.getString("usersessionForget",forget_userid);
    }

    void setForget_CircleSession(String forget_circlesession)
    {
        forget_circleid=forget_circlesession;
        editor.putString("circlesessionForget",forget_circleid);
        editor.commit();
    }

    String getForget_CircleSession()
    {
        return prefs.getString("circlesessionForget",forget_circleid);
    }

}
