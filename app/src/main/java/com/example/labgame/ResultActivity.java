package com.example.labgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    ImageView ivWinner;
    TextView tvCoin;
    TextView tvReward;
    TextView tvLose;
    TextView tvTotal;
    Button btnBet;
    Button btnHome;
    int total;
    int coin;
    int reward;
    int lose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mapping();
        coin = 90;
        reward = 10;
        lose = 80;
        total = coin - lose + reward;
        tvCoin.setText("Your coin: " + coin + " coins");
        tvReward.setText("Reward: " + reward + " coins");
        tvLose.setText("Lose: " + lose + " coins");
        tvTotal.setText("Total: " + total + " coins");
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
        });
        btnBet.setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this, BetActivity.class);
            intent.putExtra("Total", total);
            startActivity(intent);
        });
    }

    private void mapping() {
        ivWinner = (ImageView) findViewById(R.id.ivWinner);
        tvCoin = (TextView) findViewById(R.id.tvCoin);
        tvReward = (TextView) findViewById(R.id.tvReward);
        tvLose = (TextView) findViewById(R.id.tvLose);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        btnBet = (Button) findViewById(R.id.btnBet);
        btnHome = (Button) findViewById(R.id.btnHome);
    }
}