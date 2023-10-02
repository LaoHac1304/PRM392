package com.example.labgame;

import android.widget.TextView;
import  java.io.Serializable;

public class Account implements Serializable{

    private String name;
    private String password;
    private int coin;

    private int bet1;
    private int bet2;
    private int bet3;

    public Account(){

    }

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

    public int getBet1() {
        return bet1;
    }

    public int getBet2() {
        return bet2;
    }

    public int getBet3() {
        return bet3;
    }

    public void setBet1(int bet1) {
        this.bet1 = bet1;
    }

    public void setBet2(int bet2) {
        this.bet2 = bet2;
    }

    public void setBet3(int bet3) {
        this.bet3 = bet3;
    }

    public void resetBet() {
        bet1 = bet2 = bet3 = 0;
    }

    public int sumBet () {
        return bet1 + bet2 + bet3;
    }
}
