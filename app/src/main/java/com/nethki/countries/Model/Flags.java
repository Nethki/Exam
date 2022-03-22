package com.nethki.countries.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flags {

    @SerializedName("png")
    @Expose
    String png;

    public Flags(String png) {
        this.png = png;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }
}
