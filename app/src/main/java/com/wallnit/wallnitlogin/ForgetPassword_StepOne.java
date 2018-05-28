package com.wallnit.wallnitlogin;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitUserForgetPassword.WallnitCircleForgetGetId;
import com.wallnit.wallnitlogin.WallnitUserForgetPassword.WallnitCircleForgetSendUseridEmailid;
import com.wallnit.wallnitlogin.WallnitUserForgetPassword.WallnitUserForgetFetchVerifiCode;
import com.wallnit.wallnitlogin.WallnitUserForgetPassword.WallnitUserForgetGetId;
import com.wallnit.wallnitlogin.WallnitUserForgetPassword.WallnitUserForgetSendUseridEmailid;

public class ForgetPassword_StepOne extends AppCompatActivity {

    private TextView title_forget,desc_forget,forgetStepOneContinue,login,termsOfService;
    private EditText enter_emailtxt;
    private ProgressBar continue_progress;
    WallnitLoginCheckUserOrCircle wallnitLoginCheckUserOrCircle;
    WallnitUserForgetGetId wallnitUserForgetGetId;
    WallnitCircleForgetGetId wallnitCircleForgetGetId;
    WallnitUserForgetSendUseridEmailid wallnitUserForgetSendUseridEmailid;
    WallnitUserForgetFetchVerifiCode wallnitUserForgetFetchVerifiCode;
    WallnitCircleForgetSendUseridEmailid wallnitCircleForgetSendUseridEmailid;
    Session session;
    ConnectionDetector connectionDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forget_password__step_one);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        wallnitLoginCheckUserOrCircle = new WallnitLoginCheckUserOrCircle();
        wallnitUserForgetGetId = new WallnitUserForgetGetId();
        wallnitCircleForgetGetId = new WallnitCircleForgetGetId();
        wallnitUserForgetSendUseridEmailid = new WallnitUserForgetSendUseridEmailid();
        wallnitUserForgetFetchVerifiCode = new WallnitUserForgetFetchVerifiCode();
        wallnitCircleForgetSendUseridEmailid = new WallnitCircleForgetSendUseridEmailid();
        connectionDetector = new ConnectionDetector(this);
        session = new Session(this);
        title_forget=(TextView) findViewById(R.id.forget_passwordfirst_title);
        desc_forget=(TextView) findViewById(R.id.forget_password_first_desc);
        forgetStepOneContinue = (TextView) findViewById(R.id.forget_password_stepone);
        login = (TextView) findViewById(R.id.forget_login_first);
        termsOfService = (TextView) findViewById(R.id.forget_terms_ofservice_first);
        enter_emailtxt = (EditText) findViewById(R.id.forget_password_enteremail);
        continue_progress = (ProgressBar) findViewById(R.id.progressBar_firststepforget);

        title_forget.setText("Why do I need to enter?");
        desc_forget.setText("So that we can check that your account is not subject to any misuse.");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword_StepOne.this,WallnitLogin.class));
                finish();
            }
        });

        termsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword_StepOne.this,WallnitTerms_of_Service.class));
                finish();
            }
        });

        forgetStepOneContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManagerlogin = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManagerlogin.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (connectionDetector.isConnected()) {

                String logintxt;
                continue_progress.setVisibility(View.VISIBLE);
                logintxt = enter_emailtxt.getText().toString();

                if (logintxt.trim().length() == 0) {
                    enter_emailtxt.requestFocus();
                } else {
                    String getValueUserOrCircle = wallnitLoginCheckUserOrCircle.checkLoginUserOrCircle(logintxt);

                    if (getValueUserOrCircle.equals("signupas_user")) {
                        String useridget = wallnitUserForgetGetId.forgetUserId(logintxt);
                        session.setForget_UserSession(useridget);
                        wallnitUserForgetSendUseridEmailid.sendUserIdEmailid(useridget, logintxt);
                        String verficodeget = wallnitUserForgetFetchVerifiCode.fetchverificode(logintxt);

                        Intent i = new Intent(getApplicationContext(), ForgetPassword_StepTwo.class);
                        Bundle extras = new Bundle();
                        extras.putString("emailid",logintxt);
                        extras.putString("forget_verifi_code",verficodeget);
                        extras.putString("email_circle_user","signupas_user");
                        i.putExtras(extras);
                        startActivity(i);
                        finish();
                    }
                    if (getValueUserOrCircle.equals("signupas_page")) {
                        String circleidget = wallnitCircleForgetGetId.forgetCircleId(logintxt);
                        session.setForget_CircleSession(circleidget);
                        wallnitCircleForgetSendUseridEmailid.sendCircleIdEmailid(circleidget,logintxt);

                        String verifiCodeGet = wallnitUserForgetFetchVerifiCode.fetchverificode(logintxt);

                        Intent i = new Intent(getApplicationContext(), ForgetPasswordCircle_StepTwo.class);
                        Bundle extras = new Bundle();
                        extras.putString("emailid",logintxt);
                        extras.putString("forget_verifi_code",verifiCodeGet);
                        extras.putString("email_circle_user","signupas_page");
                        i.putExtras(extras);
                        startActivity(i);
                        finish();

                    }
                    if(getValueUserOrCircle.equals("wrong email"))
                    {
                        startActivity(new Intent(ForgetPassword_StepOne.this,ForgetPassword_StepOne.class));
                        finish();
                        Toast.makeText(ForgetPassword_StepOne.this, "Enter correct email", Toast.LENGTH_SHORT).show();
                    }
                }
            }
                else{
                    Toast.makeText(ForgetPassword_StepOne.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
