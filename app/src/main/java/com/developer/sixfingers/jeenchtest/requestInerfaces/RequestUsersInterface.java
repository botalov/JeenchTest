package com.developer.sixfingers.jeenchtest.requestInerfaces;

import com.developer.sixfingers.jeenchtest.models.UserModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RequestUsersInterface {
    @GET("users/")
    Observable<List<UserModel>> users();
}

