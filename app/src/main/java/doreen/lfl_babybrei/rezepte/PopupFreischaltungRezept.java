package doreen.lfl_babybrei.rezepte;

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
public class PopupFreischaltungRezept extends Activity {

    /**
     * The Abbrechen.
     */
    private Button abbrechen;
    /**
     * The Freischalten.
     */
    private Button freischalten;
    /**
     * The Id.
     */
    private int id;
    /**
     *
     */
    private DBHelper mydb;

    /**
     *
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

        getWindow().setLayout((int) (width * .8), (int) (height * .4));

        abbrechen = (Button) findViewById(R.id.Abbrechen);
        freischalten = (Button) findViewById(R.id.Freischalten);
        TextView hinweis = (TextView) findViewById(R.id.hinweis);

        int di = mydb.getDiamants();
        if (di == 0) {
            hinweis.setText("Du hast zu wenig Diamanten. Versuch dein Glück bei den Minigames oder sammel Diamanten im Kochbuch.");
            freischalten.setEnabled(false);
        } else {
            hinweis.setText("Das Rezept ist gesperrt. Für 5 Diamanten kannst du diesen fertig.");
        }

        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupFreischaltungRezept.this.finish();
            }
        });


        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");

        freischalten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                databaseAccess.updateRezepteEnable(id, "true");

                int diamants = mydb.getDiamants();
                diamants = diamants - 5;
                mydb.updateDiamants(diamants);

                PopupFreischaltungRezept.this.finish();

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("_id", id);
                Intent intent = new Intent(getApplicationContext(), RezeptActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }
}
