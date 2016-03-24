package com.example.nicolas.lollipopapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Nicolas on 17/03/2016.
 * (c) Touchnote Ltd., 2015
 */
public class MyTwoThreeImageView extends ImageView {
    public MyTwoThreeImageView(Context context) {
        super(context);
    }

    public MyTwoThreeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTwoThreeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTwoThreeImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * 2 / 3;
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
