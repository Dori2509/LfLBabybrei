package doreen.lfl_babybrei.beitraege;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.db.DatabaseAccess;

/**
 * Created by Doreen on 21.11.2016.
 */
public class PopupFreischaltungBeitrag extends Activity {

    /**
     * Button zum  Abbrechen.
     */
    private Button abbrechen;
    /**
     * Button zum Freischalten.
     */
    private Button freischalten;
    /**
     * ID.
     */
    private int id;
    /**
     * Datenbankhilfe.
     */
    private DBHelper mydb;

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        mydb = new DBHelper(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //Größe des Fensters definieren
        getWindow().setLayout((int) (width * .8), (int) (height * .4));
        //Initialisierung der einzelnen Bestandteile
        abbrechen = (Button) findViewById(R.id.Abbrechen);
        freischalten = (Button) findViewById(R.id.Freischalten);
        TextView hinweis = (TextView) findViewById(R.id.hinweis);

        //Vergleich, ob überhaupt genügend Diamanten zum Freischalten vorhanden sind
        int di = mydb.getDiamants();
        if (di == 0) {
            hinweis.setText("Du hast zu wenig Diamanten. Versuch dein Glück bei den Minigames oder sammel Diamanten im Kochbuch.");
            freischalten.setEnabled(false);
        } else {
            hinweis.setText("Der Beitrag ist gesperrt. Für 5 Diamanten kannst du diesen freischalten.");
        }

        //Fenster wird geschlossen, wenn "abbrechen" gedrückt wird
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupFreischaltungBeitrag.this.finish();
            }
        });


        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");

        //Bei Freischaltung werden 5 Diamanten abgezogen, die Datenbank aktualisiert
        // (Enable-Eigenschaft wird geändert)
        //und der Beitrag wird direkt angezeigt.
        freischalten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                    databaseAccess.updateEnable(id, "true");

                    int diamants = mydb.getDiamants();
                    diamants = diamants - 5;
                    mydb.updateDiamants(diamants);

                    PopupFreischaltungBeitrag.this.finish();

                    Bundle dataBundle = new Bundle();
                    dataBundle.putInt("_id", id);
                    Intent intent = new Intent(getApplicationContext(), Beitrag.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
            }
        });
    }
}
