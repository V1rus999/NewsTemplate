package com.droidit.newstemplate.dependencyInjection;

import android.app.Activity;

import com.droidit.domain.basicExample.NewsListContract;
import com.droidit.newstemplate.basicExample.BasicExampleWireframe;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JohannesC on 07-Sep-16.
 */
@Module
public class WireframeModule {

    private final Activity activity;

    public WireframeModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return activity;
    }

    @Provides
    public NewsListContract.WireFrame provideBasicExampleWireframe(BasicExampleWireframe basicExampleWireframe) {
        return basicExampleWireframe;
    }
}
