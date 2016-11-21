package com.example.doreen.lfl_babybrei.rezepte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.MyAdapter;
import com.example.doreen.lfl_babybrei.R;
import com.example.doreen.lfl_babybrei.beitraege.Beitrag;
import com.example.doreen.lfl_babybrei.db.DBHelper;
import com.example.doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doreen on 19.11.2016.
 */

public class RezeptMonat extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;
    private GridView listView;
    private List<MyAdapter.Item> quotes;
    public ArrayList id_list;
    private int Value;
    private  List<String> enable;



    @Override
    public void onRestart() {
        super.onRestart();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            Value = extras.getInt("Monat");
        }
        quotes = databaseAccess.getRezepte(Value);
        id_list = databaseAccess.getAllRezepteID(Value);
        listView.setAdapter(new MyAdapter(this, quotes));
        enable = databaseAccess.getAllRezepteEnabled();
        initToolBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beitrag);
        initToolBar();

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            Value = extras.getInt("Monat");
        }

        this.listView = (GridView) findViewById(R.id.gridview);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        quotes = databaseAccess.getRezepte(Value);
        id_list = databaseAccess.getAllRezepteID(Value);
        listView.setAdapter(new MyAdapter(this, quotes));
        enable = databaseAccess.getAllRezepteEnabled();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int to_Search;
                to_Search = (int) id_list.get(arg2);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("_id", to_Search);


                if(enable.get(to_Search-1).equals("true")){
                    Intent intent = new Intent(getApplicationContext(),Beitrag.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
                } else if(enable.get(to_Search-1).equals("false")){

                    Intent intent = new Intent(getApplicationContext(),Popup_FreischaltungRezept.class);
                    intent.putExtra("id", to_Search);
                    startActivity(intent);
                }


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






}
