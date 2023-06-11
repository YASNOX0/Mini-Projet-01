package com.example.mini_projet_01;import android.content.Context;import android.view.LayoutInflater;import android.view.MotionEvent;import android.view.View;import android.view.ViewGroup;import android.widget.BaseAdapter;import android.widget.ImageView;import android.widget.TextView;import androidx.appcompat.app.AlertDialog;import java.util.ArrayList;public class UsersAdapter extends BaseAdapter {    Context context;    private ArrayList<User> users;    private final int DOUBLE_CLICK_TIME = 1000;    private int clickCounter = 0;    public UsersAdapter(Context context, ArrayList<User> users) {        this.users = users;        this.context = context;    }    @Override    public int getCount() {        return users.size();    }    @Override    public Object getItem(int i) {        return users.get(i);    }    @Override    public long getItemId(int i) {        return 0;    }    @Override    public View getView(int i, View view, ViewGroup viewGroup) {        view = LayoutInflater.from(context).inflate(R.layout.item_user, null);        User user = users.get(i);        TextView tv_itemUserFullName = view.findViewById(R.id.tv_itemUserFullName);        TextView tv_itemUserCity = view.findViewById(R.id.tv_itemUserCity);        ImageView iv_itemUserChecked = view.findViewById(R.id.iv_itemUserChecked);        tv_itemUserFullName.setText(user.fullName());        tv_itemUserCity.setText(user.getCity());        view.setOnLongClickListener(view1 -> {            AlertDialog.Builder builder = new AlertDialog.Builder(context);            builder.setTitle(String.format("Details of user %d", i + 1));            builder.setMessage(user.toString());            builder.show();            return true;        });        view.setOnTouchListener(new View.OnTouchListener() {            long lastClickTime = 0;            @Override            public boolean onTouch(View view, MotionEvent motionEvent) {                switch (motionEvent.getAction()) {//                    case MotionEvent.ACTION_DOWN://                        Toast.makeText(context, "down", Toast.LENGTH_SHORT).show();//                        break;                    case MotionEvent.ACTION_UP:                        long clickTime = System.currentTimeMillis();                        clickCounter++;                        if ((clickTime - lastClickTime <= DOUBLE_CLICK_TIME) && clickCounter < 3) {                            iv_itemUserChecked.setVisibility(iv_itemUserChecked.getVisibility() == View.INVISIBLE ? View.VISIBLE : View.INVISIBLE);                        } else {                            lastClickTime = clickTime;                            clickCounter = 0;                        }                        break;                }                return true;            }        });        return view;    }}