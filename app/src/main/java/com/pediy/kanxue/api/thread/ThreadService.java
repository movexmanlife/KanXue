package com.pediy.kanxue.api.thread;

import com.pediy.kanxue.bean.LoginBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ThreadService {
    @FormUrlEncoded
    @GET("index.php")
    Observable<LoginBean> getHomepage(@Query("styleid") int styleid);
}
