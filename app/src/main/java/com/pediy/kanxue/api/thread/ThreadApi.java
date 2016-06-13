package com.pediy.kanxue.api.thread;

import com.pediy.kanxue.api.ApiConst;
import com.pediy.kanxue.bean.LoginBean;
import com.pediy.kanxue.util.SimpleHASH;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ThreadApi {
    private ThreadService mThreadService;

    /**
     * BASE_URL必须以"/"来结尾。
     */
    private static final String BASE_URL = ApiConst.LOGIN_BASE_URL;

    public ThreadApi(OkHttpClient mOkHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .client(mOkHttpClient)
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        mThreadService = retrofit.create(ThreadService.class);
    }

    public Observable<LoginBean> getHomepage() {
        return mThreadService.getHomepage(12).subscribeOn(Schedulers.io());
    }
}
