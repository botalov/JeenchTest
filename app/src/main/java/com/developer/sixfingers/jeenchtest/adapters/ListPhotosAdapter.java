package com.developer.sixfingers.jeenchtest.adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.sixfingers.jeenchtest.R;
import com.developer.sixfingers.jeenchtest.models.PhotoModel;

import java.util.ArrayList;
import java.util.List;

public class ListPhotosAdapter extends BaseAdapter {

    private ArrayList<PhotoModel> photosList;
    private Activity context;



    public ListPhotosAdapter(@NonNull Context context, ArrayList<PhotoModel> photosList) {
        //super(context, R.layout.view_holder_list_photos);

        this.context = (Activity)context;
        this.photosList = photosList;
    }

    @Override
    public int getCount() {
        return photosList.size();
    }

    @Override
    public Object getItem(int position) {
        return photosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.view_holder_list_photos, null, true);

        TextView titleTextView = rowView.findViewById(R.id.tv_photo_title);
        titleTextView.setText(photosList.get(position).getTitle());

        ImageView imageView = rowView.findViewById(R.id.iv_photo);
        Glide
                .with(context)
                .load(photosList.get(position).getUrl())
                .into(imageView);

        return rowView;
    }
}
