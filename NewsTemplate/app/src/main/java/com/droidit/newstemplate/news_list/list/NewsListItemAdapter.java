package com.droidit.newstemplate.news_list.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droidit.domain.posts.PostDto;
import com.droidit.newstemplate.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johannesC on 2016/11/15.
 */

public class NewsListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<PostDto> posts = new ArrayList<>();
    private ClickListener clickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_row_item, parent, false);

        return new NewsListItemViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsListItemViewHolder newsListItemViewHolder = (NewsListItemViewHolder) holder;
        newsListItemViewHolder.setTitle(posts.get(position).title);
        newsListItemViewHolder.setBody(posts.get(position).body);
    }

    public void setPosts(final List<PostDto> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return posts != null ? posts.size() : 0;
    }

    @Override
    public void onClick(View v) {

    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position);
    }
}
