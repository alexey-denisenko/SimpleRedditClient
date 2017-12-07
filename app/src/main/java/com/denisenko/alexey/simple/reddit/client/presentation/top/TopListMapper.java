package com.denisenko.alexey.simple.reddit.client.presentation.top;

import com.denisenko.alexey.simple.reddit.client.entity.TopEntry;
import com.denisenko.alexey.simple.reddit.client.entity.gson.Child;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.functions.Function;

@Singleton
public class TopListMapper implements Function<List<Child>, List<TopEntry>> {

    @Override
    public List<TopEntry> apply(List<Child> childrens) throws Exception {
        if (childrens == null) {
            return null;
        }

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
