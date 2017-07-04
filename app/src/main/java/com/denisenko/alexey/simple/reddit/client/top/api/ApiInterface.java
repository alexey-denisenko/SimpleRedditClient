package com.denisenko.alexey.simple.reddit.client.top.api;


import com.denisenko.alexey.simple.reddit.client.pojo.RedditResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/top.json")
    Observable<RedditResponse> getTopEntries(@Query("limit") int limit, @Query("after") String after);
}
