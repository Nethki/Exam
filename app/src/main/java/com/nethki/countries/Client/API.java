package com.nethki.countries.Client;

import com.nethki.countries.Model.Country;
import com.nethki.countries.Model.CountryName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    String BASE_URL = "https://restcountries.com/v3.1/";
    int READ_TIMEOUT = 30;
    int CONNECTION_TIMEOUT = 30;
    String CONTENT_TYPE = "application/json";

    @GET("region/{region}")
    Call<List<Country>> getCountries(@Path("region") String region);

    @GET("all")
    Call<List<Country>> getAllCountries();

    @GET("name/{name}")
    Call<List<CountryName>> getCountryName(@Path("name") String region);
}
