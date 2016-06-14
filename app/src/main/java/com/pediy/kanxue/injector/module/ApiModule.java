package com.pediy.kanxue.injector.module;

import com.pediy.kanxue.api.feedback.FeedbackApi;
import com.pediy.kanxue.api.login.LoginApi;
import com.pediy.kanxue.api.thread.ThreadApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ApiModule {
    @Provides
    @Singleton
    public LoginApi provideLoginApi(@Named("api") OkHttpClient okHttpClient) {
        return new LoginApi(okHttpClient);
    }

    @Provides
    @Singleton
    public FeedbackApi provideFeedbackApi(@Named("api") OkHttpClient okHttpClient) {
        return new FeedbackApi(okHttpClient);
    }

    @Provides
    @Singleton
    public ThreadApi provideHomepageApi(@Named("api") OkHttpClient okHttpClient) {
        return new ThreadApi(okHttpClient);
    }
}
