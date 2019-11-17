package com.example.starwarsapp.controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.starwarsapp.model.People;
import com.example.starwarsapp.model.RestPeopleApi;
import com.example.starwarsapp.model.RestPeopleResponse;
import com.example.starwarsapp.view.ListFragment;
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

public class MainController {

    //private MainActivity activity;
    private ListFragment listFragment;
    private RestPeopleApi restPeopleApi;

    private final String OBJECT = "OBJECT";
    private final String NUMBER_OBJECTS = "NUMBER_OBJECTS";
    SharedPreferences sharedPreferences;

    static final String BASE_URL = "https://raw.githubusercontent.com/Lulb8/Apis/master/SWAPI/";

    public MainController(ListFragment listFragment, SharedPreferences sharedPreferences) {
        this.listFragment = listFragment;
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

        restPeopleApi = retrofit.create(RestPeopleApi.class);

        if (hasDataInDataBase()) {
            List<People> peopleList = getListFromDataBase();
            listFragment.showList(peopleList);
        } else {
            makeApiCall();
        }
    }

    private void makeApiCall() {
        Call<RestPeopleResponse> call = restPeopleApi.getListPeople();
        call.enqueue(new Callback<RestPeopleResponse>() {
            @Override
            public void onResponse(Call<RestPeopleResponse> call, Response<RestPeopleResponse> response) {
                RestPeopleResponse restPeopleResponse = response.body();
                List<People> listPeople = restPeopleResponse.getResults();
                storeData(listPeople);

                listFragment.showList(listPeople);
            }

            @Override
            public void onFailure(Call<RestPeopleResponse> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }

    private void storeData(List<People> listPeople) {
        Gson gson = new Gson();
        String json = gson.toJson(listPeople);
        sharedPreferences
                .edit().putString(OBJECT,json)
                .putInt(NUMBER_OBJECTS,listPeople.size())
                .apply();
    }

    private List<People> getListFromDataBase() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(OBJECT,null);
        Type type = new TypeToken<List<People>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private boolean hasDataInDataBase() {
        return sharedPreferences.contains(NUMBER_OBJECTS);
    }
}