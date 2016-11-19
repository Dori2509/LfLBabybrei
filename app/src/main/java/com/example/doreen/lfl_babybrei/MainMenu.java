package com.example.doreen.lfl_babybrei;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.db.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Doreen on 24.10.2016.
 */
public class MainMenu extends AppCompatActivity{

    Toolbar toolbar;
    private DBHelper mydb ;
    // Array of strings storing country names
    String[] menupuenkte = new String[] {
            "Profil",
            "Milch & Co.",
            "Beikost",
            "Infobox",
            "Rezeptübersicht",
            "Kochbuch",
            "Wochenrechner",
            "minigames"
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] menubilder = new int[]{
            R.drawable.profil,
            R.drawable.milch,
            R.drawable.beikost,
            R.drawable.infobox,
            R.drawable.rezepte,
            R.drawable.kochbuch,
            R.drawable.wochenrechner,
            R.drawable.minigames
    };

    // Array of strings to store currencies
    String[] untertitel = new String[]{
            "bearbeiten, löschen",
            "nützliche Hinweise, versch. Fertignahrung etc.",
            "nützliche Hinweise, Obst- und Gemüsekunde",
            "Rückrufe, Ernährungsplan, Küchengeflüster",
            "Finde hier Rezepte je nach Alter abgestimmt",
            "Deine Lieblingsrezepte in der Übersicht",
            "Berechne das Alter in Tagen, Wochen usw. einer beliebigen Person",
            "TicTacToe, Memory, FoodDrop, Quiz, SimonSays"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        initToolBar();
        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<untertitel.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", menupuenkte[i]);
            hm.put("cur", untertitel[i]);
            hm.put("flag", Integer.toString(menubilder[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt","cur" };

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt,R.id.cur};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) findViewById(R.id.listview);


        // Setting the adapter to the listView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                System.out.println(arg2 + " + Bernd");
                switch(arg2)
                {
                    case 0:

                        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getApplicationContext(),MilchActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(getApplicationContext(),BeikostActivity.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(getApplicationContext(),InfoboxActivity.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(getApplicationContext(),RezeptUebersichtActivity.class);
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent(getApplicationContext(),KochbuchAcitivity.class);
                        startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7 = new Intent(getApplicationContext(),WochenrechnerActivity.class);
                        startActivity(intent7);
                        break;
                    case 7:
                        Intent intent8 = new Intent(getApplicationContext(), MinigamesActivity.class);
                        startActivity(intent8);
                        break;
                    default:

                }
            }
        });
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
