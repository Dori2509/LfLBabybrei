package com.example.doreen.lfl_babybrei.rezepte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doreen.lfl_babybrei.R;
import com.example.doreen.lfl_babybrei.db.DBHelper;
import com.example.doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;

/**
 * Created by Doreen on 21.11.2016.
 */

public class RezeptActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;
    private ArrayList<Zutaten> productList;
    public int Value;
    public long xMenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezept);
        initToolBar();

        TextView article_title = (TextView) findViewById(R.id.rezept_title);
        TextView article_text = (TextView) findViewById(R.id.rezept_text);
        ImageView article_img = (ImageView) findViewById(R.id.rezept_img);
        final EditText rezeptPortion = (EditText) findViewById(R.id.portion);
        final TextView portionText = (TextView) findViewById(R.id.PortionText);
        final ImageView refresh_img = (ImageView) findViewById(R.id.refresh);
        final Button addKoch = (Button) findViewById(R.id.addkochbuch);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {

            Value = extras.getInt("_id");
            article_title.setText(databaseAccess.getRezeptitle(Value));
            article_text.setText(databaseAccess.getRezeptText(Value));
            article_img.setImageResource(databaseAccess.getRezeptImage(Value));
            rezeptPortion.setText(databaseAccess.getRezeptPortion(Value));

            if((Long.parseLong(databaseAccess.getRezeptPortion(Value)))>1){
                portionText.setText("Portionen");
            }

            xMenge = Long.parseLong(databaseAccess.getRezeptPortion(Value));

            productList = new ArrayList<Zutaten>();
            productList = databaseAccess.getZutaten(Value);
            ListView lview = (ListView) findViewById(R.id.listview);
            listviewAdapter adapter = new listviewAdapter(this, productList);
            lview.setAdapter(adapter);





            adapter.notifyDataSetChanged();


        }

        refresh_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int anzahl = Integer.parseInt(rezeptPortion.getText().toString());
                databaseAccess.updatePortion(Value, anzahl);


                ArrayList<Long> mengen = databaseAccess.getMengen(Value);

                int a = 0;
                int f = 0;
                while (a < mengen.size())
                {
                    f = (int) ((mengen.get(a)/xMenge)*anzahl);
                    databaseAccess.updateMenge(Value, a+1, f);
                    a++;
                }
                xMenge = anzahl;
                productList = new ArrayList<Zutaten>();
                productList = databaseAccess.getZutaten(Value);
                ListView lview = (ListView) findViewById(R.id.listview);
                listviewAdapter adapter = new listviewAdapter(RezeptActivity.this, productList);
                lview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });

        if(databaseAccess.getKochbuch(Value).equals("false")){
            addKoch.setText("zum Kochbuch hinzufügen");
            addKoch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseAccess.updateKochbuch(Value, "true");
                    addKoch.setText("vom Kochbuch löschen");
                }
            });
        }else if(databaseAccess.getKochbuch(Value).equals("true")){
            addKoch.setText("vom Kochbuch löschen");
            addKoch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseAccess.updateKochbuch(Value, "false");
                    addKoch.setText("zum Kochbuch hinzufügen");
                }
            });
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
