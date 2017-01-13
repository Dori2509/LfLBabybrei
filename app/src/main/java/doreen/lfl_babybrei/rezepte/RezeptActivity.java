package doreen.lfl_babybrei.rezepte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;

/**
 * Anzeige des Rezeptes
 * Created by Doreen on 21.11.2016.
 */
public class RezeptActivity extends AppCompatActivity {
    /**
     * Toolbar.
     */
    private Toolbar toolbar;
    /**
     * Datenbankverbindung
     */
    private DBHelper mydb;
    /**
     * Zutatenliste
     */
    private ArrayList<Zutaten> productList;
    /**
     * value.
     */
    private int value;
    /**
     * Menge pro Zutat
     */
    private long xMenge;

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * @param savedInstanceState Status
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezept);
        initToolBar();

        TextView articleTitle = (TextView) findViewById(R.id.rezept_title);
        TextView articleText = (TextView) findViewById(R.id.rezept_text);
        ImageView articleImg = (ImageView) findViewById(R.id.rezept_img);
        final EditText rezeptPortion = (EditText) findViewById(R.id.portion);
        final TextView portionText = (TextView) findViewById(R.id.PortionText);
        final ImageView refreshImg = (ImageView) findViewById(R.id.refresh);
        final Button addKoch = (Button) findViewById(R.id.addkochbuch);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        //sämtliche Daten werden aus Datenbank ausgelesen und zu den einzelnen Bestandteilen zugeordnet
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getInt("_id");
            articleTitle.setText(databaseAccess.getRezeptitle(value));
            articleText.setText(databaseAccess.getRezeptText(value));
            articleImg.setImageResource(databaseAccess.getRezeptImage(value));
            rezeptPortion.setText(databaseAccess.getRezeptPortion(value));

            if ((Long.parseLong(databaseAccess.getRezeptPortion(value))) > 1) {
                portionText.setText("Portionen");
            }

            xMenge = Long.parseLong(databaseAccess.getRezeptPortion(value));

            productList = new ArrayList<Zutaten>();
            productList = databaseAccess.getZutaten(value);
            ListView lview = (ListView) findViewById(R.id.listview);
            ListviewAdapter adapter = new ListviewAdapter(this, productList);
            lview.setAdapter(adapter);

            adapter.notifyDataSetChanged();


        }

        //Portionenänderung durch Eingabe des Nutzers
        refreshImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int anzahl = Integer.parseInt(rezeptPortion.getText().toString());
                databaseAccess.updatePortion(value, anzahl);

                ArrayList<Long> mengen = databaseAccess.getMengen(value);

                int a = 0;
                int f = 0;
                while (a < mengen.size()) {
                    f = (int) ((mengen.get(a) / xMenge) * anzahl);
                    databaseAccess.updateMenge(value, a + 1, f);
                    a++;
                }
                xMenge = anzahl;
                productList = new ArrayList<Zutaten>();
                productList = databaseAccess.getZutaten(value);
                ListView lview = (ListView) findViewById(R.id.listview);
                ListviewAdapter adapter = new ListviewAdapter(RezeptActivity.this, productList);
                lview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });

        //Button zum Hinzufügen oder Löschen eines Rezeptes zum Kochbuch
        if (databaseAccess.getKochbuch(value).equals("false")) {
            addKoch.setText("zum Kochbuch hinzufügen");
            addKoch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    databaseAccess.updateKochbuch(value, "true");
                    addKoch.setText("vom Kochbuch löschen");
                }
            });
        } else if (databaseAccess.getKochbuch(value).equals("true")) {
            addKoch.setText("vom Kochbuch löschen");
            addKoch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    databaseAccess.updateKochbuch(value, "false");
                    addKoch.setText("zum Kochbuch hinzufügen");
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
