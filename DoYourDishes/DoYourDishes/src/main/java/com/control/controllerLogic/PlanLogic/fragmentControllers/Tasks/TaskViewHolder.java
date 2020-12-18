package com.control.controllerLogic.PlanLogic.fragmentControllers.Tasks;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.control.asyncLogic.deleteTask.DeleteTaskFacade;
import com.control.asyncLogic.deleteTask.DeleteTaskFacadeFactory;
import com.control.asyncLogic.deleteUser.DeleteUserFacadeFactory;
import com.control.asyncLogic.fulfillTask.FulfillTaskFacade;
import com.control.asyncLogic.fulfillTask.FulfillTaskFacadeFactory;
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
    View itemView;

    private PlanController planController;

    TaskViewHolder(View itemView, PlanController planController) {
        super(itemView);
        this.itemView = itemView;
        cv = (CardView) itemView.findViewById(R.id.taskCardView);
        taskName = (TextView) itemView.findViewById(R.id.taskName);
        pointsWorth = (TextView) itemView.findViewById(R.id.taskPoints);
        lastTimeDone = (TextView) itemView.findViewById(R.id.taskTimeStamp);
        deleteTaskButton = (Button) itemView.findViewById(R.id.deleteTaskButton);
        fulfillTaskButton = (Button) itemView.findViewById(R.id.taskDoneButton);
        this.planController = planController;


        fulfillTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FulfillTaskFacade fulfillTaskFacade = FulfillTaskFacadeFactory.produceFulfillTaskFacade();
                fulfillTaskFacade.fulfillTaskCallAsync(planController.getToken(), taskId, planController);
            }
        });

        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeleteTaskFacade deleteTaskFacade = DeleteTaskFacadeFactory.produceDeleteTaskFacade();
                deleteTaskFacade.deleteTaskCallAsync(planController.getToken(), taskId, planController);
            }
        });
    }

    public View getView() {
        return itemView;
    }
}