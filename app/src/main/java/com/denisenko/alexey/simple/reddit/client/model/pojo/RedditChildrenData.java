package com.denisenko.alexey.simple.reddit.client.model.pojo;

import com.squareup.moshi.Json;

public class RedditChildrenData {

    @Json(name = "title")
    String title;

    @Json(name = "author")
    String author;

    @Json(name = "created_utc")
    long createdAt;

    @Json(name = "thumbnail")
    String thumbnailUrl;

    @Json(name = "num_comments")
    int numberOfComments;

    @Json(name = "url")
    String url;

    public RedditChildrenData() {
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public String getUrl() {
        return url;
    }
}
