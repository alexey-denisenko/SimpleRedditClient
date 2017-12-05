package com.denisenko.alexey.simple.reddit.client;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;

import com.denisenko.alexey.simple.reddit.client.top_list.TopEntry;
import com.denisenko.alexey.simple.reddit.client.top_list.TopListActivityCallback;

public class MainActivity extends AppCompatActivity implements TopListActivityCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void startWebViewActivity(TopEntry entry) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(entry.getUrl()));
    }
}
