package com.example.doreen.lfl_babybrei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.db.DBHelper;
import com.example.doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.List;

/**
 * Created by Doreen on 26.10.2016.
 */
public class MilchActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beitrag);
        initToolBar();

        this.listView = (ListView) findViewById(R.id.listView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getBeitraege("infobox");
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        this.listView.setAdapter(adapter);
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
