package com.control.controllerLogic.PlanLogic.fragmentControllers.Tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.control.controllerLogic.PlanLogic.PlanController;
import com.control.controllerLogic.PlanLogic.fragmentControllers.Users.UserViewHolder;
import com.model.dataModel.Task;
import com.model.dataModel.User;
import com.view.R;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

public class RecyclerViewAdapterTask extends RecyclerView.Adapter<TaskViewHolder>{

    List<Task> taskList;
    private PlanController planController;

    public RecyclerViewAdapterTask(List<Task> taskList, PlanController planController){
        this.taskList = taskList;
        this.planController = planController;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card_view, parent, false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(v,planController);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int position) {
        BigInteger timesTamp = taskList.get(position).getLastTimeDone();
        timesTamp = timesTamp.divide(BigInteger.valueOf(1000));
        timesTamp = BigInteger.valueOf(System.currentTimeMillis() / 1000L).subtract(timesTamp);
        taskViewHolder.taskName.setText(taskList.get(position).getName());
        taskViewHolder.pointsWorth.setText("Worth: " + taskList.get(position).getPointsWorth().toString() + " points");
        taskViewHolder.lastTimeDone.setText("done " + timesTamp.toString() + " s ago");
        taskViewHolder.taskId = taskList.get(position).getTaskId();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

}
