package com.denisenko.alexey.simple.reddit.client.entity.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("modhash")
    private String modhash;

    @SerializedName("children")
    private List<Child> child;

    @SerializedName("after")
    private String after;

    @SerializedName("before")
    private String before;

    public String getModhash() {
        return modhash;
    }

    public List<Child> getChildrens() {
        return child;
    }

    public String getAfter() {
        return after;
    }

    public String getBefore() {
        return before;
    }
}
