package com.pediy.kanxue.ui.templete;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.Subscription;

/**
 * 注意这里需要实现的不是TempleteContract接口，而是里面的Presenter接口。
 */
public class TempletePresenter implements TempleteContract.Presenter{
    private Subscription mSubscription;
    private TempleteContract.View mTempleteView;

    @Inject
    public TempletePresenter() {
    }

    @Override
    public void attachView(@NonNull TempleteContract.View view) {
        mTempleteView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mTempleteView = null;
    }

    /**
     * 操作逻辑
     */
    @Override
    public void operatorLogic() {
        mTempleteView.operatorView();
    }
}
