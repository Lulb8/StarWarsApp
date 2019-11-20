package com.example.starwarsapp.controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.starwarsapp.model.RestApi;
import com.example.starwarsapp.model.RestStarshipsResponse;
import com.example.starwarsapp.model.Starships;
import com.example.starwarsapp.view.StarshipsFragment;
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

public class StarshipsController {

    private StarshipsFragment starshipsFragment;
    private RestApi restApi;

    private final String OBJECT = "OBJECT";
    private final String NUMBER_OBJECTS = "NUMBER_OBJECTS";
    SharedPreferences sharedPreferences;

    static final String BASE_URL = "https://raw.githubusercontent.com/Lulb8/Apis/master/SWAPI/";

    public StarshipsController(StarshipsFragment starshipsFragment, SharedPreferences sharedPreferences) {
        this.starshipsFragment = starshipsFragment;
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
            List<People> peopleList = getListFromDataBase();
            peopleFragment.showList(peopleList);
        } else {*/
            makeApiCall();
        //}
    }

    private void makeApiCall() {
        Call<RestStarshipsResponse> call = restApi.getListStarships();
        call.enqueue(new Callback<RestStarshipsResponse>() {
            @Override
            public void onResponse(Call<RestStarshipsResponse> call, Response<RestStarshipsResponse> response) {
                RestStarshipsResponse restStarshipsResponse = response.body();
                List<Starships> listStarships = restStarshipsResponse.getResults();
                storeData(listStarships);

                starshipsFragment.showList(listStarships);
            }

            @Override
            public void onFailure(Call<RestStarshipsResponse> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }

    private void storeData(List<Starships> listStarships) {
        Gson gson = new Gson();
        String json = gson.toJson(listStarships);
        sharedPreferences
                .edit().putString(OBJECT,json)
                .putInt(NUMBER_OBJECTS,listStarships.size())
                .apply();
    }

    private List<Starships> getListFromDataBase() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(OBJECT,null);
        Type type = new TypeToken<List<Starships>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private boolean hasDataInDataBase() {
        return sharedPreferences.contains(NUMBER_OBJECTS);
    }
}