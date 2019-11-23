package com.example.starwarsapp.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.starwarsapp.R;
import com.example.starwarsapp.model.Films;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class FilmsDetailsActivity extends AppCompatActivity {

    private static final String NAME = "showTextView";

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    //private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films_details);

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
        String listCharacters = loadArray(jsonArrayCharacters);
        characters.setText(listCharacters);

        TextView planets = findViewById(R.id.planets);
        JSONArray jsonArrayPlanets = new JSONArray(films.getPlanets());
        String listPlanets = loadArray(jsonArrayPlanets);
        planets.setText(listPlanets);

        TextView starships = findViewById(R.id.starships);
        JSONArray jsonArrayStarships = new JSONArray(films.getStarships());
        String listStarships = loadArray(jsonArrayStarships);
        starships.setText(listStarships);

        WebView trailer = (WebView) findViewById( R.id.webview_compontent );
        trailer.getSettings().setJavaScriptEnabled(true);
        trailer.setWebChromeClient(new MyChrome());
        String playVideo= "<iframe src=\"" + films.getTrailer() + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
        trailer.loadData(playVideo, "text/html", "utf-8");

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

    private class MyChrome extends WebChromeClient {

        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        MyChrome() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846);
        }
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