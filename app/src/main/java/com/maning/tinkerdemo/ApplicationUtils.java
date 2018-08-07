package com.maning.tinkerdemo;

import android.app.Application;
import android.widget.Toast;

/**
 * @author : maning
 * @desc :
 */
public class ApplicationUtils {


    private static Application mApp;

    public static void init(Application application) {
        mApp = application;

        Toast.makeText(mApp.getApplicationContext(), "启动", Toast.LENGTH_SHORT).show();

    }

}
