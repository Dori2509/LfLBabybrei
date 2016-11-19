package com.example.doreen.lfl_babybrei;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.db.DBHelper;
import com.example.doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doreen on 26.10.2016.
 */
public class MilchActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;
    private GridView listView;
    private List<MyAdapter.Item> quotes;
    public  ArrayList id_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beitrag);
        initToolBar();

        this.listView = (GridView) findViewById(R.id.gridview);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        quotes = databaseAccess.getBeitraege("milch");
        id_list = databaseAccess.getAllID("milch");
        listView.setAdapter(new MyAdapter(this, quotes));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                int to_Search;
                to_Search = (int) id_list.get(arg2);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("_id", to_Search);
                Intent intent = new Intent(getApplicationContext(),Beitrag.class);
                intent.putExtras(dataBundle);
                startActivity(intent);

            }
        });
        databaseAccess.close();

       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        //this.listView.setAdapter(adapter);
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
