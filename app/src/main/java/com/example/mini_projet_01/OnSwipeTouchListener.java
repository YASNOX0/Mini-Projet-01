package com.example.mini_projet_01;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

abstract public class OnSwipeTouchListener implements View.OnTouchListener {

    private final int THRESHOLD = 100;
    private Context context;
    private GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {
        this.context = context;
        this.gestureDetector = new GestureDetector(context, new GestureListener());
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getY() >= THRESHOLD) {
                swipeLeft();
            } else if (e2.getX() - e1.getY() >= THRESHOLD) {
                swipeRight();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    abstract public void swipeLeft();

    abstract public void swipeRight();

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }
}
