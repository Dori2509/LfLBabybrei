package com.example.doreen.lfl_babybrei.rezepte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.R;
import com.example.doreen.lfl_babybrei.db.DBHelper;
import com.example.doreen.lfl_babybrei.db.DatabaseAccess;

/**
 * Created by Doreen on 21.11.2016.
 */

public class RezeptActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezept);
        initToolBar();

        TextView article_title = (TextView) findViewById(R.id.rezept_title);
        TextView article_text = (TextView) findViewById(R.id.rezept_text);
        ImageView article_img = (ImageView) findViewById(R.id.rezept_img);
        EditText rezeptPortion = (EditText) findViewById(R.id.portion);
        TextView portionText = (TextView) findViewById(R.id.PortionText);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {

            int Value = extras.getInt("_id");
            article_title.setText(databaseAccess.getRezeptitle(Value));
            article_text.setText(databaseAccess.getRezeptText(Value));
            article_img.setImageResource(databaseAccess.getRezeptImage(Value));
            rezeptPortion.setText(databaseAccess.getRezeptPortion(Value));

            if((Integer.parseInt(databaseAccess.getRezeptPortion(Value)))>1){
                portionText.setText("Portionen");
            }
        }


    }

    public void initToolBar() {
        mydb = new DBHelper(this);
        String Name = mydb.getName();
        int diamants = mydb.getDiamants();
        System.out.println("BeitragDiamants" + diamants);
        TextView ProfileName = (TextView) findViewById(R.id.username);
        TextView Dia = (TextView) findViewById(R.id.points);

        ProfileName.setText(Name);
        Dia.setText(String.valueOf(diamants));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }






}
