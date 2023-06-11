package com.example.mini_projet_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfos extends AppCompatActivity {

    TextView tv_userInfosFirstName;
    TextView tv_userInfosLastName;
    ImageView iv_userInfosGender;
    TextView tv_userInfosCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infos);

        tv_userInfosFirstName = findViewById(R.id.tv_userInfosFirstName);
        tv_userInfosLastName = findViewById(R.id.tv_userInfosLastName);
        iv_userInfosGender = findViewById(R.id.iv_userInfosGender);
        tv_userInfosCity = findViewById(R.id.tv_userInfosCity);

        tv_userInfosFirstName.setText(getIntent().getStringExtra("firstName"));
        tv_userInfosLastName.setText(getIntent().getStringExtra("lastName"));
        tv_userInfosCity.setText(getIntent().getStringExtra("city"));

        if (getIntent().getStringExtra("gender").equals("male")) {
            iv_userInfosGender.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.mars_symbol));
        } else {
            iv_userInfosGender.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.venus_symbol));
        }

    }
}