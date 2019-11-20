package com.example.starwarsapp.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("pokemon")
    Call<RestPeopleResponse> getListPeople();

    @GET("type")
    Call<RestFilmsResponse> getListFilms();

    @GET("ability")
    Call<RestPlanetsResponse> getListPlanets();

    @GET("species.json")
    Call<RestPeopleResponse> getListSpecies();

    @GET("starships.json")
    Call<RestPeopleResponse> getListStarships();

    @GET("vehicles.json")
    Call<RestPeopleResponse> getListVehicles();

}
