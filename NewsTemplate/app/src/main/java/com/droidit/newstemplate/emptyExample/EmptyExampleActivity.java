package com.droidit.newstemplate.emptyExample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.droidit.newstemplate.R;

/**
 * Created by JohannesC on 07-Sep-16.
 * Make this activity your launcher and rename to whatever
 */
public class EmptyExampleActivity extends AppCompatActivity {

    public static Intent getActivityIntent(final Context context, final String titleText) {

        Intent intent = new Intent(context, EmptyExampleActivity.class);
        intent.putExtra("AWE", titleText);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        String header = getIntent().getStringExtra("AWE");
        TextView tv = (TextView) findViewById(R.id.news_detail_item_title);
        tv.setText(header);
    }
}
