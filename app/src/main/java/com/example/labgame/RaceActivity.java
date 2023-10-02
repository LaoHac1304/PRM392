package com.example.labgame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RaceActivity extends AppCompatActivity {
    TextView tvCoin, txtCountdown;
    SeekBar imgvBet1, imgvBet2, imgvBet3;
    int bet1 = 10, bet2 = 10, bet3 = 10, plus, res, countdown = 3;
    private static final int MIN = 10;
    private static final int MAX = 95;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race);

        tvCoin = (TextView) findViewById(R.id.textViewCoin);
        txtCountdown = (TextView) findViewById(R.id.countdownText);
        imgvBet1 = (SeekBar) findViewById(R.id.imageViewBet1);
        imgvBet2 = (SeekBar) findViewById(R.id.imageViewBet2);
        imgvBet3 = (SeekBar) findViewById(R.id.imageViewBet3);

        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){
                txtCountdown.setText("Start in " + countdown);
                countdown--;
            }
            public void onFinish(){
                txtCountdown.setText("");
                imgvBet1.setThumb(getResources().getDrawable(R.drawable.bet1active));
                imgvBet2.setThumb(getResources().getDrawable(R.drawable.bet2active));
                imgvBet3.setThumb(getResources().getDrawable(R.drawable.bet3active));
                getStart();
            }
        }.start();
    }

    private int getMin(int a, int b) {
        if (a > b) return b;
        return a;
    }

    private void moveToResultActivity(int id) {
        Intent intent = new Intent(RaceActivity.this, ResultActivity.class);
        intent.putExtra("winner", id);
        startActivity(intent);
    }

    private void plusBet(int id) {
        plus = new Random().nextInt(4);
        switch (id) {
            case 1:
                bet1 = getMin(bet1 + plus, MAX);
                imgvBet1.setProgress(bet1, true);
                if (bet1 == MAX) moveToResultActivity(1);
                break;
            case 2:
                bet2 = getMin(bet2 + plus, MAX);
                imgvBet2.setProgress(bet2, true);
                if (bet2 == MAX) moveToResultActivity(2);
                break;
            case 3:
                bet3 = getMin(bet3 + plus, MAX);
                imgvBet3.setProgress(bet3, true);
                if (bet3 == MAX) moveToResultActivity(3);
                break;
        }
    }

    private void getStart() {
        new CountDownTimer(10000, 100){
            public void onTick(long millisUntilFinished){
                if (bet1 < MAX && bet2 < MAX && bet3 < MAX) plusBet(1);
                if (bet1 < MAX && bet2 < MAX && bet3 < MAX) plusBet(2);
                if (bet1 < MAX && bet2 < MAX && bet3 < MAX) plusBet(3);
            }
            public void onFinish(){
            }
        }.start();
    }
}