package doreen.lfl_babybrei.rezepte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import doreen.lfl_babybrei.MainMenuActivity;
import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.MonatRechner;

/**
 * Rezeptübersicht
 * Created by Doreen on 26.10.2016.
 */
public class RezeptUebersichtActivity extends AppCompatActivity {
    /**
     * Toolbar.
     */
    private Toolbar toolbar;
    /**
     * Datenbankverbindung
     */
    private DBHelper mydb;

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * @param savedInstanceState Status
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezeptuebersicht);
        initToolBar();

        //Alter des Kindes wird berechnet um maximal die Rezepte freizuschalten,
        //die für das Alter geeignet sind
        MonatRechner m = new MonatRechner(mydb.getBirthday());
        long alter = m.getAlter();

        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
            }
        });

        ImageView fuenf = (ImageView) findViewById(R.id.fuenf);
        fuenf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent2 = new Intent(getApplicationContext(), RezeptMonat.class);
                intent2.putExtra("Monat", 5);
                startActivity(intent2);
            }
        });

        ImageView sechs = (ImageView) findViewById(R.id.sechs);
        sechs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent2 = new Intent(getApplicationContext(), RezeptMonat.class);
                intent2.putExtra("Monat", 6);
                startActivity(intent2);
            }
        });

        ImageView sieben = (ImageView) findViewById(R.id.sieben);
        sieben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent2 = new Intent(getApplicationContext(), RezeptMonat.class);
                intent2.putExtra("Monat", 7);
                startActivity(intent2);
            }
        });

        ImageView acht = (ImageView) findViewById(R.id.acht);
        acht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent2 = new Intent(getApplicationContext(), RezeptMonat.class);
                intent2.putExtra("Monat", 8);
                startActivity(intent2);
            }
        });

        ImageView neun = (ImageView) findViewById(R.id.neun);
        neun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent2 = new Intent(getApplicationContext(), RezeptMonat.class);
                intent2.putExtra("Monat", 9);
                startActivity(intent2);
            }
        });
        ImageView zehn = (ImageView) findViewById(R.id.zehn);
        zehn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent2 = new Intent(getApplicationContext(), RezeptMonat.class);
                intent2.putExtra("Monat", 10);
                startActivity(intent2);
            }
        });
        ImageView einJahr = (ImageView) findViewById(R.id.einJahr);
        einJahr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent2 = new Intent(getApplicationContext(), RezeptMonat.class);
                intent2.putExtra("Monat", 12);
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

        //Freischaltung je nach Alter
        if (alter >= 5) {
            sechs.setEnabled(true);
            sechs.setImageResource(R.drawable.sechs);
        }
        if (alter >= 6) {
            sieben.setEnabled(true);
            sieben.setImageResource(R.drawable.sieben);
        }
        if (alter >= 7) {
            acht.setEnabled(true);
            acht.setImageResource(R.drawable.acht);
        }
        if (alter >= 8) {
            neun.setEnabled(true);
            neun.setImageResource(R.drawable.neun);
        }
        if (alter >= 9) {
            zehn.setEnabled(true);
            zehn.setImageResource(R.drawable.zehn);
        }
        if (alter >= 11) {
            einJahr.setEnabled(true);
            einJahr.setImageResource(R.drawable.einjahr);
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
