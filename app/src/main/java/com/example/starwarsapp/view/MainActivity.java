package com.example.starwarsapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.starwarsapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //private RecyclerView recyclerView;
    //private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager layoutManager;
    // private MainController controller;

    private static final String NAME = "showTextView";
    private static final String PREFS = "PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // recyclerView = findViewById(R.id.my_recycler_view);

        final MediaPlayer soundStart = MediaPlayer.create(getApplicationContext(), R.raw.lightsaber_on);
        soundStart.start();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //When rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new FavoritesFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_list:
                            selectedFragment = new ListFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
/*
    public void showList(List<People> input){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ItemAdapter(input, getListener(), this);
        recyclerView.setAdapter(mAdapter);
    }

    private ItemAdapter.OnItemClickListener getListener() {
        return new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(People people) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                Gson gson = new Gson();
                String json = gson.toJson(people);
                intent.putExtra(NAME, json);
                startActivity(intent);

                final MediaPlayer soundNext = MediaPlayer.create(getApplicationContext(), R.raw.lightsaber_next);
                soundNext.start();

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        };
    }
*/
    @Override
    public void finish() {
        super.finish();
        final MediaPlayer soundPrevious = MediaPlayer.create(getApplicationContext(), R.raw.lightsaber_previous);
        soundPrevious.start();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}