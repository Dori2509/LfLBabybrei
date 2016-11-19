package com.example.doreen.lfl_babybrei;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.db.DBHelper;
import com.example.doreen.lfl_babybrei.db.DatabaseAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doreen on 26.10.2016.
 */
public class BeikostActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;
    private GridView listView;
    private List<MyAdapter.Item> quotes;
    public ArrayList id_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beitrag);
        initToolBar();

        this.listView = (GridView) findViewById(R.id.gridview);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        quotes = databaseAccess.getBeitraege("beikost");
        id_list = databaseAccess.getAllID("beikost");
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
