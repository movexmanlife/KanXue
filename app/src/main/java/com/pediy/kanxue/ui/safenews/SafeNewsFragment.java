package com.pediy.kanxue.ui.safenews;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import com.pediy.kanxue.BaseFragment;
import com.pediy.kanxue.R;
import com.pediy.kanxue.bean.NewTopicBean;
import com.pediy.kanxue.ui.main.MainComponent;
import com.pediy.kanxue.ui.topic.NewTopicAdapter;
import com.pediy.kanxue.ui.topic.NewTopicContract;
import com.pediy.kanxue.util.ToastUtils;
import com.pediy.kanxue.widget.LoadMoreRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Subscription;


public class SafeNewsFragment extends BaseFragment implements NewsContract.View,
        LoadMoreRecyclerView.LoadMoreListener, NewsAdapter.OnItemClickListener {
    @BindView(R.id.ptr_classic_frame_layout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.recyclerview)
    LoadMoreRecyclerView recyclerView;
    private Subscription mSubscription;

    @Inject
    NewsAdapter mAdapter;
    @Inject
    Activity mActivity;
    @Inject
    NewsPresenter mNewsPresenter;

    public static SafeNewsFragment newInstance() {
        SafeNewsFragment fragment = new SafeNewsFragment();
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_safe_news;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mNewsPresenter.attachView(this);
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
                mNewsPresenter.onRefresh();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNewsPresenter.detachView();
    }
}
