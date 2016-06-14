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
import com.pediy.kanxue.bean.TopicBean;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomePageFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

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

    }

    @Override
    public void initView() {
        // Set adapter populated with example dummy data
        final HomePageAdapter adapter = new HomePageAdapter();

        List<TopicBean.ForumbitsEntity.ForumSubTitleEntity> list = getMockData();
        adapter.addAll(list);
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

    private List<TopicBean.ForumbitsEntity.ForumSubTitleEntity> getMockData() {
        List<TopicBean.ForumbitsEntity.ForumSubTitleEntity> list = new ArrayList<>();
        TopicBean.ForumbitsEntity.ForumSubTitleEntity e1= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e1.setName("初学者园地");
        e1.setCategroyName("求助问答");
        list.add(e1);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e2= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e2.setName("Andoid");
        e2.setCategroyName("求助问答");
        list.add(e2);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e3= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e3.setName("Watch");
        e3.setCategroyName("求助问答");
        list.add(e3);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e4= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e4.setName("ios");
        e4.setCategroyName("求助问答");
        list.add(e4);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e5= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e5.setName("coding");
        e5.setCategroyName("求助问答");
        list.add(e5);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e6= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e6.setName("github");
        e6.setCategroyName("求助问答");
        list.add(e6);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e11= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e11.setName("MFC");
        e11.setCategroyName("windows");
        list.add(e11);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e12= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e12.setName("WPS");
        e12.setCategroyName("windows");
        list.add(e12);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e13= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e13.setName("OFFICE");
        e13.setCategroyName("windows");
        list.add(e13);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e14= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e14.setName("PROJECT");
        e14.setCategroyName("windows");
        list.add(e14);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e15= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e15.setName("SYSTEM");
        e15.setCategroyName("windows");
        list.add(e15);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e16= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e16.setName("MSN");
        e16.setCategroyName("windows");
        list.add(e16);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e111= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e111.setName("MFC");
        e111.setCategroyName("安全");
        list.add(e111);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e112= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e112.setName("WPS");
        e112.setCategroyName("安全");
        list.add(e112);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e113= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e113.setName("OFFICE");
        e113.setCategroyName("安全");
        list.add(e113);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e114= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e114.setName("PROJECT");
        e114.setCategroyName("安全");
        list.add(e114);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e115= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e115.setName("SYSTEM");
        e115.setCategroyName("安全");
        list.add(e115);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e116= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e116.setName("MSN");
        e116.setCategroyName("安全");
        list.add(e116);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e117= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e117.setName("MSN");
        e117.setCategroyName("安全");
        list.add(e117);

        TopicBean.ForumbitsEntity.ForumSubTitleEntity e118= new TopicBean.ForumbitsEntity.ForumSubTitleEntity();
        e118.setName("MSN");
        e118.setCategroyName("安全");
        list.add(e118);

        return list;
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
