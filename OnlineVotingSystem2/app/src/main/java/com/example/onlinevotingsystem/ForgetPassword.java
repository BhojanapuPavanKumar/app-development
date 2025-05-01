package com.example.onlinevotingsystem;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassword extends AppCompatActivity {
    private Button bt1,bt2,bt3;
    private EditText et1,et2;
    private String generatedOtp,enteredOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.forgetpassword);
        et1 = (EditText) findViewById(R.id.mobileno);
        bt1 = (Button) findViewById(R.id.sendotp);
        bt2 = (Button) findViewById(R.id.back);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobileNumber = et1.getText().toString().trim();

                if (validateMobileNumber(mobileNumber)) {
                    sendOtp(mobileNumber);
                    setContentView(R.layout.verfiyotp);
                    et2 = (EditText) findViewById(R.id.otp);
                    bt3 = (Button) findViewById(R.id.verifyotp);
                    bt3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(verifyOtp(et2.getText().toString())){
                                setContentView(R.layout.newpassword);

                            }
                        }
                    });
                } else {
                    Toast.makeText(ForgetPassword.this, "Please enter a valid 10-digit mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateMobileNumber(String mobileNumber) {
        return mobileNumber.length() == 10 && mobileNumber.matches("[0-9]+");
    }

    private void sendOtp(String mobileNumber) {
        generatedOtp = String.format("%04d", (int) (Math.random() * 10000));
        Toast.makeText(ForgetPassword.this, "OTP sent to " + mobileNumber + ": " + generatedOtp, Toast.LENGTH_LONG).show();
    }

    private boolean verifyOtp(String inputOtp) {
        if (inputOtp.equals(generatedOtp)) {
            Toast.makeText(ForgetPassword.this, "OTP verified successfully", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(ForgetPassword.this, "Invalid or expired OTP", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}