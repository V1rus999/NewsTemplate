package com.droidit.newstemplate.news_list;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.droidit.domain.basicExample.NewsListContract;
import com.droidit.domain.basicExample.NewsListPresenter;
import com.droidit.domain.posts.PostDto;
import com.droidit.newstemplate.DefaultApplication;
import com.droidit.newstemplate.R;
import com.droidit.newstemplate.dependencyInjection.ApplicationComponent;
import com.droidit.newstemplate.dependencyInjection.DaggerNewsListComponent;
import com.droidit.newstemplate.dependencyInjection.NetworkModule;
import com.droidit.newstemplate.dependencyInjection.NewsListComponent;
import com.droidit.newstemplate.dependencyInjection.WireframeModule;
import com.droidit.newstemplate.emptyExample.EmptyExampleActivity;
import com.droidit.newstemplate.news_list.list.NewsListItemAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JohannesC on 30-May-16.
 * Basic activity which shows MVP, navigation, and some networking logic
 */
public class NewsListActivity extends AppCompatActivity implements NewsListContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_main_posts_tv)
    TextView activity_main_posts_tv;

    @BindView(R.id.news_list_rv)
    RecyclerView news_list_rv;

    @BindView(R.id.news_list_swipe_refresh)
    SwipeRefreshLayout news_list_swipe_refresh;

    @Inject
    NewsListPresenter newsListPresenter;

    private NewsListItemAdapter newsListItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.initializeInjector();
        newsListPresenter.onViewAttached(this);
        newsListPresenter.onCreate();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((DefaultApplication) this.getApplication()).getMainComponent();
    }

    private void initializeInjector() {
        NewsListComponent newsListComponent = DaggerNewsListComponent.builder()
                .applicationComponent(getApplicationComponent())
                .networkModule(new NetworkModule("http://jsonplaceholder.typicode.com/"))
                .wireframeModule(new WireframeModule(this))
                .build();
        newsListComponent.inject(this);
    }

    @Override
    public void displayConnectionError(String message) {
        activity_main_posts_tv.setTextColor(Color.RED);
        activity_main_posts_tv.setText(message);
    }

    @Override
    public void setupSwipeToRefresh() {
        news_list_swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsListPresenter.onSwipeToRefresh();
            }
        });
    }

    @Override
    public void displayBusyIndicator() {
        news_list_swipe_refresh.post(new Runnable() {
            @Override
            public void run() {
                news_list_swipe_refresh.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideBusyIndicator() {
        news_list_swipe_refresh.setRefreshing(false);
    }

    @Override
    public void setupInitialList() {
        toolbar.setTitle("AWE");
        newsListItemAdapter = new NewsListItemAdapter();
        newsListItemAdapter.setOnItemClickListener(new NewsListItemAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = EmptyExampleActivity.getActivityIntent(NewsListActivity.this, ((TextView) v).getText().toString());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(NewsListActivity.this, v, "profile");
                ActivityCompat.startActivity(NewsListActivity.this, intent, options.toBundle());
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        news_list_rv.setLayoutManager(mLayoutManager);
        news_list_rv.setItemAnimator(new DefaultItemAnimator());
        news_list_rv.setAdapter(newsListItemAdapter);
    }

    @Override
    public void updateListWithNewData(List<PostDto> postDtos) {
        newsListItemAdapter.setPosts(postDtos);
    }
}
