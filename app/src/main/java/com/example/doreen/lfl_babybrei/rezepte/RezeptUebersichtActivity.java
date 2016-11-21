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
import com.example.doreen.lfl_babybrei.games.tictactoe.MonatRechner;

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

        MonatRechner m = new MonatRechner(mydb.getBirthday());
        long alter = m.getAlter();

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

        ImageView sieben = (ImageView) findViewById(R.id.sieben);
        sieben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),RezeptMonat.class);
                intent2.putExtra("Monat", 7);
                startActivity(intent2);
            }
        });

        ImageView acht = (ImageView) findViewById(R.id.acht);
        acht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),RezeptMonat.class);
                intent2.putExtra("Monat", 8);
                startActivity(intent2);
            }
        });

        ImageView neun = (ImageView) findViewById(R.id.neun);
        neun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),RezeptMonat.class);
                intent2.putExtra("Monat", 9);
                startActivity(intent2);
            }
        });
        ImageView zehn = (ImageView) findViewById(R.id.zehn);
        zehn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),RezeptMonat.class);
                intent2.putExtra("Monat", 10);
                startActivity(intent2);
            }
        });
        ImageView einJahr = (ImageView) findViewById(R.id.einJahr);
        einJahr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),RezeptMonat.class);
                intent2.putExtra("Monat", 1);
                startActivity(intent2);
            }
        });

        sechs.setEnabled(false);
        sechs.setImageResource(R.drawable.sechs_enabled);
        sieben.setEnabled(false);
        sieben.setImageResource(R.drawable.sieben_enabled);
        acht.setEnabled(false);
        acht.setImageResource(R.drawable.acht_enabled);
        neun.setEnabled(false);
        neun.setImageResource(R.drawable.neun_enabled);
        zehn.setEnabled(false);
        zehn.setImageResource(R.drawable.zehn_enabled);
        einJahr.setEnabled(false);
        einJahr.setImageResource(R.drawable.einjahr_enabled);

        if(alter>=5){
            sechs.setEnabled(true);
            sechs.setImageResource(R.drawable.sechs);
        }
        if(alter>=6){
            sieben.setEnabled(true);
            sieben.setImageResource(R.drawable.sieben);
        }
        if(alter>=7){
            acht.setEnabled(true);
            acht.setImageResource(R.drawable.acht);
        }
        if(alter>=8){
            neun.setEnabled(true);
            neun.setImageResource(R.drawable.neun);
        }
        if(alter>=9){
            zehn.setEnabled(true);
            zehn.setImageResource(R.drawable.zehn);
        }
        if(alter>=11){
            einJahr.setEnabled(true);
            einJahr.setImageResource(R.drawable.einjahr);
        }
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
