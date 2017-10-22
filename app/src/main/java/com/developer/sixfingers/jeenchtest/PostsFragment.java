package com.developer.sixfingers.jeenchtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developer.sixfingers.jeenchtest.adapters.ListPostsAdapter;
import com.developer.sixfingers.jeenchtest.helpers.RequestPostsInterface;
import com.developer.sixfingers.jeenchtest.models.PostModel;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostsFragment extends Fragment {

    private int userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO:
        userId = GlobalData.getInstance().userId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_posts, container, false);

        FloatingActionButton myFab = (FloatingActionButton) v.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(), ListUsersActivity.class);
                AddPostDialogFragment addPostDialog = new AddPostDialogFragment();
                addPostDialog.show(getFragmentManager(), "addPostDialog");
            }
        });

        loadPosts();
        return v;
    }

    private void loadPosts(){
        RequestPostsInterface requestPostsInterface = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestPostsInterface.class);

        requestPostsInterface.posts(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlePostsResponse,this::handleError);
    }

    private void handlePostsResponse(List<PostModel> androidList) {
        RecyclerView postsRecyclerView = ((Activity) this.getContext()).findViewById(R.id.rv_posts);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ArrayList<PostModel> listPosts = new ArrayList<>(androidList);
        ListPostsAdapter postsAdapter = new ListPostsAdapter(listPosts);
        postsRecyclerView.setAdapter(postsAdapter);

    }

    private void handleError(Throwable error) {
        Toast.makeText(this.getContext(), "Error ".concat(error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
    }



}
