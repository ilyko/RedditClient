package com.slava.reddittop.api;

import com.slava.reddittop.model.top.RedditTop;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * interface for Retrofit.
 */
public interface RedditApi {
    @GET("/top.json")
    Observable<RedditTop> getRedditTop(@Query("after") String after,
                                       @Query("limit") int limit);
}
