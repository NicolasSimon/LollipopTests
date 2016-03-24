package com.example.nicolas.lollipopapp.objecttype;

/**
 * Created by Nicolas on 17/03/2016.
 * (c) Touchnote Ltd., 2015
 */
public class HomeItem {
    private int                             mResId;
    private String                          mName;

    public HomeItem(String n, int resId) {
        mName = n;
        mResId = resId;
    }

    public String getName() {
        return (mName);
    }

    public int getResId() { return (mResId); }
}
