package com.pediy.kanxue.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageView;

import com.pediy.kanxue.BaseActivity;
import com.pediy.kanxue.R;
import com.pediy.kanxue.injector.component.DaggerActivityComponent;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    private ImageView mAvatarIv;
    @Inject
    MainPresenter mPresenter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);  // 设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.navigation_new_topic);

        View headerView = mNavigationView.getHeaderView(0);
        mAvatarIv = (ImageView)headerView.findViewById(R.id.iv_avatar);

        // 创建返回键，并实现打开关或闭监听
        ActionBarDrawerToggle drawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        mDrawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public void setListener() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mPresenter.onNavigationClick(menuItem);
                        return true;
                    }
                });
    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {
    }

}
