package com.example.starwarsapp.model;

import java.util.ArrayList;

public class Films {
    private String title;
    private String film_poster;
    private String episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    private ArrayList characters;
    private ArrayList planets;
    private ArrayList starships;
    private ArrayList vehicles;
    private ArrayList species;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilm_poster() {
        return film_poster;
    }

    public void setFilm_poster(String film_poster) {
        this.film_poster = film_poster;
    }

    public String getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(String episode_id) {
        this.episode_id = episode_id;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public void setOpening_crawl(String opening_crawl) {
        this.opening_crawl = opening_crawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public ArrayList getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList characters) {
        this.characters = characters;
    }

    public ArrayList getPlanets() {
        return planets;
    }

    public void setPlanets(ArrayList planets) {
        this.planets = planets;
    }

    public ArrayList getStarships() {
        return starships;
    }

    public void setStarships(ArrayList starships) {
        this.starships = starships;
    }

    public ArrayList getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList getSpecies() {
        return species;
    }

    public void setSpecies(ArrayList species) {
        this.species = species;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
