package com.rahuls.myarea;

public class Continent {
    int id;
    String countryName,capital,flagURL,region,subRegion,population;
    String borders,languages;

    public Continent(){
        this.id = 0;
        this.countryName = "";
        this.capital = "";
        this.flagURL = "";
        this.region = "";
        this.subRegion = "";
        this.population = "";
        this.borders = "";
        this.languages = "";
    }

    public Continent(int id,String countryName, String capital , String flagURL, String region , String subRegion
            , String population , String borders , String languages){
        this.id = id;
        this.countryName = countryName;
        this.capital = capital;
        this.flagURL = flagURL;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlagURL() {
        return flagURL;
    }

    public void setFlagURL(String flagURL) {
        this.flagURL = flagURL;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
