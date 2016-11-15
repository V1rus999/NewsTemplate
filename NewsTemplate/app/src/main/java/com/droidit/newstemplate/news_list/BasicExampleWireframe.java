package com.droidit.newstemplate.news_list;

import android.app.Activity;

import com.droidit.domain.basicExample.NewsListContract;
import com.droidit.newstemplate.emptyExample.EmptyExampleActivity;

import javax.inject.Inject;

/**
 * Created by JohannesC on 07-Sep-16.
 * All navigation logic goes in here
 */
public class BasicExampleWireframe implements NewsListContract.WireFrame {

    private final Activity context;

    @Inject
    public BasicExampleWireframe(Activity context) {
        this.context = context;
    }

    @Override
    public void onConnectionButtonClicked() {
        context.startActivity(EmptyExampleActivity.getActivityIntent(context));
    }
}
