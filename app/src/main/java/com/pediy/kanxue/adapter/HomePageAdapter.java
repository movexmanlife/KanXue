package com.pediy.kanxue.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pediy.kanxue.R;
import com.pediy.kanxue.bean.TopicBean;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageAdapter extends RecyclerArrayAdapter<TopicBean.ForumbitsEntity.ForumSubTitleEntity, RecyclerView.ViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_homepage, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String name = getItem(position).getName();
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.detailName.setText(name);
    }

    /**
     * hashCode为负的，会导致显示header显示不了
     */
    @Override
    public long getHeaderId(int position) {
        return getItem(position).getCategroy();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_header, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        String categoryName = getItem(position).getCategroyName();
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.categoryName.setText(categoryName);
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        View root;
        @BindView(R.id.tv_categoryName)
        TextView categoryName;

        HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.root = view;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        View root;
        @BindView(R.id.tv_detailName)
        TextView detailName;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.root = view;
        }
    }
}