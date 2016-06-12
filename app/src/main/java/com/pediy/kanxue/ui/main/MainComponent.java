package com.pediy.kanxue.ui.main;

import com.pediy.kanxue.injector.PerActivity;
import com.pediy.kanxue.injector.component.AppComponent;
import com.pediy.kanxue.injector.module.ActivityModule;
import com.pediy.kanxue.ui.login.LoginActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
