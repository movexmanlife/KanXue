package com.pediy.kanxue.ui.templete;

import com.pediy.kanxue.BasePresenter;
import com.pediy.kanxue.BaseView;

public interface TempleteContract {
    interface View extends BaseView {
        /**
         *  操作View
         */
        void operatorView();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 操作逻辑
         */
        void operatorLogic();
    }
}
