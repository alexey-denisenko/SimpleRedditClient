package com.denisenko.alexey.simple.reddit.client.entity.gson;

import com.google.gson.annotations.SerializedName;

public class Reddit {

    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private Data data;

    public String getKind() {
        return kind;
    }

    public Data getData() {
        return data;
    }
}
