package com.example.dogapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DogBreed implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("bred_for")
    private String bredFor;

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBredGroup() {
        return bredGroup;
    }

    public void setBredGroup(String bredGroup) {
        this.bredGroup = bredGroup;
    }

    @SerializedName("breed_group")
    private String bredGroup;

    @SerializedName("life_span")
    private String lifeSpan;

    @SerializedName("origin")
    private String origin;

    @SerializedName("url")
    private String url;

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    @SerializedName("temperament")
    private String temperament;

    public String getTemperament() {
        return temperament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DogBreed(int id, String name, String lifeSpan, String origin, String url, String temperament, String bredFor, String bredGroup) {
        this.id = id;
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.url = url;
        this.temperament = temperament;
        this.bredFor = bredFor;
        this.bredGroup = bredGroup;
    }
}
