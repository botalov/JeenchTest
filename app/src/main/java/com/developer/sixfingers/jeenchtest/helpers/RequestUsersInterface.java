package com.developer.sixfingers.jeenchtest.helpers;


import com.developer.sixfingers.jeenchtest.models.PhotoModel;
import com.developer.sixfingers.jeenchtest.models.UserModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestUsersInterface {
    @GET("users/")
    Observable<List<UserModel>> users();
}

