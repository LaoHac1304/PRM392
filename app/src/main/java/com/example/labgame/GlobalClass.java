package com.example.labgame;

import java.util.ArrayList;

public class GlobalClass {

    public static ArrayList<Account> accountArrayList;

    private GlobalClass() {
        if (accountArrayList == null) {
            accountArrayList = new ArrayList<>();
        }
    }
}
