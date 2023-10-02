package com.example.labgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private List<Account> accountList = new ArrayList<>();
    ListView lv_1;
    Button btnCreateNewAccount ;

    ArrayList<Account> accountArrayList = new ArrayList<>();
    public static AccountAdapter accountAdapter;

    private static final int REQUEST_CODE_SIGNUP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_1 = findViewById(R.id.listView_1);
        bindingData();
        GlobalClass globalInstance = GlobalClass.getInstance();
        ArrayList<Account> accountsGlobal = globalInstance.accountArrayList;
        accountAdapter = new AccountAdapter(this, R.layout.activity_account, accountsGlobal);
        lv_1.setAdapter(accountAdapter);

        lv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Chuyển sang một giao diện mới (Activity khác) khi người dùng click vào mục
                Account selectedAccount = accountArrayList.get(position);
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                intent.putExtra("SelectedAccount", (Serializable) selectedAccount);
                startActivity(intent);
            }
        });

        // Sự kiện khi click button create new account
        btnCreateNewAccount = findViewById(R.id.buttonCreateNewAccount);
        btnCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                ArrayList<Account> accountsGlobal = getGlobalAccount();
                intent.putExtra("ListAccount", (Serializable) accountsGlobal);
                startActivityForResult(intent, REQUEST_CODE_SIGNUP); // Start SignupActivity with requestCode
            }
        });
    }

    // Handle the result from SignupActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<Account> accountsGlobal = getGlobalAccount();
        if (requestCode == REQUEST_CODE_SIGNUP && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<Account> updatedAccountList = (ArrayList<Account>) data.getSerializableExtra("ListAccount");
                accountArrayList.clear();
                accountArrayList.addAll(updatedAccountList);
                accountAdapter.notifyDataSetChanged();
            }
        }
    }

    private ArrayList<Account> getGlobalAccount(){
        GlobalClass globalInstance = GlobalClass.getInstance();
        ArrayList<Account> accountsGlobal = globalInstance.accountArrayList;
        return accountsGlobal;
    }

    private void bindingData() {

    }
}