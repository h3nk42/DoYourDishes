package com.control.controllerLogic.PlanLogic.fragmentControllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.model.dataModel.User;
import com.view.R;


import java.util.List;

public class RecyclerViewAdapterUser extends RecyclerView.Adapter<UserViewHolder>{

    List<User> userList;

    public RecyclerViewAdapterUser(List<User> userList){
        this.userList = userList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card_view, parent, false);
        UserViewHolder uvh = new UserViewHolder(v);
        return uvh;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        userViewHolder.userName.setText(userList.get(position).getUserName());
        userViewHolder.userScore.setText("points: " + userList.get(position).getPointsInPlan().toString());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}