package com.example.starwarsapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.starwarsapp.R;

public class HomeFragment extends Fragment {

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        TextView welcome = (TextView) v.findViewById(R.id.welcome);
        welcome.setText("Welcome to the ultimate Star Wars App !");

        TextView description = (TextView) v.findViewById(R.id.description);
        description.setText("In this app you will be able to learn everything about the films, characters, planets and starships by clicking on the menu at the bottom of the screen. You can also find a camera and credits in the toolbar above.");

        return v;
    }
}