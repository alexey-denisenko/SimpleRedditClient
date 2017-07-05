package com.denisenko.alexey.simple.reddit.client.top.mappers;

import com.denisenko.alexey.simple.reddit.client.pojo.Child;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class TopListMapper implements Function<List<Child>, List<TopEntry>> {

    @Override
    public List<TopEntry> apply(List<Child> children) throws Exception {
        if (children == null) {
            return null;
        }

        return Observable.fromIterable(children)
                .map(child -> new TopEntry(child.getData().getNumComments(),
                        child.getData().getCreatedUtc(),
                        child.getData().getTitle(),
                        child.getData().getAuthor(),
                        child.getData().getThumbnail(),
                        child.getData().getUrl()))
                .toList()
                .blockingGet();
    }
}
