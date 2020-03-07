package com.example.hotifi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneActivity extends AppCompatActivity {

    private EditText etPhoneNumber;
    private Button btnSendOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);
        btnSendOTP=findViewById(R.id.btnSendOTP);

        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneActivity.this, OTPActivity.class));
                finish();
            }
        });
    }
}
