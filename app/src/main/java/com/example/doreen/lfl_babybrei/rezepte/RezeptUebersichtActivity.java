package com.example.doreen.lfl_babybrei.rezepte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.R;
import com.example.doreen.lfl_babybrei.db.DBHelper;

/**
 * Created by Doreen on 26.10.2016.
 */
public class RezeptUebersichtActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezeptuebersicht);
        initToolBar();

        ImageView fuenf = (ImageView) findViewById(R.id.fuenf);
        fuenf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),RezeptMonat.class);
                intent2.putExtra("Monat", 5);
                startActivity(intent2);
            }
        });

        ImageView sechs = (ImageView) findViewById(R.id.sechs);
        sechs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),RezeptMonat.class);
                intent2.putExtra("Monat", 6);
                startActivity(intent2);
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
