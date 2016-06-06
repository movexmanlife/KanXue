package com.pediy.kanxue;

import android.app.Application;

import com.pediy.kanxue.injector.component.AppComponent;
import com.pediy.kanxue.injector.component.DaggerAppComponent;
import com.pediy.kanxue.injector.module.AppModule;

public class App extends Application {

    private AppComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        mAppComponent =
                DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
