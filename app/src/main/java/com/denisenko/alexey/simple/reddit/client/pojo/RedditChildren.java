package com.denisenko.alexey.simple.reddit.client.pojo;

import com.squareup.moshi.Json;

public class RedditChildren {

    @Json(name = "kind")
    String kind;

    @Json(name = "data")
    RedditChildrenData redditChildrenData;

    public RedditChildren() {
    }

    public String getKind() {
        return kind;
    }

    public RedditChildrenData getRedditChildrenData() {
        return redditChildrenData;
    }
}
