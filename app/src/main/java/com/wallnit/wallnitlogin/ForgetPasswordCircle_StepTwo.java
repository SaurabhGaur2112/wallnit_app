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

public class ForgetPasswordCircle_StepTwo extends AppCompatActivity {

    TextView title_forget;
    EditText verification_code_write,verification_code_rec;
    TextView continue_forget,forget_login,forget_terms_service,forget_nu;
    ConnectionDetector cd;
    String getMoNumber[];
    String getFirstNo,getSecondNo;
    WallnitForgetGetNumber wallnitForgetGetNumber;
    String code_first,code_second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forget_password_circle__step_two);
        title_forget = (TextView) findViewById(R.id.forget_passwordsecond_title_circle);
        title_forget.setText("We have send your code to:");
        verification_code_write = (EditText) findViewById(R.id.forget_password_enterverifi_circle);
        verification_code_rec = (EditText) findViewById(R.id.verification_code_receive_circle);
        continue_forget = (TextView) findViewById(R.id.forget_password_steptwo_circle);
        forget_login = (TextView) findViewById(R.id.forget_login_first_circle);
        forget_terms_service = (TextView) findViewById(R.id.forget_terms_ofservice_first_circle);
        forget_nu = (TextView) findViewById(R.id.forget_password_number_show_circle);
        wallnitForgetGetNumber = new WallnitForgetGetNumber();

        cd = new ConnectionDetector(this);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String code_verifi = extras.getString("forget_verifi_code");
        String emailidget = extras.getString("emailid");
        String email_user_circle = extras.getString("email_circle_user");
        verification_code_rec.setText(code_verifi);

        if(cd.isConnected())
        {
            getMoNumber = wallnitForgetGetNumber.getvaluesCircleProfile(emailidget,email_user_circle);

            getFirstNo = getMoNumber[0];
            getSecondNo = getMoNumber[1];

            forget_nu.setText(getFirstNo+"********"+getSecondNo);
        } else {
            Toast.makeText(ForgetPasswordCircle_StepTwo.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        forget_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPasswordCircle_StepTwo.this,WallnitLogin.class));
                finish();
            }
        });

        forget_terms_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPasswordCircle_StepTwo.this,WallnitTerms_of_Service.class));
                finish();
            }
        });

        continue_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code_first = verification_code_write.getText().toString();
                code_second = verification_code_rec.getText().toString();
                if(cd.isConnected())
                {
                    if(code_first.trim().length()==0){
                        verification_code_write.requestFocus();
                    } else if(code_first.equals(code_second)){
                        startActivity(new Intent(ForgetPasswordCircle_StepTwo.this, WallnitForgetCircleStepThird.class));
                        finish();
                    } else {
                        Toast.makeText(ForgetPasswordCircle_StepTwo.this, "Enter correct code", Toast.LENGTH_SHORT).show();
                        verification_code_write.setText(null);
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        verification_code_write.requestFocus();
                    }
                } else {
                    Toast.makeText(ForgetPasswordCircle_StepTwo.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
