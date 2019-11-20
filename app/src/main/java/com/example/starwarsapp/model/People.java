package com.example.starwarsapp.model;

import java.util.ArrayList;

public class People {
    private String name;
    private String image_icon;
    private String image_detail;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private ArrayList films;
    private ArrayList species;
    private ArrayList vehicles;
    private ArrayList starships;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageIcon() {
        return image_icon;
    }

    public void setImageIcon(String image_icon) {
        this.image_icon = image_icon;
    }

    public String getImageDetail() {
        return image_detail;
    }

    public void setImageDetail(String image_detail) {
        this.image_detail = image_detail;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hair_color;
    }

    public void setHairColor(String hair_color) {
        this.hair_color = hair_color;
    }

    public String getSkinColor() {
        return skin_color;
    }

    public void setSkinColor(String skin_color) {
        this.skin_color = skin_color;
    }

    public String getEyeColor() {
        return eye_color;
    }

    public void setEyeColor(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getBirthYear() {
        return birth_year;
    }

    public void setBirthYear(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public ArrayList getFilms() {
        return films;
    }

    public void setFilms(ArrayList films) {
        this.films = films;
    }

    public ArrayList getSpecies() {
        return species;
    }

    public void setSpecies(ArrayList species) {
        this.species = species;
    }

    public ArrayList getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList getStarships() {
        return starships;
    }

    public void setStarships(ArrayList starships) {
        this.starships = starships;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
