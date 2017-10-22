package com.developer.sixfingers.jeenchtest.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.sixfingers.jeenchtest.R;
import com.developer.sixfingers.jeenchtest.UserActivity;
import com.developer.sixfingers.jeenchtest.models.PostModel;
import com.developer.sixfingers.jeenchtest.models.UserModel;

import java.util.ArrayList;

public class ListPostsAdapter extends  RecyclerView.Adapter<ListPostsAdapter.ViewHolder>  {

    private ArrayList<PostModel> postsList;

    public ListPostsAdapter(ArrayList<PostModel> inPostsList) {
        postsList = inPostsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_list_posts, parent, false);
        return new ListPostsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(postsList.get(position).getTitle());
        holder.tvBody.setText(postsList.get(position).getBody());
        holder.postModel = postsList.get(position);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView tvBody;
        private TextView tvTitle;
        private PostModel postModel;
        private final Context context;
        ViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            tvTitle = v.findViewById(R.id.tv_text_post_title);
            tvBody = v.findViewById(R.id.tv_text_post_body);
        }

    }
}
