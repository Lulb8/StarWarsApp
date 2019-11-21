package com.example.starwarsapp.controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.starwarsapp.model.Films;
import com.example.starwarsapp.model.RestApi;
import com.example.starwarsapp.model.RestFilmsResponse;
import com.example.starwarsapp.view.FilmsFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmsController {

    private FilmsFragment filmsFragment;
    private RestApi restApi;

    private final String OBJECT = "OBJECT";
    private final String NUMBER_OBJECTS = "NUMBER_OBJECTS";
    SharedPreferences sharedPreferences;

    static final String BASE_URL = "https://raw.githubusercontent.com/Lulb8/Apis/master/SWAPI/";

    public FilmsController(FilmsFragment filmsFragment, SharedPreferences sharedPreferences) {
        this.filmsFragment = filmsFragment;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        restApi = retrofit.create(RestApi.class);

        /*if (hasDataInDataBase()) {
            List<Films> listFilms = getListFromDataBase();
            filmsFragment.showList(listFilms);
        } else {*/
            makeApiCall();
        //}
    }

    private void makeApiCall() {
        Call<RestFilmsResponse> call = restApi.getListFilms();
        call.enqueue(new Callback<RestFilmsResponse>() {
            @Override
            public void onResponse(Call<RestFilmsResponse> call, Response<RestFilmsResponse> response) {
                RestFilmsResponse restFilmsResponse = response.body();
                List<Films> listFilms = restFilmsResponse.getResults();
                storeData(listFilms);

                filmsFragment.showList(listFilms);
            }

            @Override
            public void onFailure(Call<RestFilmsResponse> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }

    private void storeData(List<Films> listFilms) {
        Gson gson = new Gson();
        String json = gson.toJson(listFilms);
        sharedPreferences
                .edit().putString(OBJECT,json)
                .putInt(NUMBER_OBJECTS,listFilms.size())
                .apply();
    }

    private List<Films> getListFromDataBase() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(OBJECT,null);
        Type type = new TypeToken<List<Films>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private boolean hasDataInDataBase() {
        return sharedPreferences.contains(NUMBER_OBJECTS);
    }
}