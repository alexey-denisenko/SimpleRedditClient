package com.denisenko.alexey.simple.reddit.client.model.pojo;

import com.squareup.moshi.Json;

public class RedditListingData {

    @Json(name = "children")
    RedditChildren children;

    @Json(name = "after")
    String after;

    @Json(name = "before")
    String before;

    public RedditListingData() {
    }

    public RedditChildren getChildren() {
        return children;
    }

    public String getAfter() {
        return after;
    }

    public String getBefore() {
        return before;
    }
}
