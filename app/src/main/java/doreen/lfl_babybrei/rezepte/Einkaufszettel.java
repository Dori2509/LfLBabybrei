package doreen.lfl_babybrei.rezepte;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import doreen.lfl_babybrei.MainMenuActivity;
import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;

/**
 * Einkaufszettel
 * Created by Doreen on 27.11.2016.
 */
public class Einkaufszettel extends AppCompatActivity {
    /**
     * Toolbar.
     */
    private Toolbar toolbar;
    /**
     * Datenbankverbindung
     */
    private DBHelper mydb;
    /**
     * IDListe
     */
    private ArrayList iDList;
    /**
     * Zutaten, die eingekauft werden müssen
     */
    private ArrayList einkaufszutaten;
    /**
     * Einkaufszettel
     */
    private ArrayList einkaufszettel;
    /**
     * finaler Einkaufszettel
     */
    private ArrayList einkaufszettelFinal;

    /**
     * ListView
     */
    private ListView lv;


    /**
     * Initialisierung des Einkaufszettels.
     * @param savedInstanceState Status
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einkaufszettel);
        lv = (ListView) findViewById(R.id.listeEinkaufszettel);
        initToolBar();
        onLayer();
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        iDList = databaseAccess.getKochbuchRezepteID();

        einkaufszutaten = new ArrayList<Zutaten>();
        einkaufszettel = new ArrayList<Zutaten>();
        einkaufszettelFinal = new ArrayList<Zutaten>();
        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
            }
        });

        int a = 0;
        //Durchlauf durch die Liste der abgespeicherten Rezepte (Kochbuch)
        while (a < iDList.size()) {
            //Zutaten des jeweiligen Rezeptes werden zwischengespeichert
            einkaufszutaten = databaseAccess.getZutatenEinkaufszettel((Integer) iDList.get(a));
            int b = 0;
            //die Zwischengespeicherten Zutaten eines Rezeptes werden durchlaufen
            while (b < einkaufszutaten.size()) {
                Zutaten c = (Zutaten) einkaufszutaten.get(b);
                //wenn noch keine Zutat auf dem Einkaufszettel vorhanden, wird die erste Zutat einfach hinzugefügt
                if (a == 0) {
                    einkaufszettel.add(new Zutaten(c.getMenge(), c.getZutat()));
                } else {
                    int d = 0;
                    long gesamtmenge = c.getMenge();
                    //der Einkaufszettel wird nun durchlaufen, auf der Suche, ob die Zutat bereits darauf steht
                    while (d < einkaufszettel.size()) {
                        Zutaten testZutat = (Zutaten) einkaufszettel.get(d);
                        // wenn die Zutat bereits auf der Liste steht, wird die gesamtmenge von beiden berechnet
                        if (testZutat.getZutat().equals(c.getZutat())) {
                            //falls bereits in Liste vorhanden
                            gesamtmenge = c.getMenge() + ((Zutaten) einkaufszettel.get(d)).getMenge();
                            //erste Abspeicherung wird wieder gelöscht um sie mit der Gesamtmenge neu abzuspeichern
                            einkaufszettel.remove(d);
                        }
                        d++;
                    }
                    //Zutat wird zur Einkaufsliste hinzugefügt
                    einkaufszettel.add(new Zutaten(gesamtmenge, c.getZutat()));
                }
                b++;
            }
            a++;
        }

        final EinkaufszettelAdapter adapter = new EinkaufszettelAdapter(this, einkaufszettel);
        lv.setAdapter(adapter);
        final CheckBoxClick cbC = new CheckBoxClick(einkaufszettel.size());
        lv.setOnItemClickListener(cbC);

        Button zumSpiel = (Button) findViewById(R.id.zumSpiel);
        zumSpiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ArrayList<Integer> c = cbC.getCheckedList();
                int b = 0;
                while (b < c.size()) {
                    if (c.get(b).equals(0)) {
                        Zutaten zu = (Zutaten) einkaufszettel.get(b);
                        //finaler Einkaufszettel wird generiert
                        einkaufszettelFinal.add(new Zutaten(zu.getMenge(), zu.getZutat()));
                    }
                    b++;
                }

                //Spielbrett wird aufgerufen
                Intent intent = new Intent(getApplicationContext(), SpielbrettActivity.class);
                intent.putExtra("Liste", einkaufszettelFinal);
                mydb.close();
                startActivity(intent);
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


    /**
     * Anleitung als Layer
     */
    public void onLayer() {

        final Dialog dialog = new Dialog(this);
        Window window = dialog.getWindow();
        window.setLayout(1300, 900);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.kochbuch_overlay);
        dialog.setCanceledOnTouchOutside(true);
        View masterView = dialog.findViewById(R.id.coach_mark_master_view);
        masterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
