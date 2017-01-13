package doreen.lfl_babybrei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import doreen.lfl_babybrei.db.DBHelper;

/**
 * Created by Doreen on 14.11.2016.
 */
public class WochenrechnerErgebnisActivity extends AppCompatActivity {
    /**
     * Toolbar.
     */
    private Toolbar toolbar;
    /**
     * Datenbankverbindung
     */
    private DBHelper mydb;
    /**
     * Ergebnisname.
     */
    private String ergebnisName;
    /**
     * Ergebnisdatum.
     */
    private String ergebnisDatum;
    /**
     * Datum
     */
    private Date date;

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * @param savedInstanceState Status
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wochenrechnerergebnis);
        initToolBar();
        TextView ergebnis = (TextView) findViewById(R.id.ErgebnisName);
        Bundle extras = getIntent().getExtras();

        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
            }
        });
        if (extras != null) {
            ergebnisName = extras.getString("Name");
            ergebnisDatum = extras.getString("datum");
        }

        //Aktuelles Datum zum Vergleich wird ausgelesen
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String prvvDate = df.format(new Date());


        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        try {
             date = format.parse(ergebnisDatum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfTheWeek = sdf.format(date);

        //Wochentag
       switch (dayOfTheWeek) {
           case "Monday":
               dayOfTheWeek = "Montag";
               break;
           case "Tuesday":
               dayOfTheWeek = "Dienstag";
               break;
           case "Wednesday":
               dayOfTheWeek = "Mittwoch";
               break;
           case "Thursday":
               dayOfTheWeek = "Donnerstag";
               break;
           case "Friday":
               dayOfTheWeek = "Freitag";
               break;
           case "Saturday":
               dayOfTheWeek = "Samstag";
               break;
           case "Sunday":
               dayOfTheWeek = "Sonntag";
               break;
           default:
               break;
       }

        //Wiederholung der eingegebenen Daten
        String geburtsdatum = "Geburtstag von " + ergebnisName + ": " + ergebnisDatum + ".\n\n";

        try {
            String currDate= ergebnisDatum;
            Date date1 = null;
            Date date2 = null;
            date1 = df.parse(currDate);
            date2 = df.parse(prvvDate);
            long diff = Math.abs(date1.getTime() - date2.getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
            long diffweeks = diffDays / 7;
            long diffmonths = (long) Math.floor(diffDays / 30.41667);
            long diffyears = diffDays / 365;

            //Differenz in Tage
            String tage = "Heute ist " + ergebnisName + " genau " + diffDays + " Tage alt.\n\n";
            //Differenz in Wochen
            long ddays = diffDays - (diffweeks*7);
            String wochen = "Heute ist " + ergebnisName + " genau " + diffweeks + " Wochen und " + ddays + " Tage alt.\n\n";

            long didays = (long) Math.floor(diffDays - (diffmonths * 30.41667));
            //Differenz in Monaten
            String monate = "Heute ist " + ergebnisName + " genau " + diffmonths + " Monate und " + didays + " Tage alt.\n\n";

            //Differenz in Jahre
            long difdays = 365 - (diffDays - (diffyears * 365));
            String jahre = ergebnisName + " ist " + diffyears + " und wird in " + difdays + " Tagen " + (diffyears + 1) + " Jahre alt.\n" +
                    "\n";

            String wochentag = ergebnisName + " wurde an einem " + dayOfTheWeek + " geboren.";

            String text = geburtsdatum + tage + wochen + monate + jahre + wochentag;
            ergebnis.setText(text);

        } catch (Exception e1) {
            System.out.println("exception " + e1);
        }
    }

    /**
     * Init tool bar.
     */
    public void initToolBar() {
        mydb = new DBHelper(this);
        String name = mydb.getName();
        int diamants = mydb.getDiamants();

        TextView profileName = (TextView) findViewById(R.id.username);
        TextView dia = (TextView) findViewById(R.id.points);

        profileName.setText(name);
        dia.setText(String.valueOf(diamants));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
