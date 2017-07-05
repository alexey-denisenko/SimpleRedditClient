package com.denisenko.alexey.simple.reddit.client.top.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiModule {

    private static final boolean ENABLE_LOG = true;

    public static ApiInterface getApiInterface(String url) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder();

        if (ENABLE_LOG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        builder.client(okHttpClientBuilder.build());

        return builder.build().create(ApiInterface.class);
    }
}
