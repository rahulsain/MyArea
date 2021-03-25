package com.rahuls.myarea;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "country_table")
public class Country {

    @PrimaryKey
    public String countryName;
    public String capital;
    public String flagURL;
    public String region;
    public String subRegion;
    public long population;
    public String[] borders;
    public String[] languages;
}
