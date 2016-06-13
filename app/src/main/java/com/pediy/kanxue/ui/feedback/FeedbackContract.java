package com.pediy.kanxue.ui.feedback;

import com.pediy.kanxue.BasePresenter;
import com.pediy.kanxue.BaseView;

public interface FeedbackContract {
    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        void showContentError(String error);

        void commitSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void commit(String name, String email, String content);
    }
}
