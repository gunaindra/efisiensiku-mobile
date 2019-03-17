package com.busefisensi.efisiensiku;

import android.app.Application;

import com.javasoul.swframework.component.SWToast;

public class Efisiensiku extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SWToast.init(getApplicationContext());
    }
}
