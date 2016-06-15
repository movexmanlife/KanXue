package com.pediy.kanxue.ui.main;

import com.pediy.kanxue.injector.PerActivity;
import com.pediy.kanxue.injector.component.AppComponent;
import com.pediy.kanxue.injector.module.ActivityModule;
import com.pediy.kanxue.ui.login.LoginActivity;
import com.pediy.kanxue.ui.safenews.SafeNewsFragment;
import com.pediy.kanxue.ui.topic.NewTopicAdapter;
import com.pediy.kanxue.ui.topic.NewTopicFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
    void inject(NewTopicFragment fragment);
    void inject(SafeNewsFragment fragment);
}
