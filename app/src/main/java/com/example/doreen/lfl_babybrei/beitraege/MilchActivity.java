package com.example.doreen.lfl_babybrei.beitraege;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.MainMenuActivity;
import com.example.doreen.lfl_babybrei.MyAdapter;
import com.example.doreen.lfl_babybrei.R;
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
    private  List<String> enable;
    public  ArrayList id_list;
    public TextView ueberschrift;

    @Override
    public void onRestart() {
        super.onRestart();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        quotes = databaseAccess.getBeitraege("milch");
        id_list = databaseAccess.getAllID("milch");
        listView.setAdapter(new MyAdapter(this, quotes));
        enable = databaseAccess.getAllEnabled();
        initToolBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beitrag);
        initToolBar();

        this.listView = (GridView) findViewById(R.id.gridview);
        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainMenuActivity.class);
                startActivity(i);
            }
        });
        ueberschrift = (TextView)findViewById(R.id.ueberschrift);
        ueberschrift.setText("Milch & Co.");
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        quotes = databaseAccess.getBeitraege("milch");
        id_list = databaseAccess.getAllID("milch");
        listView.setAdapter(new MyAdapter(this, quotes));
        enable = databaseAccess.getAllEnabled();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                int to_Search;
                to_Search = (int) id_list.get(arg2);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("_id", to_Search);

                if(enable.get(to_Search-1).equals("true")){
                    Intent intent = new Intent(getApplicationContext(),Beitrag.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
                } else if(enable.get(to_Search-1).equals("false")){

                    Intent intent = new Intent(getApplicationContext(),Popup_FreischaltungBeitrag.class);
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
