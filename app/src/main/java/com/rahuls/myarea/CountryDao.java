package com.rahuls.myarea;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Country country);

    @Query("DELETE FROM country_table")
    void deleteAll();

    @Query("SELECT * FROM country_table ORDER BY countryName ASC")
    LiveData<List<Country>> getAllCountries();
}
