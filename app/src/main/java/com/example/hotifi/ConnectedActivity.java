package com.example.hotifi;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConnectedActivity extends AppCompatActivity {

    private Button btnDisconnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);

        btnDisconnected=findViewById(R.id.dscntdBtn);
    }
}
