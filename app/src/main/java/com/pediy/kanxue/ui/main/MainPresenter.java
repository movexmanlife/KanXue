package com.pediy.kanxue.ui.main;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.pediy.kanxue.api.login.LoginApi;
import com.pediy.kanxue.bean.LoginBean;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 注意这里需要实现的不是MainContract接口，而是里面的Presenter接口。
 */
public class MainPresenter implements MainContract.Presenter{
    private Subscription mSubscription;
    private MainContract.View mMainView;

    @Inject
    public MainPresenter() {
    }

//    @Override
//    public void login(String userName, String passwd) {
//
//    }

    @Override
    public void attachView(@NonNull MainContract.View view) {
        mMainView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mMainView = null;
    }

    public void onNavigationClick(MenuItem menuItem) {

    }
}
