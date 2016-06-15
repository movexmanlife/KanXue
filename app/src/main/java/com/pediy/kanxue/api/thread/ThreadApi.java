package com.pediy.kanxue.api.thread;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pediy.kanxue.api.ApiConst;
import com.pediy.kanxue.bean.HomepageBean;
import com.pediy.kanxue.bean.NewTopicBean;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ThreadApi {
    private ThreadService mThreadService;

    /**
     * BASE_URL必须以"/"来结尾。
     */
    private static final String BASE_URL = ApiConst.LOGIN_BASE_URL;

    public ThreadApi(OkHttpClient mOkHttpClient) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                        .client(mOkHttpClient)
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        mThreadService = retrofit.create(ThreadService.class);
    }

    /**
     * 获取看雪的版块列表
     */
    public Observable<HomepageBean> getHomepage() {
        return mThreadService.getHomepage(ApiConst.SYTLE_ID).subscribeOn(Schedulers.io());
    }

    /**
     * 获取指定版块的主题列表
     * @param id 版块id
     * @param page 版块主题列表的页码
     */
    public Observable<NewTopicBean> getForumDisplayPage(int id, int page) {
        return mThreadService.getForumDisplayPage(ApiConst.SYTLE_ID, id, page, ApiConst.DESC).subscribeOn(Schedulers.io());
    }
}
