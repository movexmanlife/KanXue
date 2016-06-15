package com.pediy.kanxue;

import android.app.Application;

import com.google.gson.reflect.TypeToken;
import com.pediy.kanxue.injector.component.AppComponent;
import com.pediy.kanxue.injector.component.DaggerAppComponent;
import com.pediy.kanxue.injector.module.AppModule;
import com.pediy.kanxue.util.GsonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private AppComponent mAppComponent;
    private static App mApp;


    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();

        mApp = this;
    }

    private void initComponent() {
        mAppComponent =
                DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static App getInstance() {
        return mApp;
    }
}
