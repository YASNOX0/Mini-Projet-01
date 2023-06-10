package com.example.mini_projet_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_loadUsers, btn_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_loadUsers = findViewById(R.id.btn_loadUsers);
        btn_quit = findViewById(R.id.btn_quit);

        btn_loadUsers.setOnClickListener(this);
        btn_quit.setOnClickListener(this);
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

                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("users");
                StringBuilder stringBuilderFullName = new StringBuilder();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject user = jsonArray.getJSONObject(i);
                    JSONObject userName  = user.getJSONObject("name");
                    String fullName = String.format("%s %s%n", userName.get("first"), userName.get("last"));

                    stringBuilderFullName.append(fullName);
                }
                Toast.makeText(this , stringBuilderFullName , Toast.LENGTH_SHORT).show();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } else if (view.equals(btn_quit)) {
            finish();
        }

    }
}