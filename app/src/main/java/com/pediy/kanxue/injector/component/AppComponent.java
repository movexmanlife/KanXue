package com.pediy.kanxue.injector.component;

import android.app.Activity;

import com.pediy.kanxue.App;
import com.pediy.kanxue.BaseActivity;
import com.pediy.kanxue.api.login.LoginApi;
import com.pediy.kanxue.injector.module.ApiModule;
import com.pediy.kanxue.injector.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
  LoginApi getLoginApi();
  void inject(App app);
  void inject(BaseActivity baseActivity);
}
