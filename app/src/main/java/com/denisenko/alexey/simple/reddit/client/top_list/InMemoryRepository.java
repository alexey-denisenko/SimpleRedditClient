package com.denisenko.alexey.simple.reddit.client.top_list;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository {

    private List<TopEntry> entries = new ArrayList<>();

    public InMemoryRepository() {}

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

        if (entries.size() > 0) {
            return entries.get(entries.size() - 1).getName();
        }
        return "";
    }

    public void clear() {
        entries.clear();
    }
}
