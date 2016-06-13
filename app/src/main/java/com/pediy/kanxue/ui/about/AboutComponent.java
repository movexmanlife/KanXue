package com.pediy.kanxue.ui.about;

import com.pediy.kanxue.injector.PerActivity;
import com.pediy.kanxue.injector.component.AppComponent;
import com.pediy.kanxue.injector.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface AboutComponent {
    void inject(AboutActivity activity);
}
