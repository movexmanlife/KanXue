package com.pediy.kanxue.ui.login;

import com.pediy.kanxue.BasePresenter;
import com.pediy.kanxue.BaseView;

public interface LoginContract {
    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        void showUserNameError(String error);

        void showPassWordError(String error);

        void loginSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void login(String userName, String passwd);
    }
}
