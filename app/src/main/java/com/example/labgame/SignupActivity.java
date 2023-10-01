package com.example.labgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etPassword;
    private Button btnSubmit;
    private Button btnHome;
    private ArrayList<Account> accountArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindingData();

        // Get the account list from the intent extras and make a copy
        accountArrayList = new ArrayList<>();
        ArrayList<Account> originalAccountList = (ArrayList<Account>)
                getIntent().getSerializableExtra("ListAccount");
        if (originalAccountList != null) {
            accountArrayList.addAll(originalAccountList);
        }

        btnSubmit.setOnClickListener(this);
        btnHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignIn){
            register();
        }
    }

    private void register() {
        String username = etName.getText().toString();
        String password = etPassword.getText().toString();

        // Check if the username already exists
        for (Account account : accountArrayList) {
            if (account.getName().equals(username)) {
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
                return;
            }
        }

        // Create a new account
        Account account = new Account(username, password, 100);
        accountArrayList.add(account);
        // Return the updated account list to MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("ListAccount", accountArrayList);
        setResult(RESULT_OK, resultIntent);
        finish(); // Finish SignupActivity
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void bindingData() {
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        btnSubmit = findViewById(R.id.btnSignIn);
        btnHome = findViewById(R.id.btnHome);
    }
}