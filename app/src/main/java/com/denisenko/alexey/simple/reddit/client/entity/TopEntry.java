package com.denisenko.alexey.simple.reddit.client.entity;

public class TopEntry {

    private final int numberOfComments;
    private final long createdAt;
    private final String title;
    private final String author;
    private final String thumbnailUrl;
    private final String url;
    private final String name;

    public TopEntry(int numberOfComments,
                    long createdAt,
                    String title,
                    String author,
                    String thumbnailUrl,
                    String url, String name) {
        this.numberOfComments = numberOfComments;
        this.createdAt = createdAt;
        this.title = title;
        this.author = author;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
