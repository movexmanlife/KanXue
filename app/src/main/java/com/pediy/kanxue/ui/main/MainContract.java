package com.pediy.kanxue.ui.main;


import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.pediy.kanxue.BasePresenter;
import com.pediy.kanxue.BaseView;

public interface MainContract {
    interface View extends BaseView {
        void startAboutActivity();
        void startFeedbackActivity();
        void startSettingActivity();

        void closeDrawers();
        void setTitle(CharSequence title);
        void showFragment(Fragment fragment);
        void onTabSelect(MenuItem menuItem);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
