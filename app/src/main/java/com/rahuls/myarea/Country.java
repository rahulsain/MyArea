package com.rahuls.myarea;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "country_table")
public class Country {

    @PrimaryKey
    String countryName;
    String capital,flagURL,region,subRegion;
    long population;
    String[] borders,languages;
}
