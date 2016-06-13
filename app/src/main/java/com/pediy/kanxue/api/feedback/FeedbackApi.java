package com.pediy.kanxue.api.feedback;

import com.pediy.kanxue.api.ApiConst;
import com.pediy.kanxue.bean.LoginBean;
import com.pediy.kanxue.util.SimpleHASH;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * TODO!!!这里有一个疑问就是如果Gson转化出现错误，如何处理
 */
public class FeedbackApi {
    private FeedbackService mFeedbackService;

    /**
     * BASE_URL必须以"/"来结尾。
     */
    private static final String BASE_URL = ApiConst.LOGIN_BASE_URL;

    public FeedbackApi(OkHttpClient mOkHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(mOkHttpClient)
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        mFeedbackService = retrofit.create(FeedbackService.class);
    }

    public Observable<LoginBean> commit(String name, String email, String msg, String token) {
        Map<String, String> params = new HashMap<>();
        /**
         * do=docontactus参数必须传递
         */
        params.put("do", "docontactus");
        params.put("subject", "0");
        params.put("securitytoken", token);
        return mFeedbackService.commit("docontactus", 12, name, email, msg, params).subscribeOn(Schedulers.io());
    }
}
