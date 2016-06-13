package com.pediy.kanxue.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.pediy.kanxue.BaseActivity;
import com.pediy.kanxue.R;

import butterknife.BindView;

public class SettingActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        initToolBar(mToolbar);
        setTitle(R.string.setting);
        getFragmentManager().beginTransaction().replace(R.id.content, new SettingFragment()).commit();
    }

    @Override
    public void setListener() {

    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {

    }
}
