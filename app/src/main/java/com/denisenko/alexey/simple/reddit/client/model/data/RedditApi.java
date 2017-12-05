package com.denisenko.alexey.simple.reddit.client.model.data;


import com.denisenko.alexey.simple.reddit.client.entity.gson.Reddit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditApi {

    @GET("/top.json")
    Observable<Reddit> getTopEntries(@Query("limit") int limit, @Query("after") String after);
}
