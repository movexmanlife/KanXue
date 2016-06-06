package com.pediy.kanxue;

import android.support.annotation.NonNull;

public interface BasePresenter<T extends BaseView> {

  void attachView(@NonNull T view);

  void detachView();
}
