package com.developer.sixfingers.jeenchtest.helpers;

import com.developer.sixfingers.jeenchtest.models.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestPostsInterface{
    @GET("posts/")
    Observable<List<PostModel>> posts(@Query("userId") int userId);
}
