package com.wallnit.wallnitlogin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wallnit.wallnitlogin.WallnitCheckNetworkConnection.ConnectionDetector;
import com.wallnit.wallnitlogin.WallnitForget.WallnitForgetGetNumber;

import org.w3c.dom.Text;

public class ForgetPassword_StepTwo extends AppCompatActivity {

    TextView title_forget,num_forget,continue_forget,forget_login,forget_terms_service,forget_no_show;
    EditText verification_code_write,verification_code_rec;
    String code_first,code_second;
    ConnectionDetector connectionDetector;
    String getMoNumber[];
    WallnitForgetGetNumber wallnitForgetGetNumber;
    String getFirstNo,getSecondNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forget_password__step_two);
        title_forget=(TextView) findViewById(R.id.forget_passwordsecond_title);
        title_forget.setText("We have send your code to:");
        verification_code_write = (EditText) findViewById(R.id.forget_password_enterverifi);
        verification_code_rec = (EditText) findViewById(R.id.verification_code_receive);
        continue_forget = (TextView) findViewById(R.id.forget_password_steptwo);
        forget_login = (TextView) findViewById(R.id.forget_login_first);
        forget_terms_service = (TextView) findViewById(R.id.forget_terms_ofservice_first);
        forget_no_show = (TextView) findViewById(R.id.forget_password_number_show);
        connectionDetector = new ConnectionDetector(this);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String code_verifi = extras.getString("forget_verifi_code");
        String emailidget = extras.getString("emailid");
        String email_user_circle = extras.getString("email_circle_user");
        verification_code_rec.setText(code_verifi);


        if(connectionDetector.isConnected())
        {
            getMoNumber = wallnitForgetGetNumber.getvaluesCircleProfile(emailidget,email_user_circle);

            getFirstNo = getMoNumber[0];
            getSecondNo = getMoNumber[1];

            forget_no_show.setText(getFirstNo+"********"+getSecondNo);

        } else {
            Toast.makeText(ForgetPassword_StepTwo.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        forget_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword_StepTwo.this,WallnitLogin.class));
                finish();
            }
        });

        forget_terms_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword_StepTwo.this,WallnitTerms_of_Service.class));
                finish();
            }
        });


        continue_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code_first = verification_code_write.getText().toString();
                code_second = verification_code_rec.getText().toString();
                if(connectionDetector.isConnected()) {
                    if (code_first.trim().length() == 0) {
                        verification_code_write.requestFocus();
                    } else if (code_first.equals(code_second)) {
                        startActivity(new Intent(ForgetPassword_StepTwo.this, ForgetPassword_StepThird.class));
                        finish();
                    } else {
                        Toast.makeText(ForgetPassword_StepTwo.this, "Enter correct code", Toast.LENGTH_SHORT).show();
                        verification_code_write.setText(null);
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        verification_code_write.requestFocus();
                    }
                } else {
                    Toast.makeText(ForgetPassword_StepTwo.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
