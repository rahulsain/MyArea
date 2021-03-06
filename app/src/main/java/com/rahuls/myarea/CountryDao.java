package com.rahuls.myarea;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert
    void insert(Country country);

    @Query("SELECT * FROM country_table")
    List<Country> getAll();

    @Query("DELETE FROM country_table")
    void deleteAll();
}
