package com.developer.sixfingers.jeenchtest.helpers;

import com.developer.sixfingers.jeenchtest.models.AlbumModel;
import com.developer.sixfingers.jeenchtest.models.UserModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestAlbumsInterface{
    @GET("albums/")
    Observable<List<AlbumModel>> albums(@Query("userId") int userId);
}

