package com.example.mini_projet_01;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UserDetailsDialog extends DialogFragment {

    User user;
    ImageView iv_itemUserDetailsImage;

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
        iv_itemUserDetailsImage = view.findViewById(R.id.iv_itemUserDetailsImage);

        tv_itemUserDetailsFirstName.setText(user.getFirstName());
        tv_itemUserDetailsLastName.setText(user.getLastName());
        tv_itemUserDetailsCity.setText(user.getCity());

        RetrieveImages retrieveImages = new RetrieveImages();
        retrieveImages.execute(user.getImage());

        if (user.getGender().equals("male")) {
            cl_itemUserDetails.setBackgroundColor(Color.parseColor("#ADD8E6"));
        } else {
            cl_itemUserDetails.setBackgroundColor(Color.parseColor("#ffb6c1"));
        }

    }

    class RetrieveImages extends AsyncTask<String, Drawable, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            try {
                InputStream inputStream = requireActivity().getAssets().open(strings[0]);
                publishProgress(Drawable.createFromStream(inputStream, "userImage"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Drawable... values) {
            super.onProgressUpdate(values);
            iv_itemUserDetailsImage.setImageDrawable(values[0]);
        }
    }

}
