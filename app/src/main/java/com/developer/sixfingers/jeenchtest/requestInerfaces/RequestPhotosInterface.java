package com.developer.sixfingers.jeenchtest.requestInerfaces;

import com.developer.sixfingers.jeenchtest.models.PhotoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestPhotosInterface {
    @GET("photos/")
    Observable<List<PhotoModel>> photos(@Query("albumId") int albumId);
}
