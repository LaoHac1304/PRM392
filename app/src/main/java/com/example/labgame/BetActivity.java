package com.example.labgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BetActivity extends AppCompatActivity {

    Button btnStart, btnBack;
    EditText etBet1,etBet2, etBet3;
    TextView txtError, coin;

    GlobalClass globalClass;
    String TAG = "BetActivity";
    protected void init() {
        btnBack = findViewById(R.id.btnBack);
        btnStart = findViewById(R.id.btnStart);
        etBet1 = findViewById(R.id.etBet1);
        etBet2 = findViewById(R.id.etBet2);
        etBet3 = findViewById(R.id.etBet3);
        txtError = (TextView) findViewById(R.id.txtError);
        globalClass = GlobalClass.getInstance();
        coin = findViewById(R.id.btnCoin);

        coin.setText(globalClass.currentUser.getCoin() + "");
    }
    protected int validation(int num1, int num2, int num3) {
        if (num1 == 0 && num2 == 0 && num3 == 0) {
            return 1;
        }
        if ( globalClass.currentUser.getCoin()  < num1 + num2 + num3 )
        {
            return 2;
        }
        return 0;
    }

    protected void setCoin (int num1,int num2,int num3) {
        globalClass.currentUser.setBet1(num1);
        globalClass.currentUser.setBet2(num2);
        globalClass.currentUser.setBet3(num3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        init();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // TODO: change to play page
                    Intent intent = new Intent(BetActivity.this, RaceActivity.class);

                    String spaceText1 = String.valueOf(etBet1.getText());
                    String spaceText2 = String.valueOf(etBet2.getText());
                    String spaceText3 = String.valueOf(etBet3.getText());

                    int num1 = Integer.parseInt(spaceText1.isEmpty() ? "0":  spaceText1);
                    int num2 = Integer.parseInt(spaceText2.isEmpty() ? "0":  spaceText2);
                    int num3 = Integer.parseInt(spaceText3.isEmpty() ? "0":  spaceText3);
                    Log.d(TAG, "onClick: " + num2);
                    System.out.println(num2);
                    int isValid = validation(num1, num2, num3);
                    if (isValid > 0) {
                        if (isValid == 1) {
                            txtError.setText("Please bet to at least 1 spacecraft !");
                            return;
                        }
                        txtError.setText("The bet coin must less than current coin!");
                        return;
                    }
                    setCoin(num1, num2, num3);
                    startActivity(intent);
                } catch (Exception e) {
                    txtError.setText("Invalid input number");
                }
            }
        });
    }
}