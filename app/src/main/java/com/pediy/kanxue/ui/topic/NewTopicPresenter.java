package com.pediy.kanxue.ui.topic;

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
public class NewTopicPresenter implements NewTopicContract.Presenter {

    private ThreadApi mThreadApi;

    private NewTopicContract.View mNewTopicView;
    private Subscription mSubscription;
    private List<Thread> threads = new ArrayList<>();
    private int page = 1;
    private boolean hasNextPage = true;

    @Inject
    public NewTopicPresenter(ThreadApi threadApi) {
        mThreadApi = threadApi;
    }

    @Override
    public void onThreadReceive() {
        mNewTopicView.showLoading();
        loadNewTopicList(page);
    }

    private void loadNewTopicList(final int page) {
        this.page = page;
        mSubscription = mThreadApi.getForumDisplayPage(ApiConst.NEW_FORUM_ID, page)
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
                        mNewTopicView.onRefreshCompleted();
                        if (subEntityList != null && !subEntityList.isEmpty()) {
                            mNewTopicView.renderThreads(subEntityList);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mNewTopicView.onRefreshCompleted();
                        throwable.printStackTrace();
                    }
                });
    }

    private void loadThreadError() {
        if (threads.isEmpty()) {
            mNewTopicView.onError("数据加载失败");
        } else {
            mNewTopicView.hideLoading();
            mNewTopicView.onLoadCompleted(true);
            mNewTopicView.onRefreshCompleted();
            ToastUtils.showShort("数据加载失败");
        }
    }

    public void onRefresh() {
        page = 1;
        loadNewTopicList(page);
    }

    public void onReload() {
        mNewTopicView.showLoading();
        loadNewTopicList(page);
    }

    public void onLoadMore() {
        if (!hasNextPage) {
            ToastUtils.showShort("没有更多了~");
            mNewTopicView.onLoadCompleted(false);
            return;
        }
        loadNewTopicList(++page);
    }

    @Override
    public void attachView(@NonNull NewTopicContract.View view) {
        mNewTopicView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mNewTopicView = null;
    }
}
