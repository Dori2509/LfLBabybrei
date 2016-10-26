package com.example.doreen.lfl_babybrei;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Doreen on 24.10.2016.
 */
public class MainMenu extends AppCompatActivity{

    Toolbar toolbar;
    // Array of strings storing country names
    String[] menupuenkte = new String[] {
            "Profil",
            "Milch & Co.",
            "Beikost",
            "Infobox",
            "Rezeptübersicht",
            "Kochbuch",
            "Wochenrechner",
            "Minigames"
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] menubilder = new int[]{
            R.drawable.profil,
            R.drawable.milch,
            R.drawable.beikost,
            R.drawable.infobox,
            R.drawable.infobox,
            R.drawable.infobox,
            R.drawable.infobox,
            R.drawable.infobox
    };

    // Array of strings to store currencies
    String[] untertitel = new String[]{
            "bearbeiten, löschen",
            "nützliche Hinweise, versch. Fertignahrung etc.",
            "nützliche Hinweise, Obst- und Gemüsekunde",
            "Rückrufe, Ernährungsplan, Küchengeflüster",
            "XXX",
            "XXX",
            "XXX",
            "XXX"
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

    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);




    }
}
