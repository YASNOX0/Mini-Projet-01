package com.example.mini_projet_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter {

    Context context;
    private ArrayList<User> users;

    public UsersAdapter(Context context, ArrayList<User> users) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_user, null);

        TextView tv_itemUserFullName = view.findViewById(R.id.tv_itemUserFullName);
        TextView tv_itemUserCity = view.findViewById(R.id.tv_itemUserCity);
        TextView tv_itemUserGender = view.findViewById(R.id.tv_itemUserGender);

        tv_itemUserFullName.setText(users.get(i).fullName());
        tv_itemUserCity.setText(users.get(i).getCity());
        tv_itemUserGender.setText(users.get(i).getGender());

        return view;
    }
}
