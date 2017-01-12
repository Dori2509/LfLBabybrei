package doreen.lfl_babybrei.rezepte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import doreen.lfl_babybrei.MyAdapter;
import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doreen on 19.11.2016.
 */
public class RezeptMonat extends AppCompatActivity {
    /**
     * The Toolbar.
     */
    private Toolbar toolbar;
    /**
     *
     */
    private DBHelper mydb;
    /**
     *
     */
    private GridView listView;
    /**
     *
     */
    private List<MyAdapter.Item> quotes;
    /**
     * The Id list.
     */
    private ArrayList idList;
    /**
     *
     */
    private int value;
    /**
     *
     */
    private  List<String> enable;
    /**
     * The Ueberschrift.
     */
    private TextView ueberschrift;


    /**
     *
     */
    @Override
    public void onRestart() {
        super.onRestart();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getInt("Monat");
        }
        quotes = databaseAccess.getRezepte(value);
        idList = databaseAccess.getAllRezepteID(value);
        listView.setAdapter(new MyAdapter(this, quotes));
        enable = databaseAccess.getAllRezepteEnabled();
        initToolBar();
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beitrag);
        initToolBar();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getInt("Monat");
        }

        this.listView = (GridView) findViewById(R.id.gridview);
        ueberschrift = (TextView) findViewById(R.id.ueberschrift);
        ueberschrift.setText(value + ". Monat");
        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getApplicationContext(), RezeptUebersichtActivity.class);
                startActivity(i);
            }
        });
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        quotes = databaseAccess.getRezepte(value);
        idList = databaseAccess.getAllRezepteID(value);
        listView.setAdapter(new MyAdapter(this, quotes));
        enable = databaseAccess.getAllRezepteEnabled();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> arg0, final View arg1, final int arg2, final long arg3) {
                int toSearch;
                toSearch = (int) idList.get(arg2);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("_id", toSearch);


                if (enable.get(toSearch - 1).equals("true")) {
                    Intent intent = new Intent(getApplicationContext(), RezeptActivity.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
                } else if (enable.get(toSearch - 1).equals("false")) {
                    Intent intent = new Intent(getApplicationContext(), PopupFreischaltungRezept.class);
                    intent.putExtra("id", toSearch);
                    startActivity(intent);
                }


            }
        });
        databaseAccess.close();

    }


    /**
     * Init tool bar.
     */
    public void initToolBar() {
        mydb = new DBHelper(this);
        String name = mydb.getName();
        int diamants = mydb.getDiamants();

        TextView profileName = (TextView) findViewById(R.id.username);
        TextView dia = (TextView) findViewById(R.id.points);

        profileName.setText(name);
        dia.setText(String.valueOf(diamants));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }






}
