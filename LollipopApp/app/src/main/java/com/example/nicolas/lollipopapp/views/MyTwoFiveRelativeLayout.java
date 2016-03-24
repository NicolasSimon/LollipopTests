package com.example.nicolas.lollipopapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Nicolas on 18/03/2016.
 * (c) Touchnote Ltd., 2015
 */
public class MyTwoFiveRelativeLayout extends RelativeLayout {
    public MyTwoFiveRelativeLayout(Context context) {
        super(context);
    }

    public MyTwoFiveRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTwoFiveRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTwoFiveRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * 2 / 5;
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
