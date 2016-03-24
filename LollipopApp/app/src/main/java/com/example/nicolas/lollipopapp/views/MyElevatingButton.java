package com.example.nicolas.lollipopapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Nicolas on 17/03/2016.
 * (c) Touchnote Ltd., 2015
 */
public class MyElevatingButton extends Button {
    public MyElevatingButton(Context context) {
        super(context);
    }

    public MyElevatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyElevatingButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyElevatingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                animate().setDuration(100).scaleX(1.4f).scaleY(1.4f).translationZ(20).start();
                return super.onTouchEvent(event);
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                animate().setDuration(100).scaleX(1.0f).scaleY(1.0f).translationZ(0).start();
                return super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }
}
