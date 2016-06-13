package com.pediy.kanxue.ui.feedback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pediy.kanxue.BaseActivity;
import com.pediy.kanxue.R;
import com.pediy.kanxue.injector.module.ActivityModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackActivity extends BaseActivity implements FeedbackContract.View {

    @Inject
    FeedbackPresenter mFeedbackPresenter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_content)
    EditText mContentEt;
    @BindView(R.id.btn_commit)
    Button mCommitBtn;

    private MaterialDialog mDialog;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initData() {
        mDialog = new MaterialDialog.Builder(this).title("提示").content("提交中").progress(true, 0).build();
    }

    @Override
    public void initView() {
        mFeedbackPresenter.attachView(this);
        initToolbar();
    }

    private void initToolbar() {
        initToolBar(mToolbar);
        setTitle("反馈");
    }

    @Override
    public void setListener() {
        mCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {
        DaggerFeedbackComponent.builder().
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
    public void showContentError(String error) {

    }

    @Override
    public void commitSuccess() {
        mCommitBtn.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1500);
    }

    private void commit() {
        String content = mContentEt.getText().toString();
        mFeedbackPresenter.commit("", "",content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFeedbackPresenter.detachView();
    }
}
