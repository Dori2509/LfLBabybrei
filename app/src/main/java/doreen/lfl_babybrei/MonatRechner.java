package doreen.lfl_babybrei;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import doreen.lfl_babybrei.db.DBHelper;

/**
 * Created by Doreen on 20.11.2016.
 */
public class MonatRechner {

    /**
     *
     */
    private DBHelper mydb;
    /**
     *
     */
    private String day;
    /**
     * The Date.
     */
    private Date date;
    /**
     * The Diffmonths.
     */
    private long diffmonths;

    /**
     * Instantiates a new Monat rechner.
     *
     * @param birthday the birthday
     */
    public MonatRechner(final String birthday) {
        this.day = birthday;
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
     * Get alter long.
     *
     * @return the long
     */
    public long getAlter() {
        long alter = diffmonths;
       return alter;
    }

}
