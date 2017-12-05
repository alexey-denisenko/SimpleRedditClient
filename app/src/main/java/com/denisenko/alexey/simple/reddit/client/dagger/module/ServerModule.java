package com.denisenko.alexey.simple.reddit.client.dagger.module;

import com.denisenko.alexey.simple.reddit.client.BuildConfig;
import com.denisenko.alexey.simple.reddit.client.model.data.RedditApi;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.denisenko.alexey.simple.reddit.client.dagger.NamingConstants.REDDIT_ENDPOINT;

@Module
public class ServerModule {

    @Provides
    @Singleton
    RedditApi provideRedditApi(OkHttpClient client,
                               Gson gson,
                               @Named(REDDIT_ENDPOINT) String serverPath) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverPath)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(RedditApi.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttp() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }

        return httpClientBuilder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }


    @Provides
    @Singleton
    @Named(REDDIT_ENDPOINT)
    String provideRedditEndpoint() {
        return BuildConfig.REDDIT_ENDPOINT;
    }
}
