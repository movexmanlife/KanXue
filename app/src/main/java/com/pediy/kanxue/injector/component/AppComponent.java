package com.pediy.kanxue.injector.component;

import android.app.Activity;
import android.content.Context;

import com.pediy.kanxue.App;
import com.pediy.kanxue.BaseActivity;
import com.pediy.kanxue.api.feedback.FeedbackApi;
import com.pediy.kanxue.api.login.LoginApi;
import com.pediy.kanxue.api.thread.ThreadApi;
import com.pediy.kanxue.injector.module.ApiModule;
import com.pediy.kanxue.injector.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
  Context getAppContext();
  LoginApi getLoginApi();
  FeedbackApi getFeedbackApi();
  ThreadApi getHomepageApi();
  void inject(App app);
  void inject(BaseActivity baseActivity);
}
