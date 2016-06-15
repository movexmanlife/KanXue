package com.pediy.kanxue.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pediy.kanxue.BaseActivity;
import com.pediy.kanxue.R;
import com.pediy.kanxue.injector.HasComponent;
import com.pediy.kanxue.injector.module.ActivityModule;
import com.pediy.kanxue.ui.about.AboutActivity;
import com.pediy.kanxue.ui.feedback.FeedbackActivity;
import com.pediy.kanxue.ui.home.HomePageFragment;
import com.pediy.kanxue.ui.safenews.SafeNewsFragment;
import com.pediy.kanxue.ui.setting.SettingActivity;
import com.pediy.kanxue.ui.topic.NewTopicFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View, HasComponent<MainComponent>{
    private static final String KEY_FRAGMENT_TAG = "fragment_tag";
    private static final String FRAGMENT_TAG_NEW_TOPIC = "home";
    private static final String FRAGMENT_TAG_HOME_PAGE = "product";
    private static final String FRAGMENT_TAG_NEWS = "my_assert";

    private String mFragmentCurrentTag = FRAGMENT_TAG_NEW_TOPIC;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    private ImageView mAvatarIv;
    @Inject
    MainPresenter mPresenter;
    MainComponent mMainComponent;
    private String[] mFragmentTags = new String[]{FRAGMENT_TAG_NEW_TOPIC, FRAGMENT_TAG_HOME_PAGE, FRAGMENT_TAG_NEWS};
    private NewTopicFragment mNewTopicFragment;
    private HomePageFragment mHomepageFragment;
    private SafeNewsFragment mSafeNewsFragment;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            restoreFragments();
            mFragmentCurrentTag =  savedInstanceState.getString(KEY_FRAGMENT_TAG);
        }
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
        mPresenter.attachView(this);
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
        mDrawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    public void setListener() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
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
        mMainComponent = DaggerMainComponent.builder().appComponent(getAppComponent()).
                activityModule(new ActivityModule(this)).build();
        mMainComponent.inject(this);
    }

    @Override
    public void startAboutActivity() {
        AboutActivity.startActivity(this);
    }

    @Override
    public void startFeedbackActivity() {
        FeedbackActivity.startActivity(this);
    }

    @Override
    public void startSettingActivity() {
        SettingActivity.startActivity(this);
    }

    @Override
    public void closeDrawers() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentView, fragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public MainComponent getComponent() {
        return mMainComponent;
    }

    @Override
    public void onTabSelect(MenuItem menuItem) {
        int id = menuItem.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(manager, transaction);

        if (id == R.id.nav_new_topic) {
            mFragmentCurrentTag = FRAGMENT_TAG_NEW_TOPIC;

            if (mNewTopicFragment == null) {
                mNewTopicFragment = NewTopicFragment.newInstance();
                transaction.add(R.id.contentView, mNewTopicFragment, FRAGMENT_TAG_NEW_TOPIC);
            }
            transaction.show(mNewTopicFragment);
        } else if (id == R.id.nav_home) {
            mFragmentCurrentTag = FRAGMENT_TAG_HOME_PAGE;

            if (mHomepageFragment == null) {
                mHomepageFragment = HomePageFragment.newInstance();
                transaction.add(R.id.contentView, mHomepageFragment, FRAGMENT_TAG_HOME_PAGE);
            }
            transaction.show(mHomepageFragment);
        } else if (id == R.id.nav_safe_news) {
            mFragmentCurrentTag = FRAGMENT_TAG_NEWS;

            if (mSafeNewsFragment == null) {
                mSafeNewsFragment = SafeNewsFragment.newInstance();
                transaction.add(R.id.contentView, mSafeNewsFragment, FRAGMENT_TAG_NEWS);
            }
            transaction.show(mSafeNewsFragment);
        }

        transaction.commit();
    }

    /**
     * 先全部隐藏
     * @param fragmentManager
     * @param transaction
     */
    private void hideFragments(FragmentManager fragmentManager, FragmentTransaction transaction) {
        for (int i = 0; i < mFragmentTags.length; i++) {
            Fragment fragment = fragmentManager.findFragmentByTag(mFragmentTags[i]);
            if (fragment != null && fragment.isVisible()) {
                transaction.hide(fragment);
            }
        }
    }

    private void restoreFragments() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mNewTopicFragment = (NewTopicFragment) manager.findFragmentByTag(FRAGMENT_TAG_NEW_TOPIC);
        transaction.hide(mNewTopicFragment);
        mHomepageFragment = (HomePageFragment) manager.findFragmentByTag(FRAGMENT_TAG_NEWS);
        transaction.hide(mHomepageFragment);
        mSafeNewsFragment = (SafeNewsFragment) manager.findFragmentByTag(FRAGMENT_TAG_HOME_PAGE);
        transaction.hide(mSafeNewsFragment);
        transaction.commit();
    }
}
