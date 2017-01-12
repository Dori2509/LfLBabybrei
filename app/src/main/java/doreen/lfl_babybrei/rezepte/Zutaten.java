package doreen.lfl_babybrei.rezepte;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by Doreen on 21.11.2016.
 */
public class Zutaten implements Serializable {

    /**
     *
     */
    private long menge;
    /**
     *
     */
    private String zutat;

    /**
     * Instantiates a new Zutaten.
     *
     * @param menge the menge
     * @param zutat the zutat
     */
    public Zutaten(final long menge, final String zutat) {
        this.menge = menge;
        this.zutat = zutat;
    }

    /**
     * Instantiates a new Zutaten.
     *
     * @param in the in
     */
    protected Zutaten(final Parcel in) {
        menge = in.readLong();
        zutat = in.readString();
    }


    /**
     * Gets menge.
     *
     * @return the menge
     */
    public long getMenge() {
        return menge;
    }

    /**
     * Gets zutat.
     *
     * @return the zutat
     */
    public String getZutat() {
        return zutat;
    }

}
