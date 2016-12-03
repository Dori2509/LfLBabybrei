package com.example.doreen.lfl_babybrei;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.db.DBHelper;

/**
 * Created by Doreen on 26.10.2016.
 */
public class WochenrechnerActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;
    EditText SetName;
    EditText Datum;
    Button calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wochenrechner);
        initToolBar();

        SetName = (EditText) findViewById(R.id.WochenrechnerName);
        Datum = (EditText) findViewById(R.id.WochenrechnerDatum);
        calculate = (Button) findViewById(R.id.calculate);


        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainMenuActivity.class);
                startActivity(i);
            }
        });

        SetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetName.setText("");
            }
        });

        Datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datum.setText("");
                DateDialog dialog=new DateDialog(v, null, "Wochenrechner");
                FragmentTransaction ft =getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WochenrechnerActivity.this,WochenrechnerErgebnisActivity.class);
                intent.putExtra("Name", SetName.getText().toString());
                intent.putExtra("Datum", Datum.getText().toString());
                startActivity(intent);
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
