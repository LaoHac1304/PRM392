package com.example.labgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BetActivity extends AppCompatActivity {

    Button btnStart, btnBack;
    EditText etBet1,etBet2, etBet3;
    TextView txtError;

    String TAG = "BetActivity";
    protected void init() {
        btnBack = findViewById(R.id.btnBack);
        btnStart = findViewById(R.id.btnStart);
        etBet2 = findViewById(R.id.etBet2);
        etBet1 = findViewById(R.id.etBet1);
        etBet3 = findViewById(R.id.etBet3);
        txtError = (TextView) findViewById(R.id.txtError);
    }
    protected boolean validation(int num1, int num2, int num3) {
        if (num1 == 0 && num2 == 0 && num3 == 0) {
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        init();

        ThemeSong song = ThemeSong.getInstance(BetActivity.this);

        song.create();
        song.start(true);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // TODO: change to play page
                    //Intent intent = new Intent(BetActivity.this, PlayActivity.class);

                    String spaceText1 = String.valueOf(etBet1.getText());
                    String spaceText2 = String.valueOf(etBet2.getText());
                    String spaceText3 = String.valueOf(etBet3.getText());

                    int num1 = Integer.parseInt(spaceText1.isEmpty() ? "0":  spaceText1);
                    int num2 = Integer.parseInt(spaceText2.isEmpty() ? "0":  spaceText1);
                    int num3 = Integer.parseInt(spaceText3.isEmpty() ? "0":  spaceText1);
                    boolean isValid = validation(num1, num2, num3);
                    if (!isValid) {
                        txtError.setText("Please bet to at least 1 spacecraft!");
                        return;
                    }
                    //startActivity(intent);
                } catch (Exception e) {
                    txtError.setText(e.getMessage());
                }
            }
        });
    }
}