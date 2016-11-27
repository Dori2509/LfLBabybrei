package com.example.doreen.lfl_babybrei.rezepte;

/**
 * Created by Doreen on 21.11.2016.
 */

public class Zutaten {

    private long Menge;
    private String Zutat;

    public Zutaten(long Menge, String zutat) {
        this.Menge = Menge;
        this.Zutat = zutat;
    }

    public long getMenge() {
        return Menge;
    }

    public String getZutat() {
        return Zutat;
    }


}