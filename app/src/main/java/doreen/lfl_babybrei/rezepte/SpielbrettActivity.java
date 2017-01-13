package doreen.lfl_babybrei.rezepte;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import doreen.lfl_babybrei.MainMenuActivity;
import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.db.DBHelper;

import android.os.AsyncTask;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;


/**
 * Spielbrett des Kochbuches
 * Created by Doreen on 28.11.2016.
 */
public class SpielbrettActivity extends Activity {
    /**
     * Einkaufszettel.
     */
    public ArrayList einkaufsZettel;
    /**
     * Liste aller möglichen Gemüsesorten
     */
    private ArrayList gemuese;
    /**
     * Liste aller möglichen Flüssigkeiten
     */
    private ArrayList fluessigkeiten;
    /**
     * Liste aller möglichen Fleischsorten
     */
    private ArrayList fischFleisch;
    /**
     * Liste der restlichen Zutaten
     */
    private ArrayList sonstiges;

    /**
     * Flüssigkeitencheck.
     */
    public String[] flüssigkeitenCheck;
    /**
     * Gemüsecheck.
     */
    public String[] gemüseCheck;
    /**
     * Fischfleischcheck.
     */
    public String[] fischfleischCheck;
    /**
     * Sonstigescheck.
     */
    public String[] sonstigesCheck;
    /**
     * ProgressDialog
     */
    private ProgressDialog mProgressDialog;
    /**
     * Bubble 1 1.
     */
    ImageView bubble1_1, /**
     * Bubble 1 2.
     */
    bubble1_2, /**
     * Bubble 1 3.
     */
    bubble1_3, /**
     * Bubble 2 1.
     */
    bubble2_1, /**
     *Bubble 2 2.
     */
    bubble2_2, /**
     * Bubble 2 3.
     */
    bubble2_3, /**
     * Bubble 3 1.
     */
    bubble3_1, /**
     * Bubble 3 2.
     */
    bubble3_2, /**
     * Bubble 3 3.
     */
    bubble3_3, /**
     * Bubble 4 1.
     */
    bubble4_1, /**
     * Bubble 4 2.
     */
    bubble4_2, /**
     * Bubble 4 3.
     */
    bubble4_3;
    /**
     * Getingredients.
     */
    static int GET_INGREDIENTS;
    /**
     * Datenbankverbindung
     */
    private DBHelper mydb ;
    /**
     * Reihe 1.
     */
    public Integer[] Reihe1, /**
     * Reihe 2.
     */
    Reihe2, /**
     * Reihe 3.
     */
    Reihe3, /**
     * Reihe 4.
     */
    Reihe4;

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * @param savedInstanceState Status
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spielbrett);
        onCoachMark();
        mydb = new DBHelper(this);
        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainMenuActivity.class);
                startActivity(i);
            }
        });
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //Die Zutaten werden in 3 Reihen zu jeweils 3 Bereichen aufgeteilt
        Reihe1 = new Integer[3];
        Reihe2 = new Integer[3];
        Reihe3 = new Integer[3];
        Reihe4 = new Integer[3];

        int za =0;
        while(za<3){
            Reihe1[za] = 0;
            Reihe2[za] = 0;
            Reihe3[za] = 0;
            Reihe4[za] = 0;
            za++;
        }


        bubble1_1 = (ImageView) findViewById(R.id.bubble_1_1);
        bubble1_2 = (ImageView) findViewById(R.id.bubble_1_2);
        bubble1_3 = (ImageView) findViewById(R.id.bubble_1_3);
        bubble2_1 = (ImageView) findViewById(R.id.bubble_2_1);
        bubble2_2 = (ImageView) findViewById(R.id.bubble_2_2);
        bubble2_3 = (ImageView) findViewById(R.id.bubble_2_3);
        bubble3_1 = (ImageView) findViewById(R.id.bubble_3_1);
        bubble3_2 = (ImageView) findViewById(R.id.bubble_3_2);
        bubble3_3 = (ImageView) findViewById(R.id.bubble_3_3);
        bubble4_1 = (ImageView) findViewById(R.id.bubble_4_1);
        bubble4_2 = (ImageView) findViewById(R.id.bubble_4_2);
        bubble4_3 = (ImageView) findViewById(R.id.bubble_4_3);

        bubble2_1.setEnabled(false);
        bubble2_2.setEnabled(false);
        bubble2_3.setEnabled(false);
        bubble3_1.setEnabled(false);
        bubble3_2.setEnabled(false);
        bubble3_3.setEnabled(false);
        bubble4_1.setEnabled(false);
        bubble4_2.setEnabled(false);
        bubble4_3.setEnabled(false);


        flüssigkeitenCheck = new String[] {
                "etwas Wasser", "EL Möhrensaft",
                "TL Rapsöl", "EL Orangensaft", "ml Apfeldirektsaft",
                "ml Möhren-oder Apfelsaft", "Wasser", "EL Wasser",
                "EL Apfelsaft", "ml Möhren-Direktsaft", "ml Milch",
                "ml Wasser", "ml Vollmilch oder Milch-Wasser-Gemisch",
                "l Gemüsebrühe", "EL Maiskeimöl", "ml Traubensaft","ml Vollmilch",
                "ml Gemüsebrühe", "ml warmes Wasser", "etwas Apfelsaft",
                "EL Apfelsaft", "ml Öl", "ml Mineralwasser", "g heller Sirup oder Ahornsirup",
                "ml Olivenöl", "TL Gemüsebrühe","l Milch", "etwas Zitronensaft",
                "ml Schlagsahne"
        };

        gemüseCheck = new String[] {
                "g Zucchini", "Gurke", "gehackte Kräuter", "g Banane", "Bananen",
                "g Kartoffeln", "g grüne Bohnen", "Birne", "Kartoffeln",
                "g Spinat", "g Süßkartoffeln", "g Romanesco", "g Knollensellerie",
                "Süßkartoffel", "EL Apfelmark/Apfelmus", "g Steckrübe",
                "kleiner Kohlrabi", "g Rote Bete", "Aprikosen", "g Zucchini",
                "Apfel", "g Äpfel", "g Apfel", "Möhren", "g Melone",
                "Birne", "kleiner Apfel", "Banane", "g Blumenkohl",
                "kleine Banane", "kleine Fenchelknolle", "g Möhren",
                "Blätter Basilikum", "Pfirsiche", "Stange Lauch",
                "etwas Petersilie", "Tomate", "Stange Rhabarber",
                "g geschälte, geriebene Karotte", "g Brokkoli",
                "g Kohlrabi", "Tomaten", "Pflaumen", "Orange",
                "g Heidelbeeren", "g Apfelmus", "g Tomaten", "g Tomatenmark",
                "g Früchte", "Paprika", "g Rucola", "g Cherrytomaten", "Stiele Basilikum",
                "kleine Zwiebel", "Obst je nach Wunsch"
        };

        fischfleischCheck = new String[] {
                "g mageres Fleisch", "g Hackfleisch", "g Tafelspitz",
                "g Hühnchen", "g Lachsfilet", "kleine Dose Thunfisch"

        };

        sonstigesCheck = new String[] {
                "EL Dinkelgrieß", "g Goldhirse", "g Reisflocken", "g Grieß",
                "g Maisgrieß", "g Vollkorn-Dinkelmehl", "g Joghurt", "g Couscous",
                "Scheiben Vollkornzwieback", "g Bulgur", "g Grünkerne", "g saure Sahne",
                "g Vollkornreis", "EL Zucker", "Blätter Gelatine", "g Nudeln",
                "g geriebener Käse", "Eier", "EL Mehl", "ml Sahne", "g Buchstabennudeln",
                "g Stärke", "g Kakao", "g Rohrzucker", "g Dinkelmehl", "Ei",
                "TL Butter", "EL Creme Fraiche", "EL Kräuter", "Pck. Trockenhefe",
                "TL Salz", "g Frischkäse gemischt mit frischen Kräutern",
                "Eigelb", "g weiche Butter", "EL Früchtetee (BIO)", "TL Süßholz", "g Zucker",
                "etwas Vanillemark", "Prise Salz", "g frische Hefe", "g Mehl",
                "TL Backpulver", "g geschmolzene Butter", "etwas Sesam",
                "EL Kakao", "TL Natron", "TL Zimt", "TL gemahlene Nelken",
                "TL Kardamom", "TL Zucker", "TL Oregano", "g Spaghetti",
                "EL klein gestückeltes Brot", "TL Thymian", "etwas geriebene Zitronenschale",
                "g zarte Haferflocken", "g Butter", "TL Creme Fraiche",
                "g Vollkornspaghetti", "g geriebener Parmesan","Meersalz, Paprikapulver edelsüß, schwarzer Pfeffer",
                "Salz, schwarzer Pfeffer", "EL Paniermehl", "EL gehackte Petersilie",
                "g weiße Schokolade", "EL getrocknete Sauerkirschen", "g gehackte Mandeln", "Salz",
                "g Magerquark", "EL Honig", "TL Lebkuchen-Gewürz", "EL Semmelbrösel",
                "g gemahlene Mandeln", "Pck. Backpulver", "g Hirseflocken", "g Haferflocken",
                "g Honigmelonenmus"
        };



        einkaufsZettel = new ArrayList<>();
        einkaufsZettel = (ArrayList<Zutaten>) getIntent().getSerializableExtra("Liste");


        gemuese = new ArrayList<>();
        fluessigkeiten = new ArrayList<>();
        fischFleisch = new ArrayList<>();
        sonstiges = new ArrayList<>();


        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();
    }

    /**
     *
     */
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;

        /**
         * Zutaten werden aufgeteilt
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping...");
            try {

                int z = 0;
                while(z<einkaufsZettel.size()){
                    Zutaten h = (Zutaten) einkaufsZettel.get(z);

                    int i = 0;
                    while(i<gemüseCheck.length){
                        if(h.getZutat().equals(gemüseCheck[i])){
                            gemuese.add(new Zutaten(h.getMenge(), h.getZutat()));
                        }
                        i++;
                    }


                    int j = 0;
                    while(j<fischfleischCheck.length){
                        if(h.getZutat().equals(fischfleischCheck[j])){
                            fischFleisch.add(new Zutaten(h.getMenge(), h.getZutat()));
                        }
                        j++;
                    }

                    int k = 0;
                    while(k<flüssigkeitenCheck.length){
                        if(h.getZutat().equals(flüssigkeitenCheck[k])){
                            fluessigkeiten.add(new Zutaten(h.getMenge(), h.getZutat()));
                        }
                        k++;
                    }

                    int l = 0;
                    while(l<sonstigesCheck.length){
                        if(h.getZutat().equals(sonstigesCheck[l])){
                            sonstiges.add(new Zutaten(h.getMenge(), h.getZutat()));
                        }
                        l++;
                    }

                    z++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        /*
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            mProgressDialog.dismiss();
            // wenn im Cluster kein Element ist, dann Bubbles als erledigt anzeigen
            if(gemuese.size()==0){
                bubble1_1.setImageResource(R.drawable.glas_17_2);
                bubble1_2.setImageResource(R.drawable.glas_18_2);
                bubble1_3.setImageResource(R.drawable.glas_16_2);
            }

            if(fischFleisch.size()==0){
                bubble2_1.setImageResource(R.drawable.glas_12_2);
                bubble2_2.setImageResource(R.drawable.glas_13_2);
                bubble2_3.setImageResource(R.drawable.glas_14_2);
            }

            if(fluessigkeiten.size()==0){
                bubble3_1.setImageResource(R.drawable.glas_11_2);
                bubble3_2.setImageResource(R.drawable.glas_09_2);
                bubble3_3.setImageResource(R.drawable.glas_10_2);
            }
            if(sonstiges.size()==0){
                bubble4_1.setImageResource(R.drawable.glas_05_2);
                bubble4_2.setImageResource(R.drawable.glas_06_2);
                bubble4_3.setImageResource(R.drawable.glas_07_2);
            }

            // wenn im Cluster ein Element ist, dann 2 Bubbles als erledigt anzeigen
            if(gemuese.size()==1){
                Reihe1[0] = 1;
                //Reihe 1, links
                bubble1_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 1;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", gemuese);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }


                });
                //Reihe 1, mitte
                bubble1_2.setImageResource(R.drawable.glas_18_2);
                //Reihe 1, rechts
                bubble1_3.setImageResource(R.drawable.glas_16_2);
            }

            if(fischFleisch.size()==1){
                Reihe2[0] = 1;
                //Reihe 2, links
                bubble2_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 4;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", fischFleisch);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 2, mitte
                bubble2_1.setImageResource(R.drawable.glas_12_2);
                //Reihe 2, rechts
                bubble2_3.setImageResource(R.drawable.glas_14_2);
            }

            if(fluessigkeiten.size()==1){
                //Reihe 3, links
                bubble3_1.setImageResource(R.drawable.glas_11_2);
                //Reihe 3, mitte
                bubble3_2.setImageResource(R.drawable.glas_09_2);
                //Reihe 3, rechts
                bubble3_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 9;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", fluessigkeiten);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                Reihe3[2] = 1;
            }
            if(sonstiges.size()==1){
                Reihe4[1] = 1;
                //Reihe 4, links
                bubble4_1.setImageResource(R.drawable.glas_05_2);
                //Reihe 4, mitte
                bubble4_3.setImageResource(R.drawable.glas_07_2);
                //Reihe 4, rechts
                bubble4_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 11;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", sonstiges);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
            }

            // wenn im Cluster zwei Elemente ist, dann 1 Bubble als erledigt anzeigen
            if(gemuese.size()==2){
                Reihe1[0] = 1;
                Reihe1[2] = 1;
                //Reihe 1, links
                bubble1_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 1;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        ArrayList h = new ArrayList<Zutaten>();
                        h.add(gemuese.get(0));
                        intent.putExtra("Zutaten", h);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 1, mitte
                bubble1_2.setImageResource(R.drawable.glas_18_2);
                //Reihe 1, rechts
                bubble1_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 3;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        ArrayList h = new ArrayList<Zutaten>();
                        h.add(gemuese.get(1));
                        intent.putExtra("Zutaten", h);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
            }

            if(fischFleisch.size()==2){
                Reihe2[0] = 1;
                Reihe2[1] = 1;
                bubble2_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 4;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        ArrayList h = new ArrayList<Zutaten>();
                        h.add(fischFleisch.get(0));
                        intent.putExtra("Zutaten", h);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                bubble2_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 5;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        ArrayList h = new ArrayList<Zutaten>();
                        h.add(fischFleisch.get(1));
                        intent.putExtra("Zutaten", h);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                bubble2_3.setImageResource(R.drawable.glas_14_2);
            }

            if(fluessigkeiten.size()==2){
                Reihe3[1] = 1;
                Reihe3[2] = 1;
                bubble3_1.setImageResource(R.drawable.glas_11_2);
                bubble3_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 8;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        ArrayList h = new ArrayList<Zutaten>();
                        h.add(fluessigkeiten.get(0));
                        intent.putExtra("Zutaten", h);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                bubble3_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 9;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        ArrayList h = new ArrayList<Zutaten>();
                        h.add(fluessigkeiten.get(1));
                        intent.putExtra("Zutaten", h);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
            }
            if(sonstiges.size()==2){
                Reihe4[1] = 1;
                Reihe4[2] = 1;
                bubble4_1.setImageResource(R.drawable.glas_05_2);
                bubble4_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 11;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        ArrayList h = new ArrayList<Zutaten>();
                        h.add(sonstiges.get(0));
                        intent.putExtra("Zutaten", h);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                bubble4_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 12;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        ArrayList h = new ArrayList<Zutaten>();
                        h.add(sonstiges.get(1));
                        intent.putExtra("Zutaten", h);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
            }

            // wenn im Cluster 3 Elemente sind
            if(gemuese.size()>2){
                Reihe1[0] = 1;
                Reihe1[1] = 1;
                Reihe1[2] = 1;
                final ArrayList gemuese1 = new ArrayList<Zutaten>();
                final ArrayList gemuese2 = new ArrayList<Zutaten>();
                final ArrayList gemuese3 = new ArrayList<Zutaten>();
                int laufvariable = 1;
                int l = 0;
                while(l<gemuese.size()){
                    if(laufvariable==1){
                        gemuese1.add(gemuese.get(l));
                        l++;
                        laufvariable++;
                    }
                    else if(laufvariable==2){
                        gemuese2.add(gemuese.get(l));
                        l++;
                        laufvariable++;
                    }
                    else if(laufvariable==3){
                        gemuese3.add(gemuese.get(l));
                        l++;
                        laufvariable=1;

                    }

                }

                //Reihe 1, links
                bubble1_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 1;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", gemuese1);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 1, mitte
                bubble1_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 2;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", gemuese2);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 1, rechts
                bubble1_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 3;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", gemuese3);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
            }

            if(fischFleisch.size()>2){
                Reihe2[0] = 1;
                Reihe2[1] = 1;
                Reihe2[2] = 1;
                final ArrayList fischFleisch1 = new ArrayList<Zutaten>();
                final ArrayList fischFleisch2 = new ArrayList<Zutaten>();
                final ArrayList fischFleisch3 = new ArrayList<Zutaten>();
                int laufvariable = 1;
                int l = 0;
                while(l<fischFleisch.size()){
                    if(laufvariable==1){
                        fischFleisch1.add(fischFleisch.get(l));
                        l++;
                        laufvariable++;
                    }
                    else if(laufvariable==2){
                        fischFleisch2.add(fischFleisch.get(l));
                        l++;
                        laufvariable++;
                    }
                    else if(laufvariable==3){
                        fischFleisch3.add(fischFleisch.get(l));
                        l++;
                        laufvariable=1;

                    }

                }

                //Reihe 1, links
                bubble2_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 4;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", fischFleisch1);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 1, mitte
                bubble2_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 5;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", fischFleisch2);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 1, rechts
                bubble2_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 6;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", fischFleisch3);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
            }

            if(fluessigkeiten.size()>2){
                Reihe3[0] = 1;
                Reihe3[1] = 1;
                Reihe3[2] = 1;
                final ArrayList fluessigkeiten1 = new ArrayList<Zutaten>();
                final ArrayList fluessigkeiten2 = new ArrayList<Zutaten>();
                final ArrayList fluessigkeiten3 = new ArrayList<Zutaten>();
                int laufvariable = 1;
                int l = 0;
                while(l<fluessigkeiten.size()){
                    if(laufvariable==1){
                        fluessigkeiten1.add(fluessigkeiten.get(l));
                        l++;
                        laufvariable++;
                    }
                    else if(laufvariable==2){
                        fluessigkeiten2.add(fluessigkeiten.get(l));
                        l++;
                        laufvariable++;
                    }
                    else if(laufvariable==3){
                        fluessigkeiten3.add(fluessigkeiten.get(l));
                        l++;
                        laufvariable=1;

                    }

                }

                //Reihe 1, links
                bubble3_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 7;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", fluessigkeiten1);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 1, mitte
                bubble3_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 8;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", fluessigkeiten2);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 1, rechts
                bubble3_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 9;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", fluessigkeiten3);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });

            }
            if(sonstiges.size()>2){
                Reihe4[0] = 1;
                Reihe4[1] = 1;
                Reihe4[2] = 1;
                final ArrayList sonstiges1 = new ArrayList<Zutaten>();
                final ArrayList sonstiges2 = new ArrayList<Zutaten>();
                final ArrayList sonstiges3 = new ArrayList<Zutaten>();
                int laufvariable = 1;
                int l = 0;
                while(l<sonstiges.size()){
                    if(laufvariable==1){
                        sonstiges1.add(sonstiges.get(l));
                        l++;
                        laufvariable++;
                    }
                    else if(laufvariable==2){
                        sonstiges2.add(sonstiges.get(l));
                        l++;
                        laufvariable++;
                    }
                    else if(laufvariable==3){
                        sonstiges3.add(sonstiges.get(l));
                        l++;
                        laufvariable=1;

                    }

                }

                //Reihe 1, links
                bubble4_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 10;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", sonstiges1);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 1, mitte
                bubble4_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 11;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", sonstiges2);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
                //Reihe 1, rechts
                bubble4_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GET_INGREDIENTS = 12;
                        Intent intent = new Intent(getApplicationContext(),Popup_ZutatenCheck.class);
                        intent.putExtra("Zutaten", sonstiges3);
                        startActivityForResult(intent, GET_INGREDIENTS);
                    }
                });
            }

        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            mProgressDialog.show();
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(String... text) {

        }

    }

    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Bubbles werden gesetzt
        //Reihe 1, links
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Reihe1[0] = 0;
                bubble1_1.setImageResource(R.drawable.glas_17_2);
                bubble1_1.setEnabled(false);
                reDrawSecond();
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Reihe1[1] = 0;
                bubble1_2.setImageResource(R.drawable.glas_18_2);
                bubble1_2.setEnabled(false);
                reDrawSecond();
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                Reihe1[2] = 0;
                bubble1_3.setImageResource(R.drawable.glas_16_2);
                bubble1_3.setEnabled(false);
                reDrawSecond();
            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                Reihe2[0] = 0;
                bubble2_1.setImageResource(R.drawable.glas_12_2);
                bubble2_1.setEnabled(false);
                reDrawThird();
            }
        }

        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                Reihe2[1] = 0;
                bubble2_2.setImageResource(R.drawable.glas_13_2);
                bubble2_2.setEnabled(false);
                reDrawThird();
            }
        }

        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                Reihe2[2] = 0;
                bubble2_3.setImageResource(R.drawable.glas_14_2);
                bubble2_3.setEnabled(false);
                reDrawThird();
            }
        }

        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                Reihe3[0] = 0;
                bubble3_1.setImageResource(R.drawable.glas_11_2);
                bubble3_1.setEnabled(false);
                reDrawFourth();
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                Reihe3[1] = 0;
                bubble3_2.setImageResource(R.drawable.glas_09_2);
                bubble3_2.setEnabled(false);
                reDrawFourth();
            }
        }

        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                Reihe3[2] = 0;
                bubble3_3.setImageResource(R.drawable.glas_10_2);
                bubble3_3.setEnabled(false);
                reDrawFourth();
            }
        }

        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                Reihe4[0] = 0;
                bubble4_1.setImageResource(R.drawable.glas_05_2);
                bubble4_1.setEnabled(false);
                win();
            }
        }

        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                Reihe4[1] = 0;
                bubble4_2.setImageResource(R.drawable.glas_06_2);
                bubble4_2.setEnabled(false);
                win();
            }
        }

        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                Reihe4[2] = 0;
                bubble4_3.setImageResource(R.drawable.glas_07_2);
                bubble4_3.setEnabled(false);
                win();
            }
        }
    }

    /**
     * Redrawsecond.
     */
    public void reDrawSecond(){
           if(Reihe1[0]==0 && Reihe1[1]==0 && Reihe1[2]==0){
               bubble2_1.setEnabled(true);
               bubble2_2.setEnabled(true);
               bubble2_2.setEnabled(true);

               if(fischFleisch.size()==0){
                   reDrawThird();
               }
           }

    }

    /**
     * Redrawthird.
     */
    public void reDrawThird(){
        if(Reihe2[0]==0 && Reihe2[1]==0 && Reihe2[2]==0){
            bubble3_1.setEnabled(true);
            bubble3_2.setEnabled(true);
            bubble3_3.setEnabled(true);

            bubble2_1.setImageResource(R.drawable.glas_12_3);
            bubble2_2.setImageResource(R.drawable.glas_13_3);
            bubble2_3.setImageResource(R.drawable.glas_14_3);

            LinearLayout lin = (LinearLayout) findViewById(R.id.linelayout);
            lin.setBackgroundColor(Color.parseColor("#80368a"));
            if(fluessigkeiten.size()==0){
                reDrawFourth();
            }
        }

    }

    /**
     * Re draw fourth.
     */
    public void reDrawFourth(){
        if(Reihe3[0]==0 && Reihe3[1]==0 && Reihe3[2]==0){
            bubble4_1.setEnabled(true);
            bubble4_2.setEnabled(true);
            bubble4_3.setEnabled(true);

            bubble3_1.setImageResource(R.drawable.glas_11_3);
            bubble3_2.setImageResource(R.drawable.glas_09_3);
            bubble3_3.setImageResource(R.drawable.glas_10_3);

            if(sonstiges.size()==0){
                win();
            }
        }

    }

    /**
     * Win.
     */
    public void win(){
        if(Reihe4[0]==0 && Reihe4[1]==0 && Reihe4[2]==0){
            bubble4_1.setImageResource(R.drawable.glas_05_3);
            bubble4_2.setImageResource(R.drawable.glas_06_3);
            bubble4_3.setImageResource(R.drawable.glas_07_3);

            onWonDiamants();
        }
    }

    /**
     * Anleitung anzeigen
     */
    public void onCoachMark(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.coach_mark);
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

    /**
     * Wenn gewonnen, dann bekommt Nutzer Diamanten und Hauptmenü wird wieder angezeigt
     */
    public void onWonDiamants(){
        mydb.updateDiamants((mydb.getDiamants()+15));
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.wonthegame);
        dialog.setCanceledOnTouchOutside(true);
        View masterView = dialog.findViewById(R.id.coach_mark_master);
        masterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
