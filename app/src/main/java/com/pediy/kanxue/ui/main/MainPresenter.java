package com.pediy.kanxue.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.pediy.kanxue.R;
import com.pediy.kanxue.api.login.LoginApi;
import com.pediy.kanxue.bean.LoginBean;
import com.pediy.kanxue.ui.about.AboutActivity;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 注意这里需要实现的不是MainContract接口，而是里面的Presenter接口。
 */
public class MainPresenter implements MainContract.Presenter{
    private Context mContext;
    private Subscription mSubscription;
    private MainContract.View mMainView;

    @Inject
    public MainPresenter(Context context) {
        this.mContext = context;
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
        switch (menuItem.getItemId()) {
            case R.id.nav_about:
                /**
                 * 这里注意应该把类似的跳转放在MainActivity中，而不是Presenter中
                 * AboutActivity.startActivity(mContext);
                 */
                mMainView.startAboutActivity();
                break;
            case R.id.nav_feedback:
                mMainView.startFeedbackActivity();
                break;
            case R.id.nav_setting:
                mMainView.startSettingActivity();
                break;
            default:
                break;
        }
    }
}
