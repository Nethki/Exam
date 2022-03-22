package com.nethki.countries.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class CountryName {

    @SerializedName("name")
    @Expose
    Name name;

    @SerializedName("continents")
    @Expose
    List<String> continents;

    @SerializedName("flags")
    @Expose
    Flags flags;

    @SerializedName("region")
    @Expose
    String region;

    @SerializedName("subregion")
    @Expose
    String subregion;

    @SerializedName("capital")
    @Expose
    List<String> capital;

    @SerializedName("languages")
    @Expose
    Map<String, String> languages;

    @SerializedName("currencies")
    @Expose
    Map<String, Currencies> currencies;

    @SerializedName("population")
    @Expose
    int population;

    @SerializedName("timezones")
    @Expose
    List<String> timezones;

    @SerializedName("latlng")
    @Expose
    List<Double> latlng;

    @SerializedName("area")
    @Expose
    Double area;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public Map<String, Currencies> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Currencies> currencies) {
        this.currencies = currencies;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public List<String> getContinents() {
        return continents;
    }

    public void setContinents(List<String> continents) {
        this.continents = continents;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "CountryName{" +
                "name=" + name +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", capital='" + capital + '\'' +
                ", languages=" + languages +
                ", currencies=" + currencies +
                ", population=" + population +
                ", timezones=" + timezones +
                ", latlng=" + latlng +
                ", area=" + area +
                '}';
    }
}
