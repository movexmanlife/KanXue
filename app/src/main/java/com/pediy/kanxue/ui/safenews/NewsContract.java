package com.pediy.kanxue.ui.safenews;


import com.pediy.kanxue.BasePresenter;
import com.pediy.kanxue.BaseView;
import com.pediy.kanxue.bean.NewTopicBean;

import java.util.List;

public interface NewsContract {
  interface View extends BaseView {

    void showLoading();

    void hideLoading();

    void renderThreads(List<NewTopicBean.ThreadListEntity> threadListEntityList);

    void onError(String error);

    void onEmpty();

    void onLoadCompleted(boolean hasMore);

    void onRefreshCompleted();
  }

  interface Presenter extends BasePresenter<View> {
    void onThreadReceive();

    void onRefresh();

    void onReload();

    void onLoadMore();
  }
}
