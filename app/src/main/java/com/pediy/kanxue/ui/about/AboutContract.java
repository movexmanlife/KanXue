package com.pediy.kanxue.ui.about;

import com.pediy.kanxue.BasePresenter;
import com.pediy.kanxue.BaseView;

public interface AboutContract {
    interface View extends BaseView {
        void showVersion(String version);
    }

    interface Presenter extends BasePresenter<View> {
    }
}
