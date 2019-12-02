package com.example.starwarsapp.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.starwarsapp.R;
import com.example.starwarsapp.controller.ShakeDetector;
import com.example.starwarsapp.model.Planets;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class PlanetsDetailsActivity extends AppCompatActivity {

    private static final String NAME = "showTextView";

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String json = getIntent().getStringExtra(NAME);
        Gson gson = new Gson();
        Planets planets = gson.fromJson(json, Planets.class);

        ImageView imageDetail = findViewById(R.id.imageDetail);
        String imageDetail1 = planets.getPicture();
        /*Picasso.with(this)
                .load(image)
                .error(R.drawable.error_icon)
                .into(imageView);
        */
        Glide.with(this)
                .load(imageDetail1)
                .fitCenter()
                .error(R.drawable.error_icon)
                .into(imageDetail);

        ImageView imagePlanet = findViewById(R.id.imagePlanets);
        String image = planets.getPicture();
        /*Picasso.with(this)
                .load(imageIcon)
                .error(R.drawable.error_icon)
                .into(imageCharacter);
        */
        Glide.with(this)
                .load(image)
                .fitCenter()
                .error(R.drawable.error_icon)
                .into(imagePlanet);

        TextView name = findViewById(R.id.name);
        name.setText(planets.getName());

        TextView rotation_period = findViewById(R.id.rotation_period);
        rotation_period.setText("Rotation Period : " + planets.getRotation_period() + " h");

        TextView orbital_period = findViewById(R.id.orbital_period);
        orbital_period.setText("Orbital Period : " + planets.getOrbital_period() + " days");

        TextView diameter = findViewById(R.id.diameter);
        diameter.setText("Diameter : " + planets.getDiameter() + " km");

        TextView climate = findViewById(R.id.climate);
        climate.setText("Climate : " + planets.getClimate());

        TextView gravity = findViewById(R.id.gravity);
        gravity.setText("Gravity : " + planets.getGravity() + " m/s²");

        TextView terrain = findViewById(R.id.terrain);
        terrain.setText("Terrain : " + planets.getTerrain());

        TextView surface_water = findViewById(R.id.surface_water);
        surface_water.setText("Surface Water : " + planets.getSurface_water() + " %");

        TextView population = findViewById(R.id.population);
        population.setText("Population : " + planets.getPopulation());

        TextView residents = findViewById(R.id.residents);
        JSONArray jsonArrayResidents = new JSONArray(planets.getResidents());
        String listResidents = loadArray(jsonArrayResidents);
        residents.setText("Residents : " + listResidents);

        TextView films = findViewById(R.id.films);
        JSONArray jsonArrayFilms = new JSONArray(planets.getFilms());
        String listFilms = loadArray(jsonArrayFilms);
        films.setText("Films : " + listFilms);

        shakePhone();
    }

        public void shakePhone(){
            // ShakeDetector initialization
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mAccelerometer = mSensorManager
                    .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mShakeDetector = new ShakeDetector();
            mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

                @Override
                public void onShake(int count) {
                    final MediaPlayer soundPrevious = MediaPlayer.create(getApplicationContext(), R.raw.planet_theme);
                    soundPrevious.start();

                    final ImageView planets = findViewById(R.id.planets);
                    planets.animate().alpha(1f).setDuration(600);
                    planets.setImageDrawable(getResources().getDrawable(R.drawable.planets));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            planets.animate().alpha(0f).setDuration(600);
                        }
                    }, 12800);
                }
            });
        }

    public String loadArray(JSONArray jsonArray){
        String listItems = "";
        try {
            listItems = jsonArray.getString(0);
            for (int i = 1; i < jsonArray.length(); i++){
                listItems = listItems + ", " + jsonArray.getString(i);
                System.out.println(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listItems;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
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