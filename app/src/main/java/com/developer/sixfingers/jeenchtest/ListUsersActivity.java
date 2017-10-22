package com.developer.sixfingers.jeenchtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.developer.sixfingers.jeenchtest.adapters.ListUsersAdapter;
import com.developer.sixfingers.jeenchtest.helpers.PermissionsHelper;
import com.developer.sixfingers.jeenchtest.helpers.RequestUsersInterface;
import com.developer.sixfingers.jeenchtest.models.UserModel;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListUsersActivity extends AppCompatActivity {

    private RecyclerView usersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        usersRecyclerView = (RecyclerView)findViewById(R.id.rv_users);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        PermissionsHelper.verifyPermissions(this);

        RequestUsersInterface requestUsersInterface = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestUsersInterface.class);

        requestUsersInterface.users()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError);

    }

    private void handleResponse(List<UserModel> androidList) {

        ArrayList<UserModel> listUsers = new ArrayList<>(androidList);
        ListUsersAdapter usersAdapter = new ListUsersAdapter(listUsers);
        usersRecyclerView.setAdapter(usersAdapter);
    }

    private void handleError(Throwable error) {
        Toast.makeText(this, "Error ".concat(error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
    }
}
