package com.denisenko.alexey.simple.reddit.client.top;

public class TopEntry {

    private int numberOfComments;
    private long createdAt;
    private String title;
    private String author;
    private String thumbnailUrl;
    private String url;

    public TopEntry() {
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }
}
