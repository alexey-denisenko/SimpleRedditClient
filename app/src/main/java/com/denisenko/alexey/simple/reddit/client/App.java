package com.denisenko.alexey.simple.reddit.client;

import android.app.Application;
import android.os.StrictMode;

import com.codemonkeylabs.fpslibrary.TinyDancer;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());

            TinyDancer.create()
                    .show(this);
        }
    }

    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .build();
    }
}
