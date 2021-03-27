package com.rahuls.myarea;

import android.content.Context;
import androidx.room.Room;

public class CountryRepository {
    private Context mCtx;
    private static CountryRepository mInstance;

    //our app database object
    private CountryRoomDatabase appDatabase;

    private CountryRepository(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //alldata is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, CountryRoomDatabase.class, "alldata")
                .allowMainThreadQueries().build();
    }

    public static synchronized CountryRepository getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new CountryRepository(mCtx);
        }
        return mInstance;
    }

    public CountryRoomDatabase getAppDatabase() {
        return appDatabase;
    }

}
