package com.nethki.countries.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currencies {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("symbol")
    @Expose
    String symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
