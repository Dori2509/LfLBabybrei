package com.example.doreen.lfl_babybrei.rezepte;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Doreen on 21.11.2016.
 */

public class Zutaten implements Serializable {

    private long Menge;
    private String Zutat;

    public Zutaten(long Menge, String zutat) {
        this.Menge = Menge;
        this.Zutat = zutat;
    }

    protected Zutaten(Parcel in) {
        Menge = in.readLong();
        Zutat = in.readString();
    }


    public long getMenge() {
        return Menge;
    }

    public String getZutat() {
        return Zutat;
    }



}