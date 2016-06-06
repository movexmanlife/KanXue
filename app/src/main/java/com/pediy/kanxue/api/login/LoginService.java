package com.pediy.kanxue.api.login;

import com.pediy.kanxue.bean.LoginBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface LoginService {
    @FormUrlEncoded
    @POST("login.php")
    Observable<LoginBean> login(@Query("do")String doAction, @Query("styleid")int styleid,
                                @Field("vb_login_username") String userName, @Field("vb_login_md5password")String passwd,
                                @Field("vb_login_md5password_utf")String passwdUtf, @FieldMap Map<String, String> params);
}
