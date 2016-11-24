package com.example.doreen.lfl_babybrei;

import android.content.Context;

import com.example.doreen.lfl_babybrei.db.DBHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Doreen on 20.11.2016.
 */

public class MonatRechner {

    private DBHelper mydb;
    private String day;
    Date date;
    public long diffmonths;

    public MonatRechner(String birthday){
        this.day = birthday;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String PrvvDate = df.format(new Date());

        System.out.println("Hallo Mila");
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        try {
            date = format.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            String CurrDate=  day;
            Date date1 = null;
            Date date2 = null;
            date1 = df.parse(CurrDate);
            date2 = df.parse(PrvvDate);
            long diff = Math.abs(date1.getTime() - date2.getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
            diffmonths = (long) Math.floor(diffDays / 30.41667);


            System.out.println("Alter:" + diffmonths);

        } catch (Exception e1) {
            System.out.println("exception " + e1);
        }

    }

    public long getAlter(){
        long Alter = diffmonths;
       return Alter;
    }

}
