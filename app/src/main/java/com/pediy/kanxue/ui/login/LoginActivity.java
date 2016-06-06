package com.pediy.kanxue.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pediy.kanxue.BaseActivity;
import com.pediy.kanxue.R;
import com.pediy.kanxue.injector.module.ActivityModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.et_username)
    AppCompatEditText mUsernameEt;
    @BindView(R.id.et_passwd)
    AppCompatEditText mPasswdEt;
    @BindView(R.id.btn_login)
    Button mLoginBtn;

    @Inject
    LoginPresenter mLoginPresenter;
    private MaterialDialog mDialog;

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        mDialog = new MaterialDialog.Builder(this).title("提示").content("登录中").progress(true, 0).build();
    }

    @Override
    public void initView() {
        mLoginPresenter.attachView(this);
    }

    @Override
    public void setListener() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {
        DaggerLoginComponent.builder().
                appComponent(getAppComponent()).
                activityModule(new ActivityModule(this))
                .build().inject(this);
    }

    @Override
    public void showLoading() {
        if (!isFinishing() && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (!isFinishing() && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showUserNameError(String error) {

    }

    @Override
    public void showPassWordError(String error) {

    }

    @Override
    public void loginSuccess() {
        mLoginBtn.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1500);
    }

    private void login() {
        String name = mUsernameEt.getText().toString();
        String passwd = mPasswdEt.getText().toString();
        mLoginPresenter.login(name, passwd);
    }
}
