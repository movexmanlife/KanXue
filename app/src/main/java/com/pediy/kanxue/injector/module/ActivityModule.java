package com.pediy.kanxue.injector.module;

import android.app.Activity;

import com.pediy.kanxue.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return this.mActivity;
    }
}
