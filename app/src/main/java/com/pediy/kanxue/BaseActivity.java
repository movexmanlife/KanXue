package com.pediy.kanxue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pediy.kanxue.injector.component.ActivityComponent;
import com.pediy.kanxue.injector.component.AppComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected ActivityComponent mActivityComponent;

    public abstract int getContentViewId();
    public abstract void initData();
    public abstract void initView();
    public abstract void setListener();
    /**
     * 注入Injector
     */
    public abstract void initInjector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initInjector();
        initUserInterface();
        AppManager.getAppManager().addActivity(this);
    }

    protected AppComponent getAppComponent() {
        return ((App)getApplication()).getAppComponent();
    }

    private void initUserInterface() {
        ButterKnife.bind(this);
        initData();
        initView();
        setListener();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        return v;
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void initToolBar(Toolbar mToolBar) {
        if (null != mToolBar) {
            setSupportActionBar(mToolBar);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
