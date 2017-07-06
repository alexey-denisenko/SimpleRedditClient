package com.denisenko.alexey.simple.reddit.client.top.mappers;

import com.denisenko.alexey.simple.reddit.client.pojo.Child;
import com.denisenko.alexey.simple.reddit.client.pojo.Reddit;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class TopListMapper implements Function<Reddit, List<TopEntry>> {

    @Override
    public List<TopEntry> apply(Reddit reddit) throws Exception {
        if (reddit == null) {
            return null;
        }

        List<Child> childrens = reddit.getData().getChildrens();
        List<TopEntry> result = new ArrayList<>(childrens.size());

        for (Child child : childrens) {
            result.add(new TopEntry(child.getData().getNumComments(),
                    child.getData().getCreatedUtc(),
                    child.getData().getTitle(),
                    child.getData().getAuthor(),
                    child.getData().getThumbnail(),
                    child.getData().getUrl(),
                    child.getData().getName()));
        }
        return result;
    }
}
