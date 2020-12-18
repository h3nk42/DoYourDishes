package com.control.controllerLogic.PlanLogic.fragmentControllers.Users;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import com.control.asyncLogic.addUserToPlan.AddUserFacade;
import com.control.asyncLogic.addUserToPlan.AddUserFacadeFactory;
import com.control.controllerLogic.PlanLogic.PlanController;

import com.view.gui.fragments.UsersFragment;

public class UserFragmentController {

    private UsersFragment usersFragment;
    private PlanController planController;

    public UserFragmentController (PlanController planController, UsersFragment usersFragment){
        this.planController = planController;
        this.usersFragment = usersFragment;
    }

    public void addUser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(usersFragment.getActivity());
        builder.setTitle("enter userName!");

        final EditText input = new EditText(usersFragment.getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("taskName");
        builder.setView(input);



        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddUserFacade addUserFacade = AddUserFacadeFactory.produceAddUserFacade();
                addUserFacade.addUserCallAsync(planController.getToken(), input.getText().toString(), planController);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

}
