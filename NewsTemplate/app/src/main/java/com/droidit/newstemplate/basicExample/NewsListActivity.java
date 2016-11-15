package com.droidit.newstemplate.basicExample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.droidit.domain.basicExample.NewsListContract;
import com.droidit.domain.basicExample.NewsListPresenter;
import com.droidit.newstemplate.DefaultApplication;
import com.droidit.newstemplate.R;
import com.droidit.newstemplate.dependencyInjection.ApplicationComponent;
import com.droidit.newstemplate.dependencyInjection.DaggerNewsListComponent;
import com.droidit.newstemplate.dependencyInjection.NetworkModule;
import com.droidit.newstemplate.dependencyInjection.NewsListComponent;
import com.droidit.newstemplate.dependencyInjection.WireframeModule;

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

    @Inject
    NewsListPresenter newsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.initializeInjector();
        newsListPresenter.onCreate(this);
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
}
