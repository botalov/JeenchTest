package com.developer.sixfingers.jeenchtest.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.sixfingers.jeenchtest.AlbumActivity;
import com.developer.sixfingers.jeenchtest.R;
import com.developer.sixfingers.jeenchtest.UserActivity;
import com.developer.sixfingers.jeenchtest.models.AlbumModel;

import java.util.ArrayList;

public class ListAlbumsAdapter extends  RecyclerView.Adapter<ListAlbumsAdapter.ViewHolder>  {

    private ArrayList<AlbumModel> albumsList;

    public ListAlbumsAdapter(ArrayList<AlbumModel> inAlbumsList) {
        albumsList = inAlbumsList;
    }

    @Override
    public ListAlbumsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_list_albums, parent, false);
        return new ListAlbumsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAlbumsAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(albumsList.get(position).getTitle());
        holder.albumModel = albumsList.get(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return albumsList.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView mTextView;
        private AlbumModel albumModel;
        private final Context context;
        ViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            mTextView = v.findViewById(R.id.tv_album_title);

            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AlbumActivity.class);
                    intent.putExtra("albumId", albumModel.getId());
                    v.getContext().startActivity(intent);
                }
            });
        }

    }
}
