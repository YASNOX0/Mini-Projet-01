package com.example.mini_projet_01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_loadUsers, btn_quit;
    ListView lv_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_loadUsers = findViewById(R.id.btn_loadUsers);
        btn_quit = findViewById(R.id.btn_quit);
        lv_users = findViewById(R.id.lv_users);

        btn_loadUsers.setOnClickListener(this);
        btn_quit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btn_loadUsers)) {
            UsersAdapter usersAdapter = new UsersAdapter(this, getUsers());
            lv_users.setAdapter(usersAdapter);
        } else if (view.equals(btn_quit)) {
            finish();
        }

    }

    private ArrayList<User> getUsers() {

        ArrayList<User> userFullNames = new ArrayList<>();

        try (InputStream inputStream = getAssets().open("users.json")) {

            int code;
            StringBuilder stringBuilder = new StringBuilder();
            String jsonString;

            code = inputStream.read();
            while (code != -1) {
                stringBuilder.append((char) code);
                code = inputStream.read();
            }
            jsonString = stringBuilder.toString();

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("users");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject user = jsonArray.getJSONObject(i);
                JSONObject userName = user.getJSONObject("name");

                userFullNames.add(new User(userName.getString("first"),
                        userName.getString("last"),
                        user.getString("gender"),
                        user.getString("city")));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return userFullNames;
    }
}