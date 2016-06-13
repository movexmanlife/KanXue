package com.pediy.kanxue.api.feedback;

import com.pediy.kanxue.bean.LoginBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface FeedbackService {
    @FormUrlEncoded
    @POST("sendmessage.php")
    Observable<LoginBean> commit(@Query("do") String doAction, @Query("styleid") int styleid,
                                @Field("name") String name, @Field("email") String email,
                                @Field("message") String msg, @FieldMap Map<String, String> params);
}
