package com.example.starwarsapp.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarsapp.R;
import com.example.starwarsapp.controller.StarshipsController;
import com.example.starwarsapp.model.People;
import com.example.starwarsapp.model.Starships;
import com.google.gson.Gson;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class StarshipsFragment extends Fragment {

    private static final String NAME = "showTextView";
    private static final String PREFS = "PREFS";

    public StarshipsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recyclerview, container, false);

        reload();

        return v;
    }

    public void showList(List<Starships> input) {
        RecyclerView recyclerView = getView().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new StarshipsAdapter(input, getListener(), getActivity());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private StarshipsAdapter.OnItemClickListener getListener() {
        return new StarshipsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Starships starships) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Main5Activity.class);
                Gson gson = new Gson();
                String json = gson.toJson(starships);
                intent.putExtra(NAME, json);
                startActivity(intent);

                final MediaPlayer soundNext = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.lightsaber_next);
                soundNext.start();

                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        };
    }

    public void reload() {
        StarshipsController starshipsController = new StarshipsController(this, getActivity().getBaseContext().getSharedPreferences(PREFS,MODE_PRIVATE));
        starshipsController.onStart();
    }
}