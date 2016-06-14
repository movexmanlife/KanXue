package com.pediy.kanxue.ui.home;

import com.pediy.kanxue.injector.PerActivity;
import com.pediy.kanxue.injector.component.AppComponent;
import com.pediy.kanxue.injector.module.ActivityModule;
import com.pediy.kanxue.ui.feedback.FeedbackActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface HomepageComponent {
    void inject(HomePageFragment fragment);
}
