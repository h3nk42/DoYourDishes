package com.control.controllerLogic.PlanLogic.fragmentControllers.Users;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.control.asyncLogic.deletePlan.DeletePlanFacade;
import com.control.asyncLogic.deletePlan.DeletePlanFacadeFactory;
import com.control.asyncLogic.removeUserFromPlan.RemoveUserFacade;
import com.control.asyncLogic.removeUserFromPlan.RemoveUserFacadeFactory;
import com.control.controllerLogic.PlanLogic.PlanController;
import com.model.dataModel.Plan;
import com.view.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView userName;
    TextView userScore;
    Button removeUserButton;
    private PlanController planController;
    private static final String TAG = "UserViewHolder";


    UserViewHolder(View itemView, PlanController planController) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        userName = (TextView) itemView.findViewById(R.id.person_name);
        userScore = (TextView) itemView.findViewById(R.id.person_age);
        removeUserButton = (Button) itemView.findViewById(R.id.removeUserFromPlanButton);
        this.planController = planController;
        removeUserButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RemoveUserFacade removeUserFacade = RemoveUserFacadeFactory.produceRemoveUserFacade();
                if(userName.getText().toString().equals(planController.getActiveUserName())){
                    Log.d(TAG, "onClick: user deleted himself");
                    removeUserFacade.removeUserCallAsync(planController.getToken(),userName.getText().toString(), planController, true);
                } else {
                    Log.d(TAG, "onClick: user deleted someone else");
                    removeUserFacade.removeUserCallAsync(planController.getToken(),userName.getText().toString(), planController, false);
                }
            }
        });
    }
}