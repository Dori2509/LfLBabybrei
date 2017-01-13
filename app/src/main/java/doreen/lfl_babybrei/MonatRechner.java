package doreen.lfl_babybrei;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import doreen.lfl_babybrei.db.DBHelper;

/**
 * Berechnung des Alters
 * Created by Doreen on 20.11.2016.
 */
public class MonatRechner {

    /**
     * Datenbankverbindung
     */
    private DBHelper mydb;
    /**
     * Tag
     */
    private String day;
    /**
     * Date.
     */
    private Date date;
    /**
     * monatliche Differenz
     */
    private long diffmonths;

    /**
     *  Initialisierung der Berechnung
     * @param birthday Geburtsdatum
     */
    public MonatRechner(final String birthday) {
        this.day = birthday;
        //Auslesen des aktuellen Datums
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String prvvDate = df.format(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        try {
            date = format.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            //Aktuelles Datum wird genutzt um die Berechnung der Monate zu veranlassen
            String currDate=  day;
            Date date1 = null;
            Date date2 = null;
            date1 = df.parse(currDate);
            date2 = df.parse(prvvDate);
            long diff = Math.abs(date1.getTime() - date2.getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
            diffmonths = (long) Math.floor(diffDays / 30.41667);



        } catch (Exception e1) {
            System.out.println("exception " + e1);
        }
    }

    /**
     * Getter für Alter
     * @return alter
     */
    public long getAlter() {
        long alter = diffmonths;
       return alter;
    }
}
