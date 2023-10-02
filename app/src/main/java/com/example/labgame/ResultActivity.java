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
    int bet1;
    int bet2;
    int bet3;
    int total;
    int coin;
    int reward;
    int lose;

    GlobalClass globalClass;
    Account currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalClass = GlobalClass.getInstance();
        currentUser = globalClass.currentUser;

        int bet1 = currentUser.getBet1();
        int bet2 = currentUser.getBet2();
        int bet3 = currentUser.getBet3();

        setContentView(R.layout.activity_result);
        mapping();
        Intent intent = getIntent();
        int winner = intent.getIntExtra("winner", 0);
        coin = currentUser.getCoin();
        calculateCoin(winner);
        tvCoin.setText("Your coin: " + coin + " coins");
        tvReward.setText("Reward: " + reward + " coins");
        tvLose.setText("Lose: " + lose + " coins");
        tvTotal.setText("Total: " + total + " coins");
        btnHome.setOnClickListener(view -> {
            Intent newIntent = new Intent(ResultActivity.this, MainActivity.class);
            currentUser.setCoin(total);
            currentUser.resetBet();
            globalClass.accountArrayList.replaceAll(account -> {
                if (account.getName().equals(currentUser.getName()))
                    account.setCoin(currentUser.getCoin());
                return account;
            });
            startActivity(newIntent);
        });
        btnBet.setOnClickListener(view -> {
            Intent newIntent = new Intent(ResultActivity.this, BetActivity.class);
            newIntent.putExtra("Total", total);
            currentUser.setCoin(total);
            currentUser.resetBet();
            startActivity(newIntent);
        });
        currentUser.setCoin(total);
        currentUser.resetBet();
    }

    private void mapping() {
        ivWinner = (ImageView) findViewById(R.id.ivWinner);
        tvCoin = (TextView) findViewById(R.id.tvCoin);
        tvReward = (TextView) findViewById(R.id.tvReward);
        tvLose = (TextView) findViewById(R.id.tvLose);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        btnBet = (Button) findViewById(R.id.btnBet);
        btnHome = (Button) findViewById(R.id.btnHome);
        bet1 = globalClass.currentUser.getBet1();
        bet2 = globalClass.currentUser.getBet2();
        bet3 = globalClass.currentUser.getBet3();
    }

    private void calculateCoin(int winner) {

        if (winner == 1) {
            ivWinner.setImageResource(R.drawable.onefirewin);
            reward = bet1;
            lose = bet2 + bet3;
        }
        else if (winner == 2) {
            ivWinner.setImageResource(R.drawable.twofirewin);
            reward = bet2;
            lose = bet1 + bet3;
        }
        else {
            ivWinner.setImageResource(R.drawable.threefirewin);
            reward = bet3;
            lose = bet1 + bet2;
        }
        total = coin + reward - lose;
    }
}