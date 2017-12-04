package com.slava.reddittop.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {

    /**
     * creates retrofit object for reddit.com;
     * @return implementation of the RedditApi interface
     */
    public static RedditApi factory() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RedditApi.class);
    }

}
