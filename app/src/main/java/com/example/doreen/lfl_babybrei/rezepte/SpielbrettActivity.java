package com.example.doreen.lfl_babybrei.rezepte;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;

import com.example.doreen.lfl_babybrei.R;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Doreen on 28.11.2016.
 */

public class SpielbrettActivity extends Activity {
    public ArrayList einkaufsZettel;
    private ArrayList gemuese;
    private ArrayList fluessigkeiten;
    private ArrayList fischFleisch;
    private ArrayList sonstiges;

    public String[] flüssigkeitenCheck;
    public String[] gemüseCheck;
    public String[] fischfleischCheck;
    public String[] sonstigesCheck;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spielbrett);


        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        flüssigkeitenCheck = new String[] {
                "etwas Wasser",
                "TL Rapsöl",
                "ml Möhren-oder Apfelsaft",
                "EL Apfelsaft",
                "ml Wasser"
        };

        gemüseCheck = new String[] {
                "g Zucchini",
                "g Kartoffeln",
                "g Spinat",
                "Süßkartoffel",
                "kleiner Kohlrabi",
                "Apfel",
                "Birne",
                "kleine Banane"
        };

        fischfleischCheck = new String[] {
                " ",

        };

        sonstigesCheck = new String[] {
                "EL Dinkelgrieß"
        };



        einkaufsZettel = new ArrayList<>();
        einkaufsZettel = (ArrayList<Zutaten>) getIntent().getSerializableExtra("Liste");


        gemuese = new ArrayList<>();
        fluessigkeiten = new ArrayList<>();
        fischFleisch = new ArrayList<>();
        sonstiges = new ArrayList<>();


        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();
        //TODO
        // Einsortierung in Cluster -> in einem Thread mit Progressbar
        // Info Popup
        // Hilfebutton auf Spielbrett
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
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
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            mProgressDialog.dismiss();
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
}
