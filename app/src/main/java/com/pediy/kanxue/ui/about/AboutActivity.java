package com.pediy.kanxue.ui.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pediy.kanxue.BaseActivity;
import com.pediy.kanxue.R;
import com.pediy.kanxue.injector.module.ActivityModule;
import com.pediy.kanxue.util.PackageUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity implements AboutContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_about)
    TextView mAboutTv;
    @BindView(R.id.layout_about)
    LinearLayout mAboutLayout;
    @BindView(R.id.layout_user)
    LinearLayout mUserLayout;
    @BindView(R.id.tv_version)
    TextView mVersionTv;
    @BindView(R.id.layout_author)
    LinearLayout mAuthorLayout;
    @BindView(R.id.layout_project)
    LinearLayout mProjectLayout;

    @Inject
    AboutPresenter mAboutPresenter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mAboutPresenter.attachView(this);
        initToolbar();
    }

    private void initToolbar() {
        initToolBar(mToolbar);
        setTitle("关于");
    }

    @Override
    public void setListener() {
    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {
        DaggerAboutComponent.builder().
                appComponent(getAppComponent()).
                activityModule(new ActivityModule(this))
                .build().inject(this);
    }

    @Override
    public void showVersion(String version) {
        mVersionTv.setText(version);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAboutPresenter.detachView();
    }
}
