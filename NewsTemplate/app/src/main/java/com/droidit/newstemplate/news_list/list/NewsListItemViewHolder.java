package com.droidit.newstemplate.news_list.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.droidit.newstemplate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by johannesC on 2016/11/15.
 */

public class NewsListItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.news_list_item_title)
    TextView news_list_item_title;

    @BindView(R.id.news_list_item_body)
    TextView news_list_item_body;

    public NewsListItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setTitle(final String title) {
        news_list_item_title.setText(title);
    }

    public void setBody(final String body) {
        news_list_item_body.setText(body);
    }
}
