package com.example.mini_projet_01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_loadUsers, btn_quit;
    RadioButton rb_males, rb_females;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_loadUsers = findViewById(R.id.btn_loadUsers);
        btn_quit = findViewById(R.id.btn_quit);
        rb_males = findViewById(R.id.rb_males);
        rb_females = findViewById(R.id.rb_females);

        btn_loadUsers.setOnClickListener(this);
        btn_quit.setOnClickListener(this);

        rb_males.setChecked(true);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btn_loadUsers)) {
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

                JSONObject jsonObject, user , userName;
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("users");
                StringBuilder stringBuilderUserInfo = new StringBuilder();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String userCity, userInfo , userGender;

                for (int i = 0; i < jsonArray.length(); i++) {
                    user = jsonArray.getJSONObject(i);
                    userGender = user.get("gender").toString();
                    userCity = user.get("city").toString();
                    userName = user.getJSONObject("name");
                    userInfo = String.format("%s %s | %s%n", userName.get("first"), userName.get("last"), userCity);

                    if (rb_males.isChecked() && userGender.equals("male")) {
                        builder.setTitle("Male users");
                        stringBuilderUserInfo.append(userInfo);
                    } else if (rb_females.isChecked() && userGender.equals("female")) {
                        builder.setTitle("Female users");
                        stringBuilderUserInfo.append(userInfo);
                    }

                }
                builder.setMessage(stringBuilderUserInfo).show();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } else if (view.equals(btn_quit)) {
            finish();
        }

    }
}