package com.example.mini_projet_01;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

public class UserDetailsDialog extends DialogFragment {

    User user;

    public UserDetailsDialog(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_user_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ConstraintLayout cl_itemUserDetails = view.findViewById(R.id.cl_itemUserDetails);
        TextView tv_itemUserDetailsFirstName = view.findViewById(R.id.tv_itemUserDetailsFirstName);
        TextView tv_itemUserDetailsLastName = view.findViewById(R.id.tv_itemUserDetailsLastName);
        TextView tv_itemUserDetailsCity = view.findViewById(R.id.tv_itemUserDetailsCity);

        tv_itemUserDetailsFirstName.setText(user.getFirstName());
        tv_itemUserDetailsLastName.setText(user.getLastName());
        tv_itemUserDetailsCity.setText(user.getCity());

        if (user.getGender().equals("male")) {
            cl_itemUserDetails.setBackgroundColor(Color.parseColor("#ADD8E6"));
        } else {
            cl_itemUserDetails.setBackgroundColor(Color.parseColor("#ffb6c1"));
        }

    }
}
