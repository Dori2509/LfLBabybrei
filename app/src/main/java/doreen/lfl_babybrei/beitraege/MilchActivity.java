package doreen.lfl_babybrei.beitraege;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import doreen.lfl_babybrei.MainMenuActivity;
import doreen.lfl_babybrei.MyAdapter;
import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doreen on 26.10.2016.
 */
public class MilchActivity extends AppCompatActivity {
    /**
     * Die Toolbar.
     */
    private Toolbar toolbar;
    /**
     * Datenbankhilfe.
     */
    private DBHelper mydb;
    /**
     *Grundaufbau der Liste.
     */
    private GridView listView;
    /**
     *Liste aller Beiträge.
     */
    private List<MyAdapter.Item> beitraege;
    /**
     *Liste aller freigeschaltenen Beiträge.
     */
    private  List<String> enable;
    /**
     * Liste aller ID´s.
     */
    private ArrayList iDList;
    /**
     * Überschrift.
     */
    private TextView ueberschrift;

    /**
     * Alle Daten und die Ansichten werden aktualisiert bei Aufruf.
     */
    @Override
    public void onRestart() {
        super.onRestart();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        beitraege = databaseAccess.getBeitraege("milch");
        iDList = databaseAccess.getAllID("milch");
        listView.setAdapter(new MyAdapter(this, beitraege));
        enable = databaseAccess.getAllEnabled();
        initToolBar();
    }

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beitrag);
        initToolBar();

        this.listView = (GridView) findViewById(R.id.gridview);
        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
            }
        });
        ueberschrift = (TextView) findViewById(R.id.ueberschrift);
        ueberschrift.setText("Milch & Co.");
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        beitraege = databaseAccess.getBeitraege("milch");
        iDList = databaseAccess.getAllID("milch");
        listView.setAdapter(new MyAdapter(this, beitraege));
        enable = databaseAccess.getAllEnabled();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> arg0, final View arg1, final int arg2, final long arg3) {
                int toSearch;
                toSearch = (int) iDList.get(arg2);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("_id", toSearch);
                //angeklickter Item wird überprüft, ob es freigeschalten wurde.
                if (enable.get(toSearch - 1).equals("true")) {
                    Intent intent = new Intent(getApplicationContext(), Beitrag.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
                } else if (enable.get(toSearch - 1).equals("false")) {
                    //wenn Beitrag nicht freigeschalten ist, dann wird das Freischaltungsfenster angezeigt.
                    Intent intent = new Intent(getApplicationContext(), PopupFreischaltungBeitrag.class);
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
