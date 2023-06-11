package com.example.mini_projet_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

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
        User user = users.get(i);

        TextView tv_itemUserFullName = view.findViewById(R.id.tv_itemUserFullName);
        TextView tv_itemUserCity = view.findViewById(R.id.tv_itemUserCity);
        Button btn_itemUserDetails = view.findViewById(R.id.btn_itemUserDetails);

        tv_itemUserFullName.setText(user.fullName());
        tv_itemUserCity.setText(user.getCity());
        btn_itemUserDetails.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(String.format("Details of user %d", i + 1));
            builder.setMessage(user.toString());
            builder.show();

        });

        return view;
    }
}
