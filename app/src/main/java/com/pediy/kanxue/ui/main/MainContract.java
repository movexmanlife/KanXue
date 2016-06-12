package com.pediy.kanxue.ui.main;

import com.pediy.kanxue.BasePresenter;
import com.pediy.kanxue.BaseView;

public interface MainContract {
    interface View extends BaseView {
/*        void showLoading();

        void hideLoading();

        void showUserNameError(String error);

        void showPassWordError(String error);

        void loginSuccess();*/
    }

    interface Presenter extends BasePresenter<View> {
        /*void login(String userName, String passwd);*/
    }
}
