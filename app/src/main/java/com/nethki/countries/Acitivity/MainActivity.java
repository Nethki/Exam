package com.nethki.countries.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nethki.countries.Adapters.RvCountryAdapter;
import com.nethki.countries.Adapters.RvMenuAdapter;
import com.nethki.countries.CountryApplication;
import com.nethki.countries.Model.Continent;
import com.nethki.countries.Model.Country;
import com.nethki.countries.Model.CountryName;
import com.nethki.countries.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RvMenuAdapter.OnclickButton, RvCountryAdapter.OnClickButtonCountry {

    Toolbar toolbar;
    RecyclerView rvMenu;
    RecyclerView rvCountry;
    RvMenuAdapter rvMenuAdapter;
    RvCountryAdapter rvCountryAdapter;
    EditText etSearch;
    List<Country> countryList;
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_toolbar);
        rvMenu = findViewById(R.id.rv_menu);
        rvCountry = findViewById(R.id.rv_country);
        etSearch = findViewById(R.id.edit_search);

        mProgress = findViewById(R.id.progressBar);

        //toolbar setup
        toolbar.setTitle(R.string.home);
        setSupportActionBar(toolbar);

        /*
        *setup recyclerview menu
        *setup static menu
         */

        List<Continent> continentList = new ArrayList<>();
        String[] arrayName = getResources().getStringArray(R.array.array_region);
        int defaultMenu = 0;
        for (int i = 0; i < arrayName.length; i++) {

                Continent continent = new Continent();
                continent.setActive(i==defaultMenu);
                continent.setName(arrayName[i]);
                continentList.add(continent);


        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvMenu.setLayoutManager(layoutManager);
        rvMenuAdapter = new RvMenuAdapter(this,continentList,this);
        Log.e("test",getResources().getStringArray(R.array.array_region).length + "");
        rvMenu.setAdapter(rvMenuAdapter);


        //Initialize Country Recyclerview



        LinearLayoutManager layoutManagerCountry = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCountry.setLayoutManager(layoutManagerCountry);
        List<Country> countryList = new ArrayList<>();
        rvCountryAdapter = new RvCountryAdapter(this,countryList,this);
        rvCountry.setAdapter(rvCountryAdapter);
        //initialize all country

        //progress dialog here
        getAllCountries(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {


                //dismiss progress here
                mProgress.setVisibility(View.GONE);
                intializeData(response.body());
                rvCountryAdapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });

        //search initialize
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("TEST", "beforeTextChanged " + charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("TEST", "onTextChanged " + charSequence.toString());
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

                Log.e("TEST","afterTextChanged " + editable.toString());

            }
        });
    }

    public void intializeData(List<Country> countries){
        countryList = countries;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        List<Country> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Country item : countryList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().getCommon().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            rvCountryAdapter.filterList(filteredlist);
        }
    }


    @Override
    public void onClickButton(int position, String name) {
        if(position == 0){
            rvCountry.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
            getAllCountries(new Callback<List<Country>>() {
                @Override
                public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                    mProgress.setVisibility(View.GONE);
                    rvCountry.setVisibility(View.VISIBLE);
                    rvCountryAdapter.updateData(response.body());
                }

                @Override
                public void onFailure(Call<List<Country>> call, Throwable t) {

                }
            });
        }else{
            rvCountry.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
            getCountries(name, new Callback<List<Country>>() {
                @Override
                public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                    mProgress.setVisibility(View.GONE);
                    rvCountry.setVisibility(View.VISIBLE);
                    rvCountryAdapter.updateData(response.body());
                }

                @Override
                public void onFailure(Call<List<Country>> call, Throwable t) {

                }
            });
        }

        rvMenuAdapter.changStatus(position);
    }

    @Override
    public void onClickCountry(String common) {
        Log.e("Test","MAIN" + common);

        //progress here
        rvCountry.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
        getCountryName(common, new Callback<List<CountryName>>() {
            @Override
            public void onResponse(Call<List<CountryName>> call, Response<List<CountryName>> response) {
                Log.e("HERE" , response.body().get(0).getFlags().getPng());
                mProgress.setVisibility(View.GONE);
                rvCountry.setVisibility(View.VISIBLE);
                startActivity(new Intent(MainActivity.this, com.nethki.countries.Acitivity.Country.class).putExtra("data", new Gson().toJson(response.body())));
            }

            @Override
            public void onFailure(Call<List<CountryName>> call, Throwable t) {
                Log.e("HERE",t.getMessage());
            }
        });
    }


    public static void getCountries(String continent, Callback<List<Country>> callback) {
        Call<List<Country>> call = CountryApplication.apiClient.getCountries(continent);
        call.enqueue(callback);
    }

    public static void getCountryName(String common, Callback<List<CountryName>> callback) {
        Call<List<CountryName>> call = CountryApplication.apiClient.getCountryName(common);
        call.enqueue(callback);
    }

    public static void getAllCountries(Callback<List<Country>> callback) {
        Call<List<Country>> call = CountryApplication.apiClient.getAllCountries();
        call.enqueue(callback);
    }

}