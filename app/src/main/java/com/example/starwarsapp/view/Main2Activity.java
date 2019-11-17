package com.example.starwarsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.example.starwarsapp.R;
import com.example.starwarsapp.model.People;
import com.google.gson.Gson;

public class Main2Activity extends AppCompatActivity {

    private static final String NAME = "showTextView";

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    //private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String json = getIntent().getStringExtra(NAME);
        Gson gson = new Gson();
        People people = gson.fromJson(json, People.class);

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

        TextView source = findViewById(R.id.linkUrl);
        source.setText("Source : https://swapi.co/api/people");

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