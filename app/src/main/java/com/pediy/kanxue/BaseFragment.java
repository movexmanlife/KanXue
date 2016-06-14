package com.pediy.kanxue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pediy.kanxue.injector.component.AppComponent;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    public abstract int getContentViewId();
    public abstract void initData();
    public abstract void initView();
    public abstract void setListener();
    /**
     * 注入Injector
     */
    public abstract void initInjector();

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentViewId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initInjector();
        initUserInterface();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initUserInterface() {
        initData();
        initView();
        setListener();
    }

    protected AppComponent getAppComponent() {
        return ((App)getActivity().getApplication()).getAppComponent();
    }
}
