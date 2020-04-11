package com.example.hotifi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class    PhoneActivity extends AppCompatActivity {

    private EditText etPhoneNumber;
    private Button btnSendOTP;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);
        btnSendOTP=findViewById(R.id.btnSendOTP);

        mAuth = FirebaseAuth.getInstance();

       // mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()

        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = etPhoneNumber.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    etPhoneNumber.setError("Enter a valid mobile");
                    etPhoneNumber.requestFocus();
                    return;
                }

                Intent intent = new Intent(PhoneActivity.this, OTPActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }


        });
    }
}

