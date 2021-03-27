package com.rahuls.myarea;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class} ,  version = 1)
public abstract class CountryRoomDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();
}
