package com.control.controllerLogic.PlanLogic.fragmentControllers.Tasks;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.control.asyncLogic.removeUserFromPlan.RemoveUserFacade;
import com.control.asyncLogic.removeUserFromPlan.RemoveUserFacadeFactory;
import com.control.controllerLogic.PlanLogic.PlanController;
import com.view.R;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView taskName;
    TextView pointsWorth;
    TextView lastTimeDone;
    Button fulfillTaskButton;
    Button deleteTaskButton;
    String taskId;

    private PlanController planController;

    TaskViewHolder(View itemView, PlanController planController) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.taskCardView);
        taskName = (TextView) itemView.findViewById(R.id.taskName);
        pointsWorth = (TextView) itemView.findViewById(R.id.taskPoints);
        lastTimeDone = (TextView) itemView.findViewById(R.id.taskTimeStamp);
        deleteTaskButton = (Button) itemView.findViewById(R.id.deleteTaskButton);
        fulfillTaskButton = (Button) itemView.findViewById(R.id.taskDoneButton);
        this.planController = planController;


        fulfillTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              /* RemoveUserFacade removeUserFacade = RemoveUserFacadeFactory.produceRemoveUserFacade();
                removeUserFacade.removeUserCallAsync(planController.getToken(), taskId, planController);*/

            }
        });
    }
}