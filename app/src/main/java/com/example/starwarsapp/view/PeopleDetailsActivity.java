package com.example.starwarsapp.view;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.starwarsapp.R;
import com.example.starwarsapp.model.People;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class PeopleDetailsActivity extends AppCompatActivity {

    private static final String NAME = "showTextView";

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    //private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String json = getIntent().getStringExtra(NAME);
        Gson gson = new Gson();
        People people = gson.fromJson(json, People.class);

        ImageView imageDetail = findViewById(R.id.imageDetail);
        String imageDetail1 = people.getImageDetail();
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

        ImageView imageCharacter = findViewById(R.id.imageCharacter);
        String imageIcon = people.getImageIcon();
        /*Picasso.with(this)
                .load(imageIcon)
                .error(R.drawable.error_icon)
                .into(imageCharacter);
        */
        Glide.with(this)
                .load(imageIcon)
                .fitCenter()
                .error(R.drawable.error_icon)
                .into(imageCharacter);

        TextView name = findViewById(R.id.name);
        name.setText(people.getName());

        TextView birthYear = findViewById(R.id.birth_year);
        birthYear.setText("Birth Year : " + people.getBirthYear());

        TextView eyeColor = findViewById(R.id.eye_color);
        eyeColor.setText("Eye color : " + people.getEyeColor());

        TextView gender = findViewById(R.id.gender);
        gender.setText("Gender : " + people.getGender());

        TextView hairColor = findViewById(R.id.hair_color);
        hairColor.setText("Hair color : " + people.getHairColor());

        TextView height = findViewById(R.id.height);
        height.setText("Height : " + people.getHeight() + " cm");

        TextView mass = findViewById(R.id.mass);
        mass.setText("Mass : " + people.getMass() + " kg");

        TextView skinColor = findViewById(R.id.skin_color);
        skinColor.setText("Skin color : " + people.getSkinColor());

        TextView homeworld = findViewById(R.id.homeworld);
        homeworld.setText("Homeworld : " + people.getHomeworld());

        TextView films = findViewById(R.id.films);
        JSONArray jsonArrayFilms = new JSONArray(people.getFilms());
        String listFilms = loadArray(jsonArrayFilms);
        films.setText("Films : " + listFilms);

        TextView species = findViewById(R.id.species);
        JSONArray jsonArraySpecies = new JSONArray(people.getSpecies());
        String listSpecies = loadArray(jsonArraySpecies);
        species.setText("Specie : " + listSpecies);

        TextView vehicles = findViewById(R.id.vehicles);
        JSONArray jsonArrayVehicles = new JSONArray(people.getVehicles());
        String listVehicles = loadArray(jsonArrayVehicles);
        vehicles.setText("Vehicles : " + listVehicles);

        TextView starships = findViewById(R.id.starships);
        JSONArray jsonArrayStarships = new JSONArray(people.getStarships());
        String listStarships = loadArray(jsonArrayStarships);
        starships.setText("Starships : " + listStarships);

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