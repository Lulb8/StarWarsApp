package com.example.starwarsapp.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestPeopleApi {

    @GET("people.json")
    Call<RestPeopleResponse> getListPeople();
/*
    @GET("films.json")
    Call<RestPeopleResponse> getListFilms();

    @GET("planets.json")
    Call<RestPeopleResponse> getListPlanets();

    @GET("species.json")
    Call<RestPeopleResponse> getListSpecies();

    @GET("starships.json")
    Call<RestPeopleResponse> getListStarships();

    @GET("vehicles.json")
    Call<RestPeopleResponse> getListVehicles();
*/
}
