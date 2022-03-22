package com.nethki.countries.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {

    @SerializedName("cca2")
    @Expose
    String cca2;

    @SerializedName("name")
    @Expose
    Name name;
    @SerializedName("flags")
    @Expose
    Flags flags;

    public Country(String cca2, Name name, Flags flags) {
        this.cca2 = cca2;
        this.name = name;
        this.flags = flags;
    }

    public String getCca2() {
        return cca2;
    }

    public void setCca2(String cca2) {
        this.cca2 = cca2;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }
}
