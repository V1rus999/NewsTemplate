package com.droidit.newstemplate.news_list;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;

import com.droidit.domain.basicExample.NewsListContract;
import com.droidit.newstemplate.emptyExample.EmptyExampleActivity;

import javax.inject.Inject;

/**
 * Created by JohannesC on 07-Sep-16.
 * All navigation logic goes in here
 */
public class NewsListWireframe implements NewsListContract.WireFrame {

    private final Activity context;

    @Inject
    public NewsListWireframe(Activity context) {
        this.context = context;
    }

    @Override
    public void onListItemSelected() {

    }
}
