package com.denisenko.alexey.simple.reddit.client.model.api;

import com.denisenko.alexey.simple.reddit.client.model.RedditResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/top.json")
    Call<RedditResponse> getTopEntries(@Query("limit") int limit, @Query("after") String after);
}
