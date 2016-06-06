package com.pediy.kanxue.ui.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.pediy.kanxue.api.login.LoginApi;
import com.pediy.kanxue.bean.LoginBean;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 注意这里需要实现的不是LoginContract接口，而是里面的Presenter接口。
 */
public class LoginPresenter implements LoginContract.Presenter{
    private LoginApi mLoginApi;
    private Subscription mSubscription;
    private LoginContract.View mLoginView;

    @Inject
    public LoginPresenter(LoginApi loginApi) {
        this.mLoginApi = loginApi;
    }

    @Override
    public void login(String userName, String passwd) {
        mLoginView.showLoading();
        mSubscription = mLoginApi.login(userName, passwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LoginBean>() {
                    @Override
                    public void call(LoginBean loginBean) {
                        mLoginView.hideLoading();
                        mLoginView.loginSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mLoginView.hideLoading();
                    }
                });
    }

    @Override
    public void attachView(@NonNull LoginContract.View view) {
        mLoginView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mLoginView = null;
    }
}
