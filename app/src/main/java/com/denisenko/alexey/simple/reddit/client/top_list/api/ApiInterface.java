package com.denisenko.alexey.simple.reddit.client.top_list.api;


import com.denisenko.alexey.simple.reddit.client.pojo.Reddit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/top.json")
    Observable<Reddit> getTopEntries(@Query("limit") int limit, @Query("after") String after);
}
