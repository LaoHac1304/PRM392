package com.example.labgame;

import java.util.ArrayList;

public class GlobalClass {

    public static ArrayList<Account> accountArrayList;
    public static Account currentUser;
    private static GlobalClass instance;

    private GlobalClass() {
        if (accountArrayList == null) {
            accountArrayList = new ArrayList<>();
            accountArrayList.add(new Account("Jun", "123456", 100));
            accountArrayList.add(new Account("Alex", "888888", 150));
            accountArrayList.add(new Account("Chip", "162534", 200));
        }
        if (currentUser == null){
            currentUser = new Account();
        }
    }

    public static synchronized GlobalClass getInstance() {
        if (instance == null) {
            instance = new GlobalClass();
        }
        return instance;
    }
}
