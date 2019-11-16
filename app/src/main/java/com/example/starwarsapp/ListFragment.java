package com.example.starwarsapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarsapp.model.People;
import com.google.gson.Gson;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ListFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<People> peopleList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;


    private static final String NAME = "showTextView";
    private static final String PREFS = "PREFS";

    public ListFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list, container, false);
        //v.findViewById(R.id.my_recycler_view);
        //ItemAdapter itemAdapter = new ItemAdapter(peopleList, getListener(), getActivity());
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setAdapter(itemAdapter);

/*
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ItemAdapter itemAdapter = new ItemAdapter(peopleList, getListener(), getActivity());
        recyclerView.setAdapter(itemAdapter);
*/
/*
        controller = new MainController(this, getActivity().getBaseContext().getSharedPreferences(PREFS,MODE_PRIVATE));
        controller.onStart();
*/
        return v;
    }


    public void showList(List<People> input){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ItemAdapter(input, getListener(), getActivity());
        recyclerView.setAdapter(mAdapter);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //recyclerView = findVi

        controller = new MainController(this, getActivity().getBaseContext().getSharedPreferences(PREFS,MODE_PRIVATE));
        controller.onStart();
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