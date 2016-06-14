package com.pediy.kanxue.api.thread;

import com.pediy.kanxue.bean.HomepageBean;
import com.pediy.kanxue.bean.NewTopicBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ThreadService {
    @GET("index.php")
    Observable<HomepageBean> getHomepage(@Query("styleid") int styleid);

    @GET("forumdisplay.php")
    Observable<NewTopicBean> getForumDisplayPage(@Query("styleid") int styleid,
                                                 @Query("f") int id,
                                                 @Query("page") int page,
                                                 @Query("order") String order);
}
