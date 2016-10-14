package com.mb.test;

import android.app.Application;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class App extends Application {
    public static App INS;
    public static App getInst() {
        return INS;
    }

}
