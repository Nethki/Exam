package com.nethki.countries;

import android.app.Application;

import com.nethki.countries.Client.API;
import com.nethki.countries.Client.RetrofitClient;

public class CountryApplication extends Application {
    public static API apiClient;
    @Override
    public void onCreate() {
        super.onCreate();

        apiClient = RetrofitClient.getClient().create(API.class);

    }
}
