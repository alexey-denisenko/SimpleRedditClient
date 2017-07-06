package com.denisenko.alexey.simple.reddit.client.pojo;

import com.google.gson.annotations.SerializedName;

public class ChildData {

    @SerializedName("title")
    private String title;

    @SerializedName("author")
    private String author;

    @SerializedName("created_utc")
    private long createdUtc;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("num_comments")
    private int numComments;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getCreatedUtc() {
        return createdUtc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getNumComments() {
        return numComments;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
