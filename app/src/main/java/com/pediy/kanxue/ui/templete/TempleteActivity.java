package com.pediy.kanxue.ui.templete;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pediy.kanxue.BaseActivity;
import com.pediy.kanxue.R;
import com.pediy.kanxue.injector.module.ActivityModule;
import com.pediy.kanxue.ui.login.DaggerLoginComponent;

import javax.inject.Inject;

import butterknife.BindView;

public class TempleteActivity extends BaseActivity implements TempleteContract.View {

    @Inject
    TempletePresenter mTempletePresenter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TempleteActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_templete;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mTempletePresenter.attachView(this);
    }

    @Override
    public void setListener() {
    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {
        DaggerTempleteComponent.builder().
                appComponent(getAppComponent()).
                activityModule(new ActivityModule(this))
                .build().inject(this);
    }

    @Override
    public void operatorView() {

    }
}
