package com.example.starwarsapp.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("people.json")
    Call<RestPeopleResponse> getListPeople();

    @GET("films.json")
    Call<RestFilmsResponse> getListFilms();

    @GET("planets.json")
    Call<RestPlanetsResponse> getListPlanets();

    @GET("species.json")
    Call<RestPeopleResponse> getListSpecies();

    @GET("starships.json")
    Call<RestStarshipsResponse> getListStarships();

    @GET("vehicles.json")
    Call<RestPeopleResponse> getListVehicles();

}
