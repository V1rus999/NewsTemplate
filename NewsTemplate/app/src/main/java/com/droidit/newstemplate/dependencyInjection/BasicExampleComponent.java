package com.droidit.newstemplate.dependencyInjection;

import com.droidit.newstemplate.basicExample.BasicExampleActivity;

import dagger.Component;

/**
 * Created by JohannesC on 05-Sep-16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {NetworkModule.class, WireframeModule.class, PresenterModule.class})
public interface BasicExampleComponent {

    void inject(BasicExampleActivity basicExampleActivity);

}
