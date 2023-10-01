package com.example.labgame;

import android.widget.TextView;
import  java.io.Serializable;

public class Account implements Serializable{

    private String name;
    private String password;
    private int coin;

    public Account(String name, String password,int coin ) {
        this.name = name;
        this.password = password;
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }



}
