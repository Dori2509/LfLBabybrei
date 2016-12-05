package com.example.doreen.lfl_babybrei.rezepte;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.MainMenuActivity;
import com.example.doreen.lfl_babybrei.R;
import com.example.doreen.lfl_babybrei.db.DBHelper;
import com.example.doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doreen on 27.11.2016.
 */

public class Einkaufszettel extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;
    public ArrayList id_list;
    private ArrayList einkaufszutaten;
    private ArrayList einkaufszettel;
    private ArrayList einkaufszettelFinal;

    ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einkaufszettel);
        lv = (ListView) findViewById(R.id.listeEinkaufszettel);
        initToolBar();
        onLayer();
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        id_list = databaseAccess.getKochbuchRezepteID();

        einkaufszutaten = new ArrayList<Zutaten>();
        einkaufszettel = new ArrayList<Zutaten>();
        einkaufszettelFinal = new ArrayList<Zutaten>();
        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainMenuActivity.class);
                startActivity(i);
            }
        });

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







        final EinkaufszettelAdapter adapter = new EinkaufszettelAdapter(this, einkaufszettel);
        lv.setAdapter(adapter);
        final CheckBoxClick cbC = new CheckBoxClick(einkaufszettel.size());
        lv.setOnItemClickListener(cbC);


        Button zumSpiel = (Button) findViewById(R.id.zumSpiel);
        zumSpiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> c = cbC.getCheckedList();
                int b = 0;
                while (b < c.size()){
                    if(c.get(b).equals(0)){
                        Zutaten zu = (Zutaten) einkaufszettel.get(b);
                        einkaufszettelFinal.add(new Zutaten(zu.getMenge(), zu.getZutat()));
                    }
                    b++;
                }

                Intent intent = new Intent(getApplicationContext(),SpielbrettActivity.class);
                intent.putExtra("Liste", einkaufszettelFinal);
                mydb.close();
                startActivity(intent);
            }
        });
        databaseAccess.close();


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


    public void onLayer(){

        final Dialog dialog = new Dialog(this);
        Window window = dialog.getWindow();
        window.setLayout(1300,900);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.kochbuch_overlay);
        dialog.setCanceledOnTouchOutside(true);
        //for dismissing anywhere you touch
        View masterView = dialog.findViewById(R.id.coach_mark_master_view);
        masterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
