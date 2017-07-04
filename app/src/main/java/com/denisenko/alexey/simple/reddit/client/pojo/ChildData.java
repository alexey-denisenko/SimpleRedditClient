package com.denisenko.alexey.simple.reddit.client.pojo;

import com.squareup.moshi.Json;

public class ChildData {

    @Json(name = "title")
    private String title;

    @Json(name = "author")
    private String author;

    @Json(name = "created_utc")
    private long createdUtc;

    @Json(name = "thumbnail")
    private String thumbnail;

    @Json(name = "num_comments")
    private int numComments;

    @Json(name = "url")
    private String url;

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
}
