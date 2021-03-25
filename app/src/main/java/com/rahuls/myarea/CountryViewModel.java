package com.rahuls.myarea;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository mRepository;

    private final LiveData<List<Country>> mAllCountries;

    public CountryViewModel (Application application) {
        super(application);
        mRepository = new CountryRepository(application);
        mAllCountries = mRepository.getAllCountries();
    }

    LiveData<List<Country>> mAllCountries() { return mAllCountries; }

    public void insert(Country country) { mRepository.insert(country); }
}
