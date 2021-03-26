package com.rahuls.myarea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {

    Context context;
    List<Continent> arrayList;

    public CountryListAdapter(Context context, List<Continent> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Continent repo = arrayList.get(position);

        holder.name.setText(repo.getCountryName());
        holder.region.setText("Region:" + repo.getRegion());
        holder.capital.setText("Capital:" + repo.getCapital());
        holder.subregion.setText("SubRegion: " + repo.getSubRegion());
        holder.population.setText("Population: " + repo.getPopulation());
        holder.borders.setText("Shared Borders: " + repo.getBorders());
        holder.languages.setText("Languages Spoken Here: " + repo.getLanguages());


        Utils.fetchSvg(context, repo.getFlagURL(), holder.flag);

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, capital, region, subregion, population, borders, languages;
        public ImageView flag;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.country_name);
            region = itemView.findViewById(R.id.country_region);
            capital = itemView.findViewById(R.id.country_capital);
            subregion = itemView.findViewById(R.id.country_subregion);
            flag = itemView.findViewById(R.id.country_flag);
            population = itemView.findViewById(R.id.country_population);
            borders = itemView.findViewById(R.id.country_borders);
            languages = itemView.findViewById(R.id.country_languages);
        }
    }

}
