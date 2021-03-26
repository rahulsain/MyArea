package com.rahuls.myarea;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String FETCHURL = "https://restcountries.eu/rest/v2/region/asia";
    List<Continent> recipes;
    private RecyclerView recyclerview;
    private ArrayList<Continent> arrayList;
    private CountryListAdapter adapter;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.pb);
        pb.setVisibility(View.GONE);

        recyclerview = findViewById(R.id.recyclerview);
        arrayList = new ArrayList<>();
        adapter = new CountryListAdapter(this, arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(adapter);


        isNetworkConnected();

    }

    public void isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) {
            fetchfromRoom();
        } else {
            fetchfromServer();
        }
    }


    private void fetchfromRoom() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                List<Country> recipeList = CountryRepository.getInstance(MainActivity.this).getAppDatabase().countryDao().getAll();
//                arrayList.clear();
                for (Country recipe: recipeList) {
                    Continent repo = new Continent(recipe.getId(),
                            recipe.getCountryName(),
                            recipe.getCapital(),
                            recipe.getFlagURL(),
                            recipe.getRegion(),
                            recipe.getSubRegion(),
                            recipe.getPopulation(),
                            recipe.getBorders(),
                            recipe.getLanguages());
                    arrayList.add(repo);
                }
                // refreshing recycler view
                runOnUiThread(() -> adapter.notifyDataSetChanged());
            }
        });
        thread.start();


    }

    private void fetchfromServer() {
        pb.setVisibility(View.VISIBLE);

        JsonArrayRequest request = new JsonArrayRequest(FETCHURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                        if (response == null) {
                            pb.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject country = response.getJSONObject(i);

                                Continent continent = new Continent();

                                // Get the current student (json object) data
                                continent.setCountryName(country.getString("name"));
                                continent.setCapital(country.getString("capital"));
                                continent.setFlagURL(country.getString("flag"));
                                continent.setRegion(country.getString("region"));
                                continent.setSubRegion(country.getString("subregion"));
                                continent.setPopulation(country.getString("population"));
                                continent.setBorders(country.getString("borders"));
                                continent.setLanguages(country.getString("languages"));

                                // Display the formatted json data in text view
                                arrayList.add(continent);
                            }

//                        recipes = new Gson().fromJson(response.toString(), new TypeToken<List<Continent>>() {
//                        }.getType());
//
//                        // adding data to cart list
//                        arrayList.clear();
//                        arrayList.addAll(recipes);


                        // refreshing recycler view
                        adapter.notifyDataSetChanged();

                        pb.setVisibility(View.GONE);

                        saveTask();

                    }catch (JsonSyntaxException | JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                pb.setVisibility(View.GONE);
                Log.e("TAG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        request.setShouldCache(false);

        requestQueue.add(request);
    }


    private void saveTask() {


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                if(recipes != null) {
                    for (int i = 0; i < recipes.size(); i++) {
                        Country recipe = new Country();
                        recipe.setCountryName(recipes.get(i).getCountryName());
                        recipe.setCapital(recipes.get(i).getCapital());
                        recipe.setFlagURL(recipes.get(i).getFlagURL());
                        recipe.setRegion(recipes.get(i).getRegion());
                        recipe.setSubRegion(recipes.get(i).getSubRegion());
                        recipe.setPopulation(recipes.get(i).getPopulation());
                        recipe.setBorders(recipes.get(i).getBorders());
                        recipe.setLanguages(recipes.get(i).getLanguages());
                        CountryRepository.getInstance(getApplicationContext()).getAppDatabase().countryDao().insert(recipe);
                    }
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute(avoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    public void DeleteAll(View view) {
        CountryRepository.getInstance(getApplicationContext()).getAppDatabase().countryDao().deleteAll();
    }
}