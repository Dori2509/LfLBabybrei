package doreen.lfl_babybrei;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import doreen.lfl_babybrei.beitraege.BeikostActivity;
import doreen.lfl_babybrei.beitraege.InfoboxActivity;
import doreen.lfl_babybrei.beitraege.MilchActivity;
import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.rezepte.KochbuchAcitivity;
import doreen.lfl_babybrei.rezepte.RezeptUebersichtActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Doreen on 24.10.2016.
 */
public class MainMenuActivity extends AppCompatActivity {

    /**
     * The Toolbar.
     */
    private Toolbar toolbar;
    /**
     *
     */
    private DBHelper mydb;
    /**
     * The Menupuenkte.
     */
// Array of strings storing country names
    private String[] menupuenkte = new String[] {
            "Profil",
            "Milch & Co.",
            "Beikost",
            "Infobox",
            "Rezeptübersicht",
            "Kochbuch",
            "Wochenrechner",
            "Minigames",
            "Umfrage",
            "Impressum"
    };

    /**
     * The Menubilder.
     */
// Array of integers points to images stored in /res/drawable-ldpi/
    private int[] menubilder = new int[]{
            R.drawable.profil,
            R.drawable.milch,
            R.drawable.beikost,
            R.drawable.infobox,
            R.drawable.rezepte,
            R.drawable.kochbuch,
            R.drawable.wochenrechner,
            R.drawable.minigames,
            R.drawable.umfrage,
            R.drawable.impressum
    };

    /**
     * The Untertitel.
     */
// Array of strings to store currencies
    private String[] untertitel = new String[]{
            "bearbeiten, löschen",
            "nützliche Hinweise, versch. Fertignahrung etc.",
            "nützliche Hinweise, Obst- und Gemüsekunde",
            "Rückrufe, Ernährungsplan, Küchengeflüster",
            "Finde hier Rezepte je nach Alter abgestimmt",
            "Deine Lieblingsrezepte in der Übersicht",
            "Berechne das Alter in Tagen, Wochen usw. einer beliebigen Person",
            "TicTacToe, Memory, FoodDrop, Quiz, SimonSays",
            "Helft uns und gibt uns über diese Umfrage eine Feedback",
            ""
    };

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        initToolBar();

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < untertitel.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("txt", menupuenkte[i]);
            hm.put("cur", untertitel[i]);
            hm.put("flag", Integer.toString(menubilder[i]));
            aList.add(hm);
        }

        String[] from = {"flag", "txt", "cur"};

        int[] to = {R.id.flag, R.id.txt, R.id.cur};

        // Initialisierung eines Adapters um die Elemente zu "füllen"/ erstellen
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) findViewById(R.id.listview);


        // Setting the adapter to the listView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> arg0, final View arg1, final int arg2, final long arg3) {
                switch (arg2) {
                    case 0:

                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getApplicationContext(), MilchActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(getApplicationContext(), BeikostActivity.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(getApplicationContext(), InfoboxActivity.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(getApplicationContext(), RezeptUebersichtActivity.class);
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent(getApplicationContext(), KochbuchAcitivity.class);
                        startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7 = new Intent(getApplicationContext(), WochenrechnerActivity.class);
                        startActivity(intent7);
                        break;
                    case 7:
                        Intent intent8 = new Intent(getApplicationContext(), MinigamesActivity.class);
                        startActivity(intent8);
                        break;
                    case 8:
                        Uri uri = Uri.parse("https://www.survio.com/survey/d/E7J3Y8K7T7Q6O4V1W");
                        Intent intent9 = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent9);
                        break;
                    case 9:
                        Intent intenta = new Intent(getApplicationContext(), ImpressumActivity.class);
                        startActivity(intenta);
                        break;
                    default:

                }
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
