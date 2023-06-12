package com.example.mini_projet_01;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_loadUsers;
    TextView tv_quit;
    ListView lv_users;
    ConstraintLayout cl_users;
    ProgressBar progressBar;
    private static final int PROGRESS_BAR_ID = 123;
    private boolean isRetrievingData = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_loadUsers = findViewById(R.id.btn_loadUsers);
        tv_quit = findViewById(R.id.tv_quit);
        lv_users = findViewById(R.id.lv_users);
        cl_users = findViewById(R.id.cl_users);

        btn_loadUsers.setOnClickListener(this);
        tv_quit.setOnClickListener(this);

        tv_quit.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void swipeLeft() {
                finish();
            }
        });

        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        progressBar.setId(PROGRESS_BAR_ID);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        progressBar.setLayoutParams(params);

    }

    @Override
    public void onClick(View view) {
        if (view.equals(btn_loadUsers) && !isRetrievingData) {
            RetrieveUsers retrieveUsers = new RetrieveUsers();
            retrieveUsers.start();

            isRetrievingData = true;

            cl_users.addView(progressBar);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(cl_users);
            constraintSet.connect(progressBar.getId(), ConstraintSet.TOP, btn_loadUsers.getId(), ConstraintSet.BOTTOM, 0);
            constraintSet.connect(progressBar.getId(), ConstraintSet.BOTTOM, tv_quit.getId(), ConstraintSet.TOP, 0);
            constraintSet.connect(progressBar.getId(), ConstraintSet.START, cl_users.getId(), ConstraintSet.START, 0);
            constraintSet.connect(progressBar.getId(), ConstraintSet.END, cl_users.getId(), ConstraintSet.END, 0);
            constraintSet.applyTo(cl_users);
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

                Thread.sleep(250);
                userFullNames.add(new User(userName.getString("first"),
                        userName.getString("last"),
                        user.getString("gender"),
                        user.getString("city")));
            }
        } catch (IOException | JSONException | InterruptedException e) {
            e.printStackTrace();
        }
        return userFullNames;
    }

    class RetrieveUsers extends Thread {
        @Override
        public void run() {
            getUsers();
            runOnUiThread(() -> {
                UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this, getUsers());
                lv_users.setAdapter(usersAdapter);
                cl_users.removeView(progressBar);
            });
        }

    }
}