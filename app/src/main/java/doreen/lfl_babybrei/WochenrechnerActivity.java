package doreen.lfl_babybrei;

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

import doreen.lfl_babybrei.db.DBHelper;

/**
 * Wochenrechner
 * Created by Doreen on 26.10.2016.
 */
public class WochenrechnerActivity extends AppCompatActivity {
    /**
     * Toolbar.
     */
    private Toolbar toolbar;
    /**
     * Datenbankverbindung
     */
    private DBHelper mydb;
    /**
     * Name.
     */
    private EditText setName;
    /**
     * Datum.
     */
    private EditText datum;
    /**
     * Button
     */
    private Button calculate;

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * @param savedInstanceState Status
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wochenrechner);
        initToolBar();

        setName = (EditText) findViewById(R.id.WochenrechnerName);
        datum = (EditText) findViewById(R.id.WochenrechnerDatum);
        calculate = (Button) findViewById(R.id.calculate);


        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
            }
        });

        setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setName.setText("");
            }
        });

        datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                datum.setText("");
                DateDialog dialog = new DateDialog(v, null, "Wochenrechner");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
            }
        });

        //Eingaben werden an weitere Activity gegeben und Alter genau zu berechnen
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(WochenrechnerActivity.this, WochenrechnerErgebnisActivity.class);
                intent.putExtra("Name", setName.getText().toString());
                intent.putExtra("datum", datum.getText().toString());
                startActivity(intent);
            }
        });
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
