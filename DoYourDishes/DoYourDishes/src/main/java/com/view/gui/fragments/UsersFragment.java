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

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View RootView = inflater.inflate(R.layout.fragment_users, container, false);

        planActivity = (PlanActivity) getActivity();
        planController = planActivity.getPlanController();


        this.userFragmentController = new UserFragmentController( this);

        List<User> userList = new ArrayList<User>();
        userList.add(new User("placeHolder","meinPlan", 10));
        userList.add(new User("placeHolder","asd", 20));
        userList.add(new User("placeHolder","meinPlan", 10));
        userList.add(new User("placeHolder","asd", 20));

        this.recyclerView = (RecyclerView) RootView.findViewById(R.id.usersFragmentRecyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(userList);
        this.recyclerView.setAdapter(adapter);



        this.rootView = RootView;
        return RootView;
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

        List<User> userList;

        RVAdapter(List<User> userList){
            this.userList = userList;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card_view, parent, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int position) {
            personViewHolder.personName.setText(userList.get(position).getUserName());
            personViewHolder.personAge.setText(userList.get(position).getPointsInPlan().toString());
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        public class PersonViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView personName;
            TextView personAge;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.cv);
                personName = (TextView) itemView.findViewById(R.id.person_name);
                personAge = (TextView) itemView.findViewById(R.id.person_age);
            }
        }

    }

    public void renderData(){
        Log.d(TAG, "renderData:" + planController.users.get(0));

        RVAdapter adapter = new RVAdapter(planController.users);
        this.recyclerView.setAdapter(adapter);
    }
}