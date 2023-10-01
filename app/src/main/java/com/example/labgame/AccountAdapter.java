package com.example.labgame;

import android.content.Context;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AccountAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Account> accountList;

    public AccountAdapter(Context context, int layout, List<Account> accountList) {
        this.context = context;
        this.layout = layout;
        this.accountList = accountList;
    }

    @Override
    public int getCount() {
        if (accountList.isEmpty()) return 0;
        return accountList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        //binding view
        TextView txtName = (TextView) view.findViewById(R.id.textView);

        Account account = accountList.get(i);
        txtName.setText(account.getName());

        return view;
    }
}
