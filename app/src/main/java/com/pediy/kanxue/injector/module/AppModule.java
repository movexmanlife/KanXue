package com.pediy.kanxue.injector.module;

import android.app.Activity;
import android.content.Context;

import com.pediy.kanxue.injector.PerActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class AppModule {
    private final Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides @Singleton
    public Context provideAppContext() {
        return mContext.getApplicationContext();
    }

    /**
     * 加入调试信息
     */
    @Provides @Singleton @Named("api")
    OkHttpClient provideApiOkHttpClient() {
        OkHttpClient.Builder builder =
                new OkHttpClient.Builder().connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                        .readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        /**
         * 会将POST中的参数打印出来，且把服务器返回的数据全部打印
         */
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        return builder.build();
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient(@Named("api") OkHttpClient mOkHttpClient) {
        OkHttpClient.Builder builder = mOkHttpClient.newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        builder.interceptors().clear();
        return builder.build();
    }
}
