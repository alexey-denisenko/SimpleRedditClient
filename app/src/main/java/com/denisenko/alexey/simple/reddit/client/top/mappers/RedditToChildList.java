package com.denisenko.alexey.simple.reddit.client.top.mappers;

import com.denisenko.alexey.simple.reddit.client.pojo.Child;
import com.denisenko.alexey.simple.reddit.client.pojo.Reddit;

import java.util.List;

import io.reactivex.functions.Function;

public class RedditToChildList implements Function<Reddit, List<Child>> {
    @Override
    public List<Child> apply(Reddit reddit) {
        if (reddit == null) {
            return null;
        }
        return reddit.getData().getChildrens();
    }
}
