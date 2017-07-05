package com.denisenko.alexey.simple.reddit.client.pojo;

import com.google.gson.annotations.SerializedName;

public class Child {

    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private ChildData data;

    public String getKind() {
        return kind;
    }

    public ChildData getData() {
        return data;
    }
}
