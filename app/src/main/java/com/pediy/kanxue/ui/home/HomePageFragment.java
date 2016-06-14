package com.pediy.kanxue.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pediy.kanxue.BaseFragment;
import com.pediy.kanxue.R;
import com.pediy.kanxue.adapter.DividerDecoration;
import com.pediy.kanxue.adapter.HomePageAdapter;
import com.pediy.kanxue.adapter.RecyclerItemClickListener;
import com.pediy.kanxue.api.thread.ThreadApi;
import com.pediy.kanxue.bean.LoginBean;
import com.pediy.kanxue.bean.TopicBean;
import com.pediy.kanxue.injector.component.AppComponent;
import com.pediy.kanxue.injector.module.ActivityModule;
import com.pediy.kanxue.ui.main.MainActivity;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;


public class HomePageFragment extends BaseFragment {
    @BindView(R.id.ptr_classic_frame_layout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private Object mSubscription;
    @Inject
    ThreadApi mThreadApi;
    HomePageAdapter adapter;

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_home_page;
    }

    @Override
    public void initData() {
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                requestData();
            }
        });
    }

    private void requestData() {
        mSubscription = mThreadApi.getHomepage()
                .map(new Func1<TopicBean, List<TopicBean.ForumbitsEntity.ForumSubTitleEntity>>() {
                    @Override
                    public List<TopicBean.ForumbitsEntity.ForumSubTitleEntity> call(TopicBean topicBean) {
                        if (topicBean == null || topicBean.getForumbits() == null) {
                            return null;
                        }
                        List<TopicBean.ForumbitsEntity.ForumSubTitleEntity> list = TopicBean.convertToStickyData(topicBean.getForumbits());
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<TopicBean.ForumbitsEntity.ForumSubTitleEntity>>() {
                    @Override
                    public void call(List<TopicBean.ForumbitsEntity.ForumSubTitleEntity> subEntityList) {
                        ptrClassicFrameLayout.refreshComplete();
                        if (subEntityList != null && !subEntityList.isEmpty()) {
                            adapter.clear();
                            adapter.addAll(subEntityList);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ptrClassicFrameLayout.refreshComplete();
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void initView() {
        // Set adapter populated with example dummy data
        adapter = new HomePageAdapter();

        recyclerView.setAdapter(adapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerDecoration(getActivity()));

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);
        // Add touch listeners
        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(recyclerView, headersDecor);
        touchListener.setOnHeaderClickListener(
                new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View header, int position, long headerId) {
                        Toast.makeText(getActivity(), "Header position: " + position + ", id: " + headerId,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        recyclerView.addOnItemTouchListener(touchListener);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // adapter.remove(adapter.getItem(position));
            }
        }));
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });
    }

    @Override
    public void setListener() {

    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {
        DaggerHomepageComponent.builder().
                appComponent(getAppComponent()).
                activityModule(new ActivityModule(getActivity())).
                build().
                inject(this);
    }

}
