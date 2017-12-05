package com.denisenko.alexey.simple.reddit.client.ui;

import com.denisenko.alexey.simple.reddit.client.entity.TopEntry;

public interface TopListActivityCallback {

    void startWebViewActivity(TopEntry entry);
}
