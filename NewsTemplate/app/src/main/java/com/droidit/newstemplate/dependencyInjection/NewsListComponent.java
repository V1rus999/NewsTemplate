package com.droidit.newstemplate.dependencyInjection;

import com.droidit.newstemplate.news_list.NewsListActivity;

import dagger.Component;

/**
 * Created by JohannesC on 05-Sep-16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {NetworkModule.class, WireframeModule.class})
public interface NewsListComponent {

    void inject(NewsListActivity newsListActivity);

}
