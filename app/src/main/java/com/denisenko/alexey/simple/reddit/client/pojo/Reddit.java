package com.denisenko.alexey.simple.reddit.client.pojo;

import com.squareup.moshi.Json;

public class Reddit {

    @Json(name = "kind")
    private String kind;

    @Json(name = "data")
    private Data data;

    public String getKind() {
        return kind;
    }

    public Data getData() {
        return data;
    }
}
