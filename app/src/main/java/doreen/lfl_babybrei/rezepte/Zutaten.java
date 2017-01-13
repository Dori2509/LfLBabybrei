package doreen.lfl_babybrei.rezepte;

import android.os.Parcel;
import java.io.Serializable;

/**
 * Aufbau/Inhalt einer Zutat
 * Created by Doreen on 21.11.2016.
 */
public class Zutaten implements Serializable {

    /**
     * Menge
     */
    private long menge;
    /**
     * Zutat
     */
    private String zutat;

    /**
     * Initialisierung einer neuen Zutat
     *
     * @param menge menge
     * @param zutat zutat
     */
    public Zutaten(final long menge, final String zutat) {
        this.menge = menge;
        this.zutat = zutat;
    }

    /**
     * Instantiates a new Zutaten.
     *
     * @param in in

    protected Zutaten(final Parcel in) {
        menge = in.readLong();
        zutat = in.readString();
    }
     */

    /**
     * Getter für die Menge
     *
     * @return menge
     */
    public long getMenge() {
        return menge;
    }

    /**
     * Getter für Zutat.
     *
     * @return zutat
     */
    public String getZutat() {
        return zutat;
    }
}
