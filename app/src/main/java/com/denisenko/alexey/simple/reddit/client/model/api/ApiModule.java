package com.denisenko.alexey.simple.reddit.client.model.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public final class ApiModule {

    public static final boolean ENABLE_LOG = true;

    public ApiModule() {
    }

    public static ApiInterface getApiInterface(String url) {
        OkHttpClient httpClient = new OkHttpClient();

        if (ENABLE_LOG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(loggingInterceptor);
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create());

        builder.client(httpClient);

        return builder.build().create(ApiInterface.class);
    }
}
