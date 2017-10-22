package com.developer.sixfingers.jeenchtest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.sixfingers.jeenchtest.R;
import com.developer.sixfingers.jeenchtest.UserActivity;
import com.developer.sixfingers.jeenchtest.models.UserModel;

import java.util.ArrayList;


public class ListUsersAdapter extends  RecyclerView.Adapter<ListUsersAdapter.ViewHolder> {

    private ArrayList<UserModel> usersList;

    public ListUsersAdapter(ArrayList<UserModel> inUsersList) {
        usersList = inUsersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_list_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(usersList.get(position).getName().concat(", ").
                concat(usersList.get(position).getEmail()).concat(". ").
                concat(usersList.get(position).getAddress()));
        holder.userModel = usersList.get(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView mTextView;
        private UserModel userModel;
        private final Context context;
        ViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            mTextView = v.findViewById(R.id.tv_text_user);

            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), UserActivity.class);
                    intent.putExtra("userId", userModel.getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}