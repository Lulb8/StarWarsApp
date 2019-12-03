package com.example.starwarsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.starwarsapp.R;
import com.example.starwarsapp.controller.ShakeDetector;

public class InfoActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title = findViewById(R.id.title);
        title.setText("Credits");

        TextView description = findViewById(R.id.description);
        description.setText("The app data come from the SWAPI API : https://swapi.co and the pictures come from various sources. Star Wars and all associated names are copyright Lucasfilm ltd.");

        TextView author = findViewById(R.id.author);
        author.setText("App created by Lucie.B");

        TextView hint = findViewById(R.id.hint);
        hint.setText("Hint : try to shake your phone on some pages and turn up your volume !");

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

                final ImageView cake = findViewById(R.id.cake);
                cake.animate().alpha(1f).setDuration(600);
                cake.setImageDrawable(getResources().getDrawable(R.drawable.cake));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cake.animate().alpha(0f).setDuration(600);
                    }
                }, 1000);
            }
        });
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
