package com.example.starwarsapp.view;

import android.content.Context;
import android.content.Intent;
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
import com.example.starwarsapp.model.Starships;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class StarshipsDetailsActivity extends AppCompatActivity {

    private static final String NAME = "showTextView";

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starships_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String json = getIntent().getStringExtra(NAME);
        Gson gson = new Gson();
        Starships starships = gson.fromJson(json, Starships.class);

        ImageView imageDetail = findViewById(R.id.imageDetail);
        String imageDetail1 = starships.getPicture();
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
        String image = starships.getPicture();
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
        name.setText(starships.getName());

        TextView model = findViewById(R.id.model);
        model.setText("Model : " + starships.getModel());

        TextView manufacturer = findViewById(R.id.manufacturer);
        manufacturer.setText("Manufacturer : " + starships.getManufacturer());

        TextView cost_in_credits = findViewById(R.id.cost_in_credits);
        cost_in_credits.setText("Cost in Credits : " + starships.getCost_in_credits() + " galactic credits");

        TextView length = findViewById(R.id.length);
        length.setText("Length : " + starships.getLength() + " m");

        TextView max_atmosphering_speed = findViewById(R.id.max_atmosphering_speed);
        max_atmosphering_speed.setText("Max Atmosphering Speed : " + starships.getMax_atmosphering_speed());

        TextView crew = findViewById(R.id.crew);
        crew.setText("Crew : " + starships.getCrew());

        TextView passengers = findViewById(R.id.passengers);
        passengers.setText("Passengers : " + starships.getPassengers());

        TextView cargo_capacity = findViewById(R.id.cargo_capacity);
        cargo_capacity.setText("Cargo Capacity : " + starships.getCargo_capacity());

        TextView consumables = findViewById(R.id.consumables);
        consumables.setText("MGLT : " + starships.getConsumables());

        TextView hyperdrive_rating = findViewById(R.id.hyperdrive_rating);
        hyperdrive_rating.setText("Hyperdrive Rating : " + starships.getHyperdrive_rating());

        TextView MGLT = findViewById(R.id.MGLT);
        MGLT.setText("Cargo Capacity : " + starships.getMGLT());

        TextView starship_class = findViewById(R.id.starship_class);
        starship_class.setText("Starship Class : " + starships.getStarship_class());

        TextView pilots = findViewById(R.id.pilots);
        JSONArray jsonArrayPilots = new JSONArray(starships.getPilots());
        String listPilots = loadArray(jsonArrayPilots);
        pilots.setText("Pilots : " + listPilots);

        TextView films = findViewById(R.id.films);
        JSONArray jsonArrayFilms = new JSONArray(starships.getFilms());
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
                    final MediaPlayer soundPrevious = MediaPlayer.create(getApplicationContext(), R.raw.death_star_theme);
                    soundPrevious.start();

                    final ImageView death_star = findViewById(R.id.death_star);
                    death_star.animate().alpha(1f).setDuration(600);
                    death_star.setImageDrawable(getResources().getDrawable(R.drawable.death_star));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            death_star.animate().alpha(0f).setDuration(600);
                        }
                    }, 12000);
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