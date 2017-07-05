package com.denisenko.alexey.simple.reddit.client.top;

public class TopEntry {

    private int numberOfComments;
    private long createdAt;
    private String title;
    private String author;
    private String thumbnailUrl;
    private String url;

    public TopEntry(int numberOfComments,
                    long createdAt,
                    String title,
                    String author,
                    String thumbnailUrl,
                    String url) {
        this.numberOfComments = numberOfComments;
        this.createdAt = createdAt;
        this.title = title;
        this.author = author;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
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
