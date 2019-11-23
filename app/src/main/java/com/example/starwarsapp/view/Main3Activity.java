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

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private static final String NAME = "showTextView";

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    //private ShakeDetector mShakeDetector;
    private ArrayList<String> characterslist;

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

        TextView director = findViewById(R.id.director);
        director.setText("Director : " + films.getDirector());

        TextView producer = findViewById(R.id.producer);
        producer.setText("Producer : " + films.getProducer()   );

        TextView release_date = findViewById(R.id.release_date);
        release_date.setText("Release date : " + films.getRelease_date());

        TextView opening_crawl = findViewById(R.id.opening_crawl);
        opening_crawl.setText("Opening crawl : " + films.getOpening_crawl());

        TextView characters = findViewById(R.id.characters);
        characterslist = new ArrayList<>();
        characterslist.add(films.getCharacters().toString());
        String printlist = "";
        for (String name : characterslist){
            printlist = characterslist + name;
        }
        characters.setText("Characters : " + printlist);

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