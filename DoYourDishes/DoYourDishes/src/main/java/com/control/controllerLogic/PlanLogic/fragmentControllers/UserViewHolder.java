package com.control.controllerLogic.PlanLogic.fragmentControllers;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.view.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView userName;
    TextView userScore;

    UserViewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        userName = (TextView) itemView.findViewById(R.id.person_name);
        userScore = (TextView) itemView.findViewById(R.id.person_age);
    }
}