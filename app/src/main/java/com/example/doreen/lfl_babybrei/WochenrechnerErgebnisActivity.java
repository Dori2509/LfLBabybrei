package com.example.doreen.lfl_babybrei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.db.DBHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Doreen on 14.11.2016.
 */

public class WochenrechnerErgebnisActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb;
    String getName;
    String getDatum;
    String ErgebnisName;
    String ErgebnisDatum;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wochenrechnerergebnis);
        initToolBar();
        TextView Ergebnis = (TextView) findViewById(R.id.ErgebnisName);
        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            ErgebnisName = extras.getString("Name");
            ErgebnisDatum = extras.getString("Datum");
        }

        System.out.println(ErgebnisDatum);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String PrvvDate = df.format(new Date());


        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        try {
             date = format.parse(ErgebnisDatum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfTheWeek = sdf.format(date);

       switch(dayOfTheWeek)
       {
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
       }

        String geburtsdatum = "Geburtstag von " + ErgebnisName + ": " + ErgebnisDatum + ".\n\n";

        try {
            String CurrDate=  ErgebnisDatum;
            Date date1 = null;
            Date date2 = null;
            date1 = df.parse(CurrDate);
            date2 = df.parse(PrvvDate);
            long diff = Math.abs(date1.getTime() - date2.getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
            long diffweeks = diffDays / 7;
            long diffmonths = (long) Math.floor(diffDays / 30.41667);
            long diffyears = diffDays / 365;

            String Tage = "Heute ist " +  ErgebnisName +" genau " + diffDays + " Tage alt.\n\n";

            long ddays = diffDays - (diffweeks*7);
            String Wochen = "Heute ist " +  ErgebnisName +" genau " + diffweeks + " Wochen und " + ddays + " Tage alt.\n\n";

            long didays = (long) Math.floor(diffDays - (diffmonths*30.41667));

            String Monate = "Heute ist " +  ErgebnisName +" genau " + diffmonths + " Monate und " + didays + " Tage alt.\n\n";

            long difdays = 365 - (diffDays - (diffyears*365));
            String Jahre = ErgebnisName +" ist " + diffyears + " und wird in " + difdays + " Tagen " + (diffyears+1) + " Jahre alt.\n" +
                    "\n";

            String wochentag = ErgebnisName + " wurde an einem " + dayOfTheWeek + " geboren.";

            String text =geburtsdatum + Tage + Wochen + Monate + Jahre + wochentag;
            Ergebnis.setText(text);


        } catch (Exception e1) {
            System.out.println("exception " + e1);
        }


    }

    public void initToolBar() {
        mydb = new DBHelper(this);
        String Name = mydb.getName();
        int diamants = mydb.getDiamants();

        TextView ProfileName = (TextView) findViewById(R.id.username);
        TextView Dia = (TextView) findViewById(R.id.points);

        ProfileName.setText(Name);
        Dia.setText(String.valueOf(diamants));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }






}
