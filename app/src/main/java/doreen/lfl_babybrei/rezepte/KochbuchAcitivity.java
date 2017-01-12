package doreen.lfl_babybrei.rezepte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
public class KochbuchAcitivity extends AppCompatActivity {
    /**
     * Toolbar.
     */
    private Toolbar toolbar;
    /**
     * Datenbankverbindung
     */
    private DBHelper mydb;
    /**
     * ListView
     */
    private GridView listView;
    /**
     * Liste der Beiträge
     */
    private List<MyAdapter.Item> beitraege;
    /**
     * ID-Liste
     */
    private ArrayList idList;


    /**
     * Alle Daten und die Ansichten werden aktualisiert bei Aufruf.
     */
    @Override
    public void onRestart() {
        super.onRestart();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        beitraege = databaseAccess.getKochbuchrezepte();
        idList = databaseAccess.getKochbuchRezepteID();
        listView.setAdapter(new MyAdapter(this, beitraege));
        initToolBar();
    }

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * @param savedInstanceState Status
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kochbuch);
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
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        beitraege = databaseAccess.getKochbuchrezepte();
        idList = databaseAccess.getKochbuchRezepteID();
        //Rezepte werden angezeigt
        listView.setAdapter(new MyAdapter(this, beitraege));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> arg0, final View arg1, final int arg2, final long arg3) {
                int toSearch;
                toSearch = (int) idList.get(arg2);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("_id", toSearch);

                Intent intent = new Intent(getApplicationContext(), RezeptActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

        Button createZettel = (Button) findViewById(R.id.createEinkaufszettel);

        // Es wird geprüft, ob überhaupt ein Rezept im Kochbuch abgespeichert wurde
        if (idList.size() == 0) {
            createZettel.setEnabled(false);
            createZettel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Toast.makeText(getApplicationContext(), "Du hast noch kein Rezept zum Kochbuch hinzugefügt.", Toast.LENGTH_LONG).show();
                }
            });

        } else {
            createZettel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Intent i = new Intent(KochbuchAcitivity.this, Einkaufszettel.class);
                    startActivity(i);

                }
            });
        }
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
