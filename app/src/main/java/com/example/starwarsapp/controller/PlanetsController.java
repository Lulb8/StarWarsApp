package com.example.starwarsapp.controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.starwarsapp.model.Planets;
import com.example.starwarsapp.model.RestApi;
import com.example.starwarsapp.model.RestPlanetsResponse;
import com.example.starwarsapp.view.PlanetsFragment;
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

public class PlanetsController {

    private PlanetsFragment planetsFragment;
    private RestApi restApi;

    private final String OBJECT = "OBJECT";
    private final String NUMBER_OBJECTS = "NUMBER_OBJECTS";
    SharedPreferences sharedPreferences;

    static final String BASE_URL = "https://raw.githubusercontent.com/Lulb8/Apis/master/SWAPI/";

    public PlanetsController(PlanetsFragment planetsFragment, SharedPreferences sharedPreferences) {
        this.planetsFragment = planetsFragment;
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
            List<Planets> planetsList = getListFromDataBase();
            planetsFragment.showList(planetsList);
        } else {*/
            makeApiCall();
        //}
    }

    private void makeApiCall() {
        Call<RestPlanetsResponse> call = restApi.getListPlanets();
        call.enqueue(new Callback<RestPlanetsResponse>() {
            @Override
            public void onResponse(Call<RestPlanetsResponse> call, Response<RestPlanetsResponse> response) {
                RestPlanetsResponse restPlanetsResponse = response.body();
                List<Planets> listPlanets = restPlanetsResponse.getResults();
                storeData(listPlanets);

                planetsFragment.showList(listPlanets);
            }

            @Override
            public void onFailure(Call<RestPlanetsResponse> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }

    private void storeData(List<Planets> listPlanets) {
        Gson gson = new Gson();
        String json = gson.toJson(listPlanets);
        sharedPreferences
                .edit().putString(OBJECT,json)
                .putInt(NUMBER_OBJECTS,listPlanets.size())
                .apply();
    }

    private List<Planets> getListFromDataBase() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(OBJECT,null);
        Type type = new TypeToken<List<Planets>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private boolean hasDataInDataBase() {
        return sharedPreferences.contains(NUMBER_OBJECTS);
    }
}