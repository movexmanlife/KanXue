package com.pediy.kanxue.ui.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.pediy.kanxue.R;
import com.pediy.kanxue.api.ApiConst;
import com.pediy.kanxue.bean.NewTopicBean;
import com.pediy.kanxue.util.ImageLoadUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTopicAdapter extends RecyclerView.Adapter<NewTopicAdapter.NewTopicViewHolder> {
    private Context mContext;
    private List<NewTopicBean.ThreadListEntity> threadListEntityList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @Inject
    public NewTopicAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void bind(List<NewTopicBean.ThreadListEntity> threadListEntityList) {
        this.threadListEntityList = threadListEntityList;
        notifyDataSetChanged();
    }

    @Override
    public NewTopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewTopicViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_newtopic, parent, false));
    }

    @Override
    public void onBindViewHolder(NewTopicViewHolder holder, int position) {
        NewTopicBean.ThreadListEntity entity = threadListEntityList.get(position);

        /**
         * 有时候会多加一个数据，很可能是由于LoadMoreRecyclerView引起来的。
         */
        if (entity == null) {
            return;
        }
        holder.threadListEntity = entity;
        holder.forumDisplayTitle.setText(entity.getThreadtitle());
        holder.postUserName.setText(entity.getPostusername());
        holder.viewsCnt.setText(String.valueOf(entity.getViews()));
        holder.replyCnt.setText(String.valueOf(entity.getReplycount()));

        if (entity.getAvatar() == 1) {
            ImageLoadUtils.load(mContext, ApiConst.getUserAvatarUrl(entity.getPostuserid()), holder.headImg);
        } else {
            ImageLoadUtils.load(mContext, R.drawable.default_user_head_img, holder.headImg);
        }

        if (entity.getOpen() == 0) {
            holder.forumDisplayLock.setVisibility(View.VISIBLE);
        } else {
            holder.forumDisplayLock.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return threadListEntityList.size();
    }

    class NewTopicViewHolder extends RecyclerView.ViewHolder {
        NewTopicBean.ThreadListEntity threadListEntity;

        @BindView(R.id.headImg)
        ImageView headImg;
        @BindView(R.id.forumDisplayTitle)
        TextView forumDisplayTitle;
        @BindView(R.id.forumDisplayLock)
        ImageView forumDisplayLock;
        @BindView(R.id.postUserName)
        TextView postUserName;
        @BindView(R.id.viewsCnt)
        TextView viewsCnt;
        @BindView(R.id.replyCnt)
        TextView replyCnt;

        public NewTopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onNewTopicClick(threadListEntity);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onNewTopicClick(NewTopicBean.ThreadListEntity threadListEntity);
    }
}
