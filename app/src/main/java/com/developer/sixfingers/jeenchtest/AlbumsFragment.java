package com.developer.sixfingers.jeenchtest;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developer.sixfingers.jeenchtest.adapters.ListAlbumsAdapter;
import com.developer.sixfingers.jeenchtest.requestInerfaces.RequestAlbumsInterface;
import com.developer.sixfingers.jeenchtest.models.AlbumModel;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AlbumsFragment extends Fragment {

    private CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_albums, container, false);

        loadAlbums();

        return v;
    }




    private void loadAlbums(){
        RequestAlbumsInterface requestAlbumsInterface = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestAlbumsInterface.class);

        compositeDisposable.add(requestAlbumsInterface.albums(GlobalData.getInstance().userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleAlbumsResponse,this::handleError));
    }

    private void handleAlbumsResponse(List<AlbumModel> androidList) {
        RecyclerView albumsRecyclerView = ((Activity) this.getContext()).findViewById(R.id.rv_albums);
        albumsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ArrayList<AlbumModel> listAlbums = new ArrayList<>(androidList);
        ListAlbumsAdapter albumsAdapter = new ListAlbumsAdapter(listAlbums);
        albumsRecyclerView.setAdapter(albumsAdapter);

    }

    private void handleError(Throwable error) {
        Toast.makeText(this.getContext(), "Error ".concat(error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
