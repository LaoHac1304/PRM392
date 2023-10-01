package com.example.labgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {

    TextView incorrectPassword;

    private EditText etPassword;
    private Button btnSubmit;
    private Button btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        bindingData();
        btnSubmit.setOnClickListener(this);
        btnHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnSignIn){

            String password = etPassword.getText().toString();
            Account account = null;
            account = authenticate("", password);
            if (account != null){
                GlobalClass globalInstance = GlobalClass.getInstance();
                globalInstance.currentUser = account;
                Toast.makeText(this, "Login successfully", Toast.LENGTH_LONG).show();
                betForm();
            }
            else{
                incorrectPassword.setText("Password is incorrect ! Please try again.");
                Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
            }
        }
        else
            if (view.getId() == R.id.btnHome){
                homeForm();
            }

    }

    private void betForm(){
        Intent intent = new Intent(this, BetActivity.class);
        startActivity(intent);
        //finish();
    }
    private void homeForm(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //finish();
    }

    private Account authenticate(String username, String password){
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("SelectedAccount");
        username = account.getName();

        if (username.equals(account.getName())
                && account.getPassword().equals(password)) return account;
        return null;
    }

    private void bindingData(){
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSubmit = (Button) findViewById(R.id.btnSignIn);
        btnHome = (Button) findViewById(R.id.btnHome);
        incorrectPassword = (TextView) findViewById(R.id.txtIncorrectPassword);
    }
}