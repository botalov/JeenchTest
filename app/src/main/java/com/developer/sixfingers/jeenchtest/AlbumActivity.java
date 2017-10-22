package com.developer.sixfingers.jeenchtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.developer.sixfingers.jeenchtest.adapters.ListAlbumsAdapter;
import com.developer.sixfingers.jeenchtest.adapters.ListPhotosAdapter;
import com.developer.sixfingers.jeenchtest.adapters.ListUsersAdapter;
import com.developer.sixfingers.jeenchtest.helpers.RequestPhotosInterface;
import com.developer.sixfingers.jeenchtest.helpers.RequestUsersInterface;
import com.developer.sixfingers.jeenchtest.models.PhotoModel;
import com.developer.sixfingers.jeenchtest.models.UserModel;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        int albumId = getIntent().getIntExtra("albumId", 0);

        RequestPhotosInterface requestPhotosInterface = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestPhotosInterface.class);

        requestPhotosInterface.photos(albumId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError);

    }

    private void handleResponse(List<PhotoModel> androidList) {

        ArrayList<PhotoModel> listPhotos = new ArrayList<>(androidList);



        ListPhotosAdapter adapter = new ListPhotosAdapter(AlbumActivity.this, listPhotos);
        ListView photosListView =(ListView)findViewById(R.id.lv_photos);
        photosListView.setAdapter(adapter);

        /*photosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
        */


        //usersAdapter = new ListUsersAdapter(listUsers);
        //usersRecyclerView.setAdapter(usersAdapter);
    }

    private void handleError(Throwable error) {
        Toast.makeText(this, "Error ".concat(error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
    }
}
