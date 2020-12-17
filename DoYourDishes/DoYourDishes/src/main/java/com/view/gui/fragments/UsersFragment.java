package com.view.gui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.control.controllerLogic.PlanLogic.PlanController;
import com.control.controllerLogic.PlanLogic.fragmentControllers.RecyclerViewAdapterUser;
import com.control.controllerLogic.PlanLogic.fragmentControllers.UserFragmentController;
import com.model.dataModel.User;
import com.view.R;
import com.view.gui.PlanActivity;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {


    private static final String TAG =  "UsersFragment";
    private PlanActivity planActivity;
    private PlanController planController;
    private UserFragmentController userFragmentController;

    private View rootView;
    private ArrayAdapter<String> adapter;


    private RecyclerView recyclerView;


    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View RootView = inflater.inflate(R.layout.fragment_users, container, false);

        planActivity = (PlanActivity) getActivity();
        Log.d(TAG, "onCreateView: " + planActivity);
        this.planController = planActivity.getPlanController();
        Log.d(TAG, "onCreateView: " + planController);


        this.userFragmentController = new UserFragmentController( this.planController, this);

        List<User> userList = new ArrayList<User>();

        this.recyclerView = (RecyclerView) RootView.findViewById(R.id.usersFragmentRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(llm);
        RecyclerViewAdapterUser adapter = new RecyclerViewAdapterUser(userList, this.planController);
        this.recyclerView.setAdapter(adapter);

        this.rootView = RootView;
        return RootView;
    }

    public void renderData(List<User> usersToRender){
        RecyclerViewAdapterUser newAdapter = new RecyclerViewAdapterUser(usersToRender, this.planController);
        this.recyclerView.setAdapter(newAdapter);
    }

    public void addUser(){
        userFragmentController.addUser();
    }
}