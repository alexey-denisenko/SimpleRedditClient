package com.denisenko.alexey.simple.reddit.client.top;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository {

    private List<TopEntry> entries = new ArrayList<>();

    private String lastEntryName;

    public InMemoryRepository() {
    }

    public List<TopEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<TopEntry> entries) {
        this.entries = entries;
    }

    public void addEntries(List<TopEntry> entries) {
        this.entries.addAll(entries);
    }

    public String getLastEntryName() {
        return lastEntryName;
    }

    public void setLastEntryName(String lastEntryName) {
        this.lastEntryName = lastEntryName;
    }
}
