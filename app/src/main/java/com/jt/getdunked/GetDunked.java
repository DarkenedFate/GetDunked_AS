package com.jt.getdunked;

import android.app.Application;
import android.content.Context;

/**
 * Created by JT on 5/30/2014.
 */
public class GetDunked extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        GetDunked.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return GetDunked.context;
    }
}
