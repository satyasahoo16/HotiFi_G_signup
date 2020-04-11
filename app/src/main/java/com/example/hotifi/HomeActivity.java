package com.example.hotifi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button homeBtn, sellBtn, historyBtn, profileBtn, leadershipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        homeBtn=findViewById(R.id.homeBtn);
        sellBtn=findViewById(R.id.sellBtn);
        historyBtn=findViewById(R.id.historyBtn);
        leadershipBtn=findViewById(R.id.leadershipBtn);
        profileBtn=findViewById(R.id.profileBtn);

        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(HomeActivity.this, InternetActivity.class);
                startActivity(it);
                finish();
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(it);
                finish();
            }
        });

        leadershipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(HomeActivity.this, LeaderboardActivity.class);
                startActivity(it);
                finish();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(HomeActivity.this, AccountActivity.class);
                startActivity(it);
                finish();
            }
        });


    }
}
