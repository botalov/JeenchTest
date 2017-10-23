package com.developer.sixfingers.jeenchtest.requestInerfaces;

import com.developer.sixfingers.jeenchtest.models.PostModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RequestAddPostsInterface{
    @POST("posts/")
    @FormUrlEncoded
    Observable<PostModel> addPost(@Field("userId") int userId,
                                  @Field("title") String title,
                                  @Field("body") String body);
}
