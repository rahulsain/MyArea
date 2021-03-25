package com.rahuls.myarea;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CountryViewHolder extends RecyclerView.ViewHolder {
    private final TextView countryItemView;

    private CountryViewHolder(View itemView) {
        super(itemView);
        countryItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        countryItemView.setText(text);
    }

    static CountryViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CountryViewHolder(view);
    }
}
