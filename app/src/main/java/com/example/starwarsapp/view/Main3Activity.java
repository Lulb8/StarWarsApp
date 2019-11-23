package com.example.starwarsapp.view;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.starwarsapp.R;
import com.example.starwarsapp.model.Films;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private static final String NAME = "showTextView";

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    //private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String json = getIntent().getStringExtra(NAME);
        Gson gson = new Gson();
        Films films = gson.fromJson(json, Films.class);

        ImageView film_poster = findViewById(R.id.film_poster);
        String image = films.getFilm_poster();


        /*Picasso.with(this)
                .load(image)
                .error(R.drawable.error_icon)
                .into(imageView);
        */
        Glide.with(this)
                .load(image)
                .fitCenter()
                .error(R.drawable.error_icon)
                .into(film_poster);

        TextView title = findViewById(R.id.title);
        title.setText(films.getTitle());

        ImageView poster = findViewById(R.id.poster);
        String poster1 = films.getFilm_poster();


        /*Picasso.with(this)
                .load(poster1)
                .error(R.drawable.error_icon)
                .into(poster);
        */
        Glide.with(this)
                .load(poster1)
                .fitCenter()
                .error(R.drawable.error_icon)
                .into(poster);

        TextView director = findViewById(R.id.director);
        director.setText(films.getDirector());

        TextView producer = findViewById(R.id.producer);
        producer.setText(films.getProducer()   );

        TextView release_date = findViewById(R.id.release_date);
        release_date.setText(films.getRelease_date());

        TextView opening_crawl = findViewById(R.id.opening_crawl);
        opening_crawl.setText("Opening crawl : " + films.getOpening_crawl());

        TextView characters = findViewById(R.id.characters);
        JSONArray jsonArrayCharacters = new JSONArray(films.getCharacters());
        String listCharacters = "";
        try {
            listCharacters = jsonArrayCharacters.getString(0);
            for (int i = 1; i < jsonArrayCharacters.length(); i++){
                listCharacters = listCharacters + ", " + jsonArrayCharacters.getString(i);
                System.out.println(jsonArrayCharacters.getString(i));
            }
            System.out.println(listCharacters);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        characters.setText(listCharacters);

        TextView planets = findViewById(R.id.planets);
        JSONArray jsonArrayPlanets = new JSONArray(films.getPlanets());
        String listPlanets = "";
        try {
            listPlanets = jsonArrayPlanets.getString(0);
            for (int i = 1; i < jsonArrayPlanets.length(); i++){
                listPlanets = listPlanets + ", " + jsonArrayPlanets.getString(i);
                System.out.println(jsonArrayPlanets.getString(i));
            }
            System.out.println(listPlanets);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        planets.setText(listPlanets);

        TextView starships = findViewById(R.id.starships);
        JSONArray jsonArrayStarships = new JSONArray(films.getStarships());
        String listStarships = "";
        try {
            listStarships = jsonArrayStarships.getString(0);
            for (int i = 1; i < jsonArrayStarships.length(); i++){
                listStarships = listStarships + ", " + jsonArrayStarships.getString(i);
                System.out.println(jsonArrayStarships.getString(i));
            }
            System.out.println(listStarships);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        starships.setText(listStarships);

        //shakePhone();
    }

    /*
        public void shakePhone(){
            // ShakeDetector initialization
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mAccelerometer = mSensorManager
                    .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mShakeDetector = new ShakeDetector();
            mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

                @Override
                public void onShake(int count) {
                    final MediaPlayer soundPrevious = MediaPlayer.create(getApplicationContext(), R.raw.imperial_march);
                    soundPrevious.start();

                    final ImageView darth_vader = findViewById(R.id.darth_vader);
                    darth_vader.animate().alpha(1f).setDuration(600);
                    darth_vader.setImageDrawable(getResources().getDrawable(R.drawable.darth_vader_icon));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            darth_vader.animate().alpha(0f).setDuration(600);
                        }
                    }, 9100);
                }
            });
        }
    */
    @Override
    public void onResume() {
        super.onResume();
        //mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        //mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public void finish() {
        super.finish();
        final MediaPlayer soundPrevious = MediaPlayer.create(getApplicationContext(), R.raw.lightsaber_previous);
        soundPrevious.start();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}