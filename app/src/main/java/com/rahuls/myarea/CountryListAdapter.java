package com.rahuls.myarea;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class CountryListAdapter extends ListAdapter<Country, CountryViewHolder> {

    public CountryListAdapter(@NonNull DiffUtil.ItemCallback<Country> diffCallback) {
        super(diffCallback);
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CountryViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        Country current = getItem(position);
        holder.bind(current.getCountry());
    }

}
