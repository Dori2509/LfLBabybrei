package com.example.doreen.lfl_babybrei.rezepte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.R;
import com.example.doreen.lfl_babybrei.db.DBHelper;
import com.example.doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;

/**
 * Created by Doreen on 27.11.2016.
 */

public class Einkaufszettel extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;
    public ArrayList id_list;
    private ArrayList einkaufszutaten;
    private ArrayList einkaufszettel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einkaufszettel);
        initToolBar();

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        id_list = databaseAccess.getKochbuchRezepteID();

        einkaufszutaten = new ArrayList<Zutaten>();
        einkaufszettel = new ArrayList<Zutaten>();

        int a = 0;
        while (a < id_list.size())
        {
            einkaufszutaten = databaseAccess.getZutatenEinkaufszettel((Integer) id_list.get(a));
            int b = 0;
            while (b < einkaufszutaten.size())
            {
                Zutaten c = (Zutaten) einkaufszutaten.get(b);
                if(a==0){
                    einkaufszettel.add(new Zutaten(c.getMenge(), c.getZutat()));

                }else{
                    int d = 0;
                    long gesamtmenge = c.getMenge();
                    while (d < einkaufszettel.size()){
                        Zutaten testZutat = (Zutaten) einkaufszettel.get(d);
                        if(testZutat.getZutat().equals(c.getZutat())){
                            //falls bereits in Liste vorhanden
                            gesamtmenge = c.getMenge() + ((Zutaten) einkaufszettel.get(d)).getMenge();
                            einkaufszettel.remove(d);

                        }
                        d++;
                    }

                    einkaufszettel.add(new Zutaten(gesamtmenge, c.getZutat()));
                }
                b++;
            }
            a++;
        }
        int e = 0;
        while (e < einkaufszettel.size()) {
            System.out.println(((Zutaten) einkaufszettel.get(e)).getMenge() + " " + ((Zutaten) einkaufszettel.get(e)).getZutat());
            e++;
        }

        //TODO
        // Einsortierung in Cluster
        // Info Popup
        // Hilfebutton auf Spielbrett




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
