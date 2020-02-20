package com.bestarmedia.ui;

import android.app.Application;
import android.net.http.HttpResponseCache;
import android.util.Log;

import java.io.File;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        try {
            File cacheDir = new File(getCacheDir(), "http");
            HttpResponseCache.install(cacheDir, 64L * 1024L * 1024L);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "处理缓存出错了", e);
        }
    }
}
