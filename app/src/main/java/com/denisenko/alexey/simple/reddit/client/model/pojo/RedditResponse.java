package com.denisenko.alexey.simple.reddit.client.model.pojo;

import com.squareup.moshi.Json;

public class RedditResponse {

    @Json(name = "kind")
    String kind;

    @Json(name = "data")
    RedditListingData data;

    public RedditResponse() {
    }

    public String getKind() {
        return kind;
    }

    public RedditListingData getData() {
        return data;
    }
}
