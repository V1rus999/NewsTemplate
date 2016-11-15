package com.droidit.newstemplate.news_list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.droidit.newstemplate.news_list.list.NewsListDividerItemDecorator;
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

    @BindView(R.id.activity_main_posts_tv)
    TextView activity_main_posts_tv;

    @BindView(R.id.news_list_rv)
    RecyclerView news_list_rv;

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

    @OnClick(R.id.activity_main_launch_connection_btn)
    public void onLaunchConnectionBtnClick() {
        newsListPresenter.onConnectionButtonClicked();
    }

    @OnClick(R.id.activity_main_get_posts_btn)
    public void onGetPostsBtnClick() {
        newsListPresenter.onGetPostsBtnClick();
    }

    @Override
    public void displaySinglePostTitle(String title) {
        activity_main_posts_tv.setTextColor(Color.BLACK);
        activity_main_posts_tv.setText(title);
    }

    @Override
    public void displayConnectionError(String message) {
        activity_main_posts_tv.setTextColor(Color.RED);
        activity_main_posts_tv.setText(message);
    }

    @Override
    public void setupInitialList() {
        newsListItemAdapter = new NewsListItemAdapter();
        newsListItemAdapter.setOnItemClickListener(new NewsListItemAdapter.ClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(NewsListActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        news_list_rv.setLayoutManager(mLayoutManager);
        news_list_rv.addItemDecoration(new NewsListDividerItemDecorator(NewsListActivity.this, LinearLayoutManager.VERTICAL));
        news_list_rv.setItemAnimator(new DefaultItemAnimator());
        news_list_rv.setAdapter(newsListItemAdapter);
    }

    @Override
    public void updateListWithNewData(List<PostDto> postDtos) {
        newsListItemAdapter.setPosts(postDtos);
    }
}
