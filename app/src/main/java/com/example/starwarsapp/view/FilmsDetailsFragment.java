package com.example.starwarsapp.view;

import android.hardware.Sensor;
import android.hardware.SensorManager;
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
import com.example.starwarsapp.model.People;
import com.google.gson.Gson;

public class FilmsDetailsFragment extends Fragment {

    private static final String NAME = "showTextView";

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    //private ShakeDetector mShakeDetector;

    public FilmsDetailsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_films_details, container, false);

        getDetails();

        return v;
    }

    public void getDetails() {
        String json = getActivity().getIntent().getStringExtra(NAME);
        Gson gson = new Gson();
        People people = gson.fromJson(json, People.class);
/*
        ImageView imageDetail = getView().findViewById(R.id.imageDetail);
        String image = people.getImageDetail();
*/
        /*Picasso.with(this)
                .load(image)
                .error(R.drawable.error_icon)
                .into(imageView);
        */
        /*
        Glide.with(this)
                .load(image)
                .fitCenter()
                .error(R.drawable.error_icon)
                .into(imageDetail);

        TextView name = getView().findViewById(R.id.name);
        name.setText(people.getName());

        TextView birthYear = getView().findViewById(R.id.birth_year);
        birthYear.setText("Birth Year : " + people.getBirthYear());

        TextView eyeColor = getView().findViewById(R.id.eye_color);
        eyeColor.setText("Eye color : " + people.getEyeColor());

        TextView gender = getView().findViewById(R.id.gender);
        gender.setText("Gender : " + people.getGender());

        TextView hairColor = getView().findViewById(R.id.hair_color);
        hairColor.setText("Hair color : " + people.getHairColor());

        TextView height = getView().findViewById(R.id.height);
        height.setText("Height : " + people.getHeight() + " cm");

        TextView mass = getView().findViewById(R.id.mass);
        mass.setText("Mass : " + people.getMass() + " kg");

        TextView skinColor = getView().findViewById(R.id.skin_color);
        skinColor.setText("Skin color : " + people.getSkinColor());
*/
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
/*
    @Override
    public void finish() {
        super.finish();
        final MediaPlayer soundPrevious = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.lightsaber_previous);
        soundPrevious.start();

        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }*/
}