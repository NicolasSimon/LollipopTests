package com.example.nicolas.lollipopapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Nicolas on 18/03/2016.
 * (c) Touchnote Ltd., 2015
 */
public class MyElevatingRelativeLayout extends RelativeLayout {
    public MyElevatingRelativeLayout(Context context) {
        super(context);
    }

    public MyElevatingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyElevatingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyElevatingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                animate().setDuration(100).scaleX(1.15f).scaleY(1.15f).translationZ(20).start();
                return super.onTouchEvent(event);
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                animate().setDuration(100).scaleX(1.0f).scaleY(1.0f).translationZ(0).start();
                return super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }
}
