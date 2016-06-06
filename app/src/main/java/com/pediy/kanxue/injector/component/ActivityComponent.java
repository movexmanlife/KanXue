package com.pediy.kanxue.injector.component;

import android.app.Activity;

import com.pediy.kanxue.injector.PerActivity;
import com.pediy.kanxue.injector.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    public Activity getActivity();
}
