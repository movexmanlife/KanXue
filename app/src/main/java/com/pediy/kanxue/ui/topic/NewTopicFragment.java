package com.pediy.kanxue.ui.topic;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.pediy.kanxue.BaseFragment;
import com.pediy.kanxue.R;
import com.pediy.kanxue.api.ApiConst;
import com.pediy.kanxue.api.thread.ThreadApi;
import com.pediy.kanxue.bean.HomepageBean;
import com.pediy.kanxue.bean.NewTopicBean;
import com.pediy.kanxue.ui.main.MainComponent;
import com.pediy.kanxue.util.ToastUtils;
import com.pediy.kanxue.widget.LoadMoreRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;


public class NewTopicFragment extends BaseFragment implements NewTopicContract.View,
        LoadMoreRecyclerView.LoadMoreListener, NewTopicAdapter.OnItemClickListener  {
    @BindView(R.id.ptr_classic_frame_layout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.recyclerview)
    LoadMoreRecyclerView recyclerView;
    private Subscription mSubscription;

    @Inject
    NewTopicAdapter mAdapter;
    @Inject
    Activity mActivity;
    @Inject
    NewTopicPresenter mNewTopicPresenter;

    public static NewTopicFragment newInstance() {
        NewTopicFragment fragment = new NewTopicFragment();
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_new_topic;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mNewTopicPresenter.attachView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {
        recyclerView.setLoadMoreListener(this);
        mAdapter.setOnItemClickListener(this);
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mNewTopicPresenter.onRefresh();
            }

        });
        ptrClassicFrameLayout.autoRefresh();
    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onNewTopicClick(NewTopicBean.ThreadListEntity threadListEntity) {
        if (threadListEntity != null) {
            ToastUtils.showShort("新帖详情!");
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void renderThreads(List<NewTopicBean.ThreadListEntity> threadListEntityList) {
        mAdapter.bind(threadListEntityList);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onLoadCompleted(boolean hasMore) {

    }

    @Override
    public void onRefreshCompleted() {
        ptrClassicFrameLayout.refreshComplete();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        mNewTopicPresenter.detachView();
    }
}
