package com.pediy.kanxue.ui.feedback;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.pediy.kanxue.api.feedback.FeedbackApi;
import com.pediy.kanxue.api.login.LoginApi;
import com.pediy.kanxue.bean.LoginBean;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 注意这里需要实现的不是FeedbackContract接口，而是里面的Presenter接口。
 */
public class FeedbackPresenter implements FeedbackContract.Presenter{
    private FeedbackApi mFeedbackApi;
    private Subscription mSubscription;
    private FeedbackContract.View mFeedbackView;

    @Inject
    public FeedbackPresenter(FeedbackApi feedbackApi) {
        this.mFeedbackApi = feedbackApi;
    }

    @Override
    public void commit(String name, String email, String content) {
        if (TextUtils.isEmpty(content)) {
            mFeedbackView.showContentError("内容不能为空");
            return;
        }

        mFeedbackView.showLoading();
        mSubscription = mFeedbackApi.commit(name, email, content, "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LoginBean>() {
                    @Override
                    public void call(LoginBean loginBean) {
                        mFeedbackView.hideLoading();
                        mFeedbackView.commitSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mFeedbackView.hideLoading();
                    }
                });
    }

    @Override
    public void attachView(@NonNull FeedbackContract.View view) {
        mFeedbackView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mFeedbackView = null;
    }
}
