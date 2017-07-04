package com.denisenko.alexey.simple.reddit.client.pojo;

import com.squareup.moshi.Json;

public class Child {

    @Json(name = "kind")
    private String kind;

    @Json(name = "data")
    private ChildData data;

    public String getKind() {
        return kind;
    }

    public ChildData getData() {
        return data;
    }
}
