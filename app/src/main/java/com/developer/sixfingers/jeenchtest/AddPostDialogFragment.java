package com.developer.sixfingers.jeenchtest;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.sixfingers.jeenchtest.models.PostModel;
import com.developer.sixfingers.jeenchtest.requestInerfaces.RequestAddPostsInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPostDialogFragment extends DialogFragment {

    private CompositeDisposable compositeDisposable;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.add_post_dialog_fragment, null);

        compositeDisposable = new CompositeDisposable();

        Button btnOk = v.findViewById(R.id.btn_addPostOk);
        btnOk.setOnClickListener(view -> {
            String body = ((EditText)v.findViewById(R.id.et_addPostBody)).getText().toString();
            String title = ((EditText)v.findViewById(R.id.et_addPostTitle)).getText().toString();

            RequestAddPostsInterface requestAddPostsInterface = new Retrofit.Builder()
                    .baseUrl(getString(R.string.base_url))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RequestAddPostsInterface.class);

            compositeDisposable.add(requestAddPostsInterface.addPost(GlobalData.getInstance().userId, title, body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse,this::handleError));
        });

        Button btnCancel = v.findViewById(R.id.btn_addPostCancel);
        btnCancel.setOnClickListener(view -> {
            this.dismiss();
        });

        return v;
    }

    private void handleResponse(PostModel postModel) {
        Toast.makeText(this.getContext(), "Successful!", Toast.LENGTH_SHORT).show();
        this.dismiss();
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
