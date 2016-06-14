package com.pediy.kanxue.ui.topic;

import android.support.v7.widget.RecyclerView;

import com.pediy.kanxue.BaseFragment;
import com.pediy.kanxue.R;
import com.pediy.kanxue.api.ApiConst;
import com.pediy.kanxue.api.thread.ThreadApi;
import com.pediy.kanxue.bean.HomepageBean;
import com.pediy.kanxue.bean.NewTopicBean;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;


public class NewTopicFragment extends BaseFragment {
    @BindView(R.id.ptr_classic_frame_layout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private Subscription mSubscription;
    @Inject
    ThreadApi mThreadApi;

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
        mSubscription = mThreadApi.getForumDisplayPage(ApiConst.NEW_FORUM_ID, 1)
                .map(new Func1<NewTopicBean, List<NewTopicBean.ThreadListEntity>>() {
                    @Override
                    public List<NewTopicBean.ThreadListEntity> call(NewTopicBean topicBean) {
                        if (topicBean == null || topicBean.getThreadList() == null) {
                            return null;
                        }
                        return topicBean.getThreadList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewTopicBean.ThreadListEntity>>() {
                    @Override
                    public void call(List<NewTopicBean.ThreadListEntity> subEntityList) {
                        ptrClassicFrameLayout.refreshComplete();
                        if (subEntityList != null && !subEntityList.isEmpty()) {
//                            adapter.clear();
//                            adapter.addAll(subEntityList);
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
    public void setListener() {

    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {

    }
}
