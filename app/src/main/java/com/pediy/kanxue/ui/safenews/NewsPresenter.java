package com.pediy.kanxue.ui.safenews;

import android.support.annotation.NonNull;

import com.pediy.kanxue.api.ApiConst;
import com.pediy.kanxue.api.thread.ThreadApi;
import com.pediy.kanxue.bean.NewTopicBean;
import com.pediy.kanxue.injector.PerActivity;
import com.pediy.kanxue.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

@PerActivity
public class NewsPresenter implements NewsContract.Presenter {

    private ThreadApi mThreadApi;

    private NewsContract.View mNewsView;
    private Subscription mSubscription;
    private List<Thread> threads = new ArrayList<>();
    private int page = 1;
    private boolean hasNextPage = true;

    @Inject
    public NewsPresenter(ThreadApi threadApi) {
        mThreadApi = threadApi;
    }

    @Override
    public void onThreadReceive() {
        mNewsView.showLoading();
        loadNewTopicList(page);
    }

    private void loadNewTopicList(final int page) {
        this.page = page;
        mSubscription = mThreadApi.getForumDisplayPage(ApiConst.SECURITY_FORUM_ID, page)
                .map(new Func1<NewTopicBean, List<NewTopicBean.ThreadListEntity>>() {
                    @Override
                    public List<NewTopicBean.ThreadListEntity> call(NewTopicBean topicBean) {
                        if (page == 1) {
                            threads.clear();
                        }
                        if (topicBean == null || topicBean.getThreadList() == null) {
                            return null;
                        }
                        return topicBean.getThreadList();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewTopicBean.ThreadListEntity>>() {
                    @Override
                    public void call(List<NewTopicBean.ThreadListEntity> subEntityList) {
                        mNewsView.onRefreshCompleted();
                        if (subEntityList != null && !subEntityList.isEmpty()) {
                            mNewsView.renderThreads(subEntityList);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mNewsView.onRefreshCompleted();
                        throwable.printStackTrace();
                    }
                });
    }

    private void loadThreadError() {
        if (threads.isEmpty()) {
            mNewsView.onError("数据加载失败");
        } else {
            mNewsView.hideLoading();
            mNewsView.onLoadCompleted(true);
            mNewsView.onRefreshCompleted();
            ToastUtils.showShort("数据加载失败");
        }
    }

    public void onRefresh() {
        page = 1;
        loadNewTopicList(page);
    }

    public void onReload() {
        mNewsView.showLoading();
        loadNewTopicList(page);
    }

    public void onLoadMore() {
        if (!hasNextPage) {
            ToastUtils.showShort("没有更多了~");
            mNewsView.onLoadCompleted(false);
            return;
        }
        loadNewTopicList(++page);
    }

    @Override
    public void attachView(@NonNull NewsContract.View view) {
        mNewsView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mNewsView = null;
    }
}
