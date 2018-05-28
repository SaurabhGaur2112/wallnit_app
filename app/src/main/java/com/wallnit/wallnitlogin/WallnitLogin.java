package com.wallnit.wallnitlogin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WallnitLogin extends AppCompatActivity {
    private Button loginButton;
    private TextView forgetPasswordLogin,signupuserLogin,signupcircleLogin;
    private EditText logintxt,passwordtxt;
    WallnitLoginCheckUserOrCircle wallnitLoginCheckUserOrCircle;
    WallnitLoginUserGetId wallnitLoginUserGetId;
    WallnitLoginUserCheckConfirmation wallnitLoginUserCheckConfirmation;
    WallnitLoginUserConnectCircle wallnitLoginUserConnectCircle;
    WallnitLoginCircleGetId wallnitLoginCircleGetId;
    WallnitLoginCircleCheckConfirmation wallnitLoginCircleCheckConfirmation;
    WallnitLoginCheckCircleCategory wallnitLoginCheckCircleCategory;
    Session session;
    InputStream is = null;
    String line = null;
    String result = null;
    String temp1 = "",temp2 = "",temp3 = "",temp4 = "",temp5 = "",temp6 = "",temp7 = "",temp8 = "",
            temp9 = "",temp10 = "",temp11 = "",temp12 = "",temp13 = "",temp14 = "",temp15 = "",temp16 = "",temp17 = "",temp18 = "",temp19 = "",
            temp20 = "",temp21 = "",temp22 = "",temp23 = "",temp24 = "",temp25 = "";
    String[] arr1,arr2,arr3,arr4,arr5,arr6,arr7,arr8,arr9,arr10,arr11,arr12,arr13,arr14,arr15,arr16,arr17,arr18,arr19,arr20,arr21,arr22,arr23,arr24,arr25;
    String interest1,interest2,interest3,interest4,interest5,interest6,interest7,interest8,interest9,interest10,interest11,interest12,interest13
            ,interest14,interest15,interest16,interest17,interest18,interest19,interest20,interest21,interest22,interest23,interest24,interest25;

    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallnit_login);
        loginButton = (Button) findViewById(R.id.login_user_button);
        forgetPasswordLogin = (TextView) findViewById(R.id.forget_password_loginActivity);
        signupuserLogin = (TextView) findViewById(R.id.login_gothis_txt_user);
        signupcircleLogin = (TextView) findViewById(R.id.login_gothis_txt_circle);
        logintxt = (EditText) findViewById(R.id.login_enter_email);
        passwordtxt = (EditText) findViewById(R.id.login_enter_pass);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        wallnitLoginCheckUserOrCircle = new WallnitLoginCheckUserOrCircle();
        wallnitLoginUserGetId = new WallnitLoginUserGetId();
        wallnitLoginUserCheckConfirmation = new WallnitLoginUserCheckConfirmation();
        wallnitLoginUserConnectCircle = new WallnitLoginUserConnectCircle();
        session = new Session(this);
        wallnitLoginCircleGetId = new WallnitLoginCircleGetId();
        wallnitLoginCircleCheckConfirmation = new WallnitLoginCircleCheckConfirmation();
        wallnitLoginCheckCircleCategory = new WallnitLoginCheckCircleCategory();
        connectionDetector = new ConnectionDetector(this);



        forgetPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitLogin.this,ForgetPassword_StepOne.class));
                finish();
            }
        });

        signupuserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitLogin.this,WallnitSignupUser.class));
                finish();
            }
        });

        signupcircleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WallnitLogin.this,WallnitSignupCircle.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManagerlogin = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManagerlogin.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                if (connectionDetector.isConnected()) {
                String loginemailid, loginpassword;
                loginemailid = logintxt.getText().toString();
                loginpassword = passwordtxt.getText().toString();

                    if(loginemailid.trim().length()==0)
                    {
                        logintxt.requestFocus();
                    } else if(loginpassword.trim().length()==0)
                    {
                        passwordtxt.requestFocus();
                    } else {
                        new LoginUserEnter(loginemailid,loginpassword).execute();
                    }



            }
                else{
                    Toast.makeText(WallnitLogin.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public class LoginUserEnter extends AsyncTask<Void,Void,Void>{

        String emailidLogin,passwordLogin;
        String getValueUserOrCircle;
        String userIdGetSessions = "";
        String circleIdGetSessions = "";

        public LoginUserEnter(String emailidLogin,String passwordLogin){
            this.emailidLogin = emailidLogin;
            this.passwordLogin = passwordLogin;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(WallnitLogin.this,"","Please wait...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            if(getValueUserOrCircle.equals("wrong email"))
            {
                Toast.makeText(WallnitLogin.this, "Enter correct email", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WallnitLogin.this,WallnitLogin.class));
                finish();
            }
            if (userIdGetSessions.equals("wrong")) {
                Toast.makeText(WallnitLogin.this, "Enter correct email and Password", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WallnitLogin.this, WallnitLogin.class));
                finish();
            }
            if (circleIdGetSessions.equals("wrong")) {
                Toast.makeText(WallnitLogin.this, "Enter correct email and Password", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WallnitLogin.this,WallnitLogin.class));
                finish();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            getValueUserOrCircle = wallnitLoginCheckUserOrCircle.checkLoginUserOrCircle(emailidLogin);


                if (getValueUserOrCircle.equals("signupas_user")) {
                    userIdGetSessions = wallnitLoginUserGetId.loginUserGetId(emailidLogin, passwordLogin);


                     if (!userIdGetSessions.equals("wrong")) {

                        session.setLoggedin(true);
                        session.setCircleLoggedin(false);
                        session.setUserSession(userIdGetSessions);
                        session.setCircleSession("circle_not_login");

                        // user check login start
                        String checkUserLoginConfirmation = wallnitLoginUserCheckConfirmation.wallnitCheckConfirmation(userIdGetSessions);

                        if (checkUserLoginConfirmation.equals("null")) {
                            session.setUserCheckConfirmation("null");
                            startActivity(new Intent(WallnitLogin.this, WallnitUserAccountConfirmation.class));
                            finish();
                        }
                        if (checkUserLoginConfirmation.equals("0")) {
                            session.setUserCheckConfirmation("0");
                            startActivity(new Intent(WallnitLogin.this, WallnitUserAccountConfirmation.class));
                            finish();
                        }
                        if (checkUserLoginConfirmation.equals("1")) {
                            session.setUserCheckConfirmation("1");

                            List<NameValuePair> confirmCheck = new ArrayList<NameValuePair>(1);
                            confirmCheck.add(new BasicNameValuePair("webusersession", userIdGetSessions));
                            try {
                                HttpClient httpClient = new DefaultHttpClient();
                                HttpPost httpPost = new HttpPost("http://www.wallnit.com/wallnit_android/wallnit_android_login_user_check_circlejoin.php");
                                httpPost.setEntity(new UrlEncodedFormEntity(confirmCheck));
                                HttpResponse response = httpClient.execute(httpPost);
                                HttpEntity entity = response.getEntity();
                                is = entity.getContent();
                            } catch (Exception e) {

                            }
                            try {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                                StringBuffer sb = new StringBuffer();
                                while ((line = reader.readLine()) != null)
                                    sb.append(line + "\n");
                                result = sb.toString();
                                is.close();
                            } catch (Exception e) {

                            }
                            try {
                                JSONArray jArray = new JSONArray(result);
                                int count = jArray.length();

                                for (int i = 0; i < count; i++) {

                                    JSONObject json_data = jArray.getJSONObject(i);

                                    temp1 += json_data.getString("movies") + ":";
                                    temp2 += json_data.getString("actors") + ":";
                                    temp3 += json_data.getString("artists") + ":";
                                    temp4 += json_data.getString("music") + ":";
                                    temp5 += json_data.getString("literature") + ":";
                                    temp6 += json_data.getString("dance") + ":";
                                    temp7 += json_data.getString("tv_episodes") + ":";
                                    temp8 += json_data.getString("humor") + ":";
                                    temp9 += json_data.getString("art") + ":";
                                    temp10 += json_data.getString("comics") + ":";
                                    temp11 += json_data.getString("yoga") + ":";
                                    temp12 += json_data.getString("exercise") + ":";
                                    temp13 += json_data.getString("sports") + ":";
                                    temp14 += json_data.getString("history") + ":";
                                    temp15 += json_data.getString("magazines") + ":";
                                    temp16 += json_data.getString("news") + ":";
                                    temp17 += json_data.getString("education") + ":";
                                    temp18 += json_data.getString("science") + ":";
                                    temp19 += json_data.getString("technology") + ":";
                                    temp20 += json_data.getString("startup") + ":";
                                    temp21 += json_data.getString("entrepreneur") + ":";
                                    temp22 += json_data.getString("fashion") + ":";
                                    temp23 += json_data.getString("travel") + ":";
                                    temp24 += json_data.getString("culture") + ":";
                                    temp25 += json_data.getString("photography") + ":";
                                }

                                arr1 = temp1.split(":");
                                arr2 = temp2.split(":");
                                arr3 = temp3.split(":");
                                arr4 = temp4.split(":");
                                arr5 = temp5.split(":");
                                arr6 = temp6.split(":");
                                arr7 = temp7.split(":");
                                arr8 = temp8.split(":");
                                arr9 = temp9.split(":");
                                arr10 = temp10.split(":");
                                arr11 = temp11.split(":");
                                arr12 = temp12.split(":");
                                arr13 = temp13.split(":");
                                arr14 = temp14.split(":");
                                arr15 = temp15.split(":");
                                arr16 = temp16.split(":");
                                arr17 = temp17.split(":");
                                arr18 = temp18.split(":");
                                arr19 = temp19.split(":");
                                arr20 = temp20.split(":");
                                arr21 = temp21.split(":");
                                arr22 = temp22.split(":");
                                arr23 = temp23.split(":");
                                arr24 = temp24.split(":");
                                arr25 = temp25.split(":");

                                interest1 = Arrays.toString(arr1).replace("[", "").replace("]", "");
                                interest2 = Arrays.toString(arr2).replace("[", "").replace("]", "");
                                interest3 = Arrays.toString(arr3).replace("[", "").replace("]", "");
                                interest4 = Arrays.toString(arr4).replace("[", "").replace("]", "");
                                interest5 = Arrays.toString(arr5).replace("[", "").replace("]", "");
                                interest6 = Arrays.toString(arr6).replace("[", "").replace("]", "");
                                interest7 = Arrays.toString(arr7).replace("[", "").replace("]", "");
                                interest8 = Arrays.toString(arr8).replace("[", "").replace("]", "");
                                interest9 = Arrays.toString(arr9).replace("[", "").replace("]", "");
                                interest10 = Arrays.toString(arr10).replace("[", "").replace("]", "");
                                interest11 = Arrays.toString(arr11).replace("[", "").replace("]", "");
                                interest12 = Arrays.toString(arr12).replace("[", "").replace("]", "");
                                interest13 = Arrays.toString(arr13).replace("[", "").replace("]", "");
                                interest14 = Arrays.toString(arr14).replace("[", "").replace("]", "");
                                interest15 = Arrays.toString(arr15).replace("[", "").replace("]", "");
                                interest16 = Arrays.toString(arr16).replace("[", "").replace("]", "");
                                interest17 = Arrays.toString(arr17).replace("[", "").replace("]", "");
                                interest18 = Arrays.toString(arr18).replace("[", "").replace("]", "");
                                interest19 = Arrays.toString(arr19).replace("[", "").replace("]", "");
                                interest20 = Arrays.toString(arr20).replace("[", "").replace("]", "");
                                interest21 = Arrays.toString(arr21).replace("[", "").replace("]", "");
                                interest22 = Arrays.toString(arr22).replace("[", "").replace("]", "");
                                interest23 = Arrays.toString(arr23).replace("[", "").replace("]", "");
                                interest24 = Arrays.toString(arr24).replace("[", "").replace("]", "");
                                interest25 = Arrays.toString(arr25).replace("[", "").replace("]", "");


                            } catch (Exception e) {

                            }

                            if (interest1.equals("0") && interest2.equals("0") && interest3.equals("0") && interest4.equals("0") && interest5.equals("0") && interest6.equals("0") &&
                                    interest7.equals("0") && interest8.equals("0") && interest9.equals("0") && interest10.equals("0") && interest11.equals("0") && interest12.equals("0") &&
                                    interest13.equals("0") && interest14.equals("0") && interest15.equals("0") && interest16.equals("0") && interest17.equals("0") && interest18.equals("0") &&
                                    interest19.equals("0") && interest20.equals("0") && interest21.equals("0") && interest22.equals("0") && interest23.equals("0") && interest24.equals("0") &&
                                    interest25.equals("0")) {
                                session.setUserInterestChoose("0");
                                startActivity(new Intent(WallnitLogin.this, WallnitUserChooseInterest.class));
                                finish();
                            } else {
                                session.setUserInterestChoose("1");
                                Intent intent = new Intent(getApplicationContext(), Wall.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }

                        // user check login end
                    }

                }
                if (getValueUserOrCircle.equals("signupas_page")) {
                    circleIdGetSessions = wallnitLoginCircleGetId.loginCircleGetId(emailidLogin, passwordLogin);


                    if (!circleIdGetSessions.equals("wrong")) {
                        String checkCircleLoginConfirmation = wallnitLoginCircleCheckConfirmation.wallnitCircleCheckConfirmation(circleIdGetSessions);

                        session.setLoggedin(false);
                        session.setCircleLoggedin(true);
                        session.setCircleSession(circleIdGetSessions);
                        session.setUserSession("user_not_login");

                        if (checkCircleLoginConfirmation.equals("null")) {
                            session.setCircleCheckConfirmation("null");
                            startActivity(new Intent(WallnitLogin.this, WallnitCircleAccountConfirmation.class));
                            finish();
                        }
                        if (checkCircleLoginConfirmation.equals("0")) {
                            session.setCircleCheckConfirmation("0");
                            startActivity(new Intent(WallnitLogin.this, WallnitCircleAccountConfirmation.class));
                            finish();
                        }
                        if (checkCircleLoginConfirmation.equals("1")) {
                            session.setCircleCheckConfirmation("1");
                            String checkCircleCategory = wallnitLoginCheckCircleCategory.loginCircleCategoryCheck(circleIdGetSessions);
                            if (checkCircleCategory.equals("")) {
                                session.setCircleCreateCircle("");
                                startActivity(new Intent(WallnitLogin.this, WallnitCircleCreateOptions.class));
                                finish();
                            } else {
                                session.setCircleCreateCircle("1");
                                Intent intent = new Intent(getApplicationContext(), WallnitCircleProfile.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                }


            return null;
        }
    }
}
