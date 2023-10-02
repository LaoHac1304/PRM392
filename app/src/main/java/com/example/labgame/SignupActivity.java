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

    private TextView txtErrorName;
    private TextView txtErrorPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindingData();

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
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void register() {
        String username = etName.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            if (username.isEmpty()) txtErrorName.setText("Name can't be empty!!!");
            if (password.isEmpty()) txtErrorPass.setText("Password can't be empty!!!");
            return;
        }

        for (Account account : accountArrayList) {
            if (account.getName().equals(username)) {
                Toast.makeText(this, "Account was existed", Toast.LENGTH_LONG).show();
                return;
            }
        }

        Account account = new Account(username, password, 100);
        accountArrayList.add(account);

        GlobalClass globalInstance = GlobalClass.getInstance();
        ArrayList<Account> accountsGlobal = globalInstance.accountArrayList;
        accountsGlobal.add(account);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("ListAccount", accountArrayList);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void bindingData() {
        txtErrorName = findViewById(R.id.txtNameError);
        txtErrorPass = findViewById(R.id.txtPassError);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        btnSubmit = findViewById(R.id.btnSignIn);
        btnHome = findViewById(R.id.btnHome);
    }
}