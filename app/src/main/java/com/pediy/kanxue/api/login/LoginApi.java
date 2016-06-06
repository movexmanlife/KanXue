package com.pediy.kanxue.api.login;

import com.pediy.kanxue.api.ApiConst;
import com.pediy.kanxue.bean.LoginBean;
import com.pediy.kanxue.util.SimpleHASH;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * TODO!!!这里有一个疑问就是如果Gson转化出现错误，如何处理
 */
public class LoginApi {
    private LoginService mLoginService;

    /**
     * BASE_URL必须以"/"来结尾。
     */
    private static final String BASE_URL = ApiConst.LOGIN_BASE_URL;

    public LoginApi(OkHttpClient mOkHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .client(mOkHttpClient)
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        mLoginService = retrofit.create(LoginService.class);
    }

    public Observable<LoginBean> login(String userName, String passwd) {
        Map<String, String> params = new HashMap<>();
        /**
         * do=login参数必须传递
         */
        params.put("do", "login");
        params.put("cookieuser", "1");
        params.put("securitytoken", "guest");
        return mLoginService.login("login", 12, userName, SimpleHASH.md5(strToEnt(passwd.trim())), SimpleHASH.md5(passwd.trim()), params).subscribeOn(Schedulers.io());
    }

    /**
     * 登录前用户密码预处理
     *
     * @param input
     *            去掉首位空格的用户密码
     * @return
     */
    private String strToEnt(String input) {
        String output = "";

        for (int i = 0; i < input.length(); i++) {
            int ucode = input.codePointAt(i);
            String tmp = "";

            if (ucode > 255) {
                while (ucode >= 1) {
                    tmp = "0123456789".charAt(ucode % 10) + tmp;
                    ucode /= 10;
                }

                if (tmp == "") {
                    tmp = "0";
                }

                tmp = "#" + tmp;
                tmp = "&" + tmp;
                tmp = tmp + ";";
                output += tmp;
            } else {
                output += input.charAt(i);
            }
        }
        return output;
    }
}
