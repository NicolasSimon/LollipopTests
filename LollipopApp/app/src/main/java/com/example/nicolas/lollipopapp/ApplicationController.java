package com.example.nicolas.lollipopapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nicolas on 17/03/2016.
 * (c) Touchnote Ltd., 2015
 */
public class ApplicationController extends Application {
    private static Context                      mContext;

    public static Context getContext() {
        return (mContext);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
