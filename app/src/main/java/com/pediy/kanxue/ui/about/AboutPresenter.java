package com.pediy.kanxue.ui.about;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pediy.kanxue.R;
import com.pediy.kanxue.api.login.LoginApi;
import com.pediy.kanxue.bean.LoginBean;
import com.pediy.kanxue.util.PackageUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 注意这里需要实现的不是AboutContract接口，而是里面的Presenter接口。
 */
public class AboutPresenter implements AboutContract.Presenter{
    private Context mContext;
    private AboutContract.View mAboutView;

    @Inject
    public AboutPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void attachView(@NonNull AboutContract.View view) {
        mAboutView = view;
        String version = mContext.getString(R.string.version_format, PackageUtils.getVersionName(mContext));
        mAboutView.showVersion(version);
    }

    @Override
    public void detachView() {
        mAboutView = null;
    }
}
