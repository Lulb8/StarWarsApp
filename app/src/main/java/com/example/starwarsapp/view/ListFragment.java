package com.example.starwarsapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarsapp.controller.MainController;
import com.example.starwarsapp.R;
import com.example.starwarsapp.model.People;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    FragmentActivity listener;
    View v;
    private RecyclerView recyclerView;
    public List<People> peopleList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;
    private ItemAdapter itemAdapter;

    private static final String NAME = "showTextView";
    private static final String PREFS = "PREFS";

    public ListFragment() { }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<People> people = new ArrayList<People>();
        itemAdapter = new ItemAdapter(people, getListener(), getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list, container, false);
        //v.findViewById(R.id.my_recycler_view);

        //showList(peopleList);

        /*recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ItemAdapter itemAdapter = new ItemAdapter(peopleList, getListener(), getActivity());
        recyclerView.setAdapter(itemAdapter);
*/


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ItemAdapter itemAdapter = new ItemAdapter(peopleList, getListener(), getActivity());*/
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


/*
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        controller = new MainController(this, getActivity().getBaseContext().getSharedPreferences(PREFS,MODE_PRIVATE));
        controller.onStart();
    }
 */

    public void showList(List<People> input){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ItemAdapter(input, getListener(), getContext());
        recyclerView.setAdapter(mAdapter);
    }

    private ItemAdapter.OnItemClickListener getListener() {
        return new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(People people) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Main2Activity.class);
                Gson gson = new Gson();
                String json = gson.toJson(people);
                intent.putExtra(NAME, json);
                startActivity(intent);

                final MediaPlayer soundNext = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.lightsaber_next);
                soundNext.start();

                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        };
    }
}