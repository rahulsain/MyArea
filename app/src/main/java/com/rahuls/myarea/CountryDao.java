package com.rahuls.myarea;

import androidx.room.Dao;
import androidx.room.Delete;
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

    @Query("SELECT * FROM country_table")
    List<Country> getAll();

    @Delete
    void delete(Country country);
}
