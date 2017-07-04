package com.denisenko.alexey.simple.reddit.client.pojo;

import com.squareup.moshi.Json;

public class Data {

    @Json(name = "modhash")
    private String modhash;

    @Json(name = "child")
    private Child child;

    @Json(name = "after")
    private String after;

    @Json(name = "before")
    private String before;

    public String getModhash() {
        return modhash;
    }

    public Child getChild() {
        return child;
    }

    public String getAfter() {
        return after;
    }

    public String getBefore() {
        return before;
    }
}
