package doreen.lfl_babybrei.rezepte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.beitraege.Beitrag;
import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.db.DatabaseAccess;

import java.util.ArrayList;

/**
 * Created by Doreen on 02.12.2016.
 */
public class Popup_ZutatenCheck extends Activity {

    /**
     * The Abbrechen.
     */
    Button abbrechen;
    /**
     * The Fertig.
     */
    Button fertig;
    private ArrayList ingredients;
    /**
     * The Lv.
     */
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zutatencheck);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));

        lv = (ListView) findViewById(R.id.listeIngredients);

        abbrechen = (Button) findViewById(R.id.Abbrechen);
        fertig = (Button) findViewById(R.id.Freischalten);



        Bundle extras = getIntent().getExtras();
        ingredients = (ArrayList<Zutaten>) getIntent().getSerializableExtra("Zutaten");

        int anzahl = ingredients.size();

            ArrayList fin = new ArrayList<Zutaten>();
            int z = 0;
            while (z<anzahl){
                fin.add(ingredients.get(z));
                z++;
            }
            final SpielbrettAdapter adapter = new SpielbrettAdapter(this, fin);
            lv.setAdapter(adapter);
            final CheckBoxClick cbC = new CheckBoxClick(fin.size());
            lv.setOnItemClickListener(cbC);





        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popup_ZutatenCheck.this.finish();
            }
        });


        fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String alles = "true";
                ArrayList n = new ArrayList<Integer>();
                n = cbC.getCheckedList();
                int u = 0;
                while (u<n.size()){

                    if (n.get(u).equals(0)){
                        alles = "false";
                    }
                    u++;
                }
                if(alles.equals("true")){
                    System.out.println("fertsch");
                    //TODO
                    //Idee in Spielbrett
                    Intent BackIntent = new Intent();
                    BackIntent.putExtra("ActivityResult", "fertig");
                    setResult(RESULT_OK,BackIntent);


                }


                Popup_ZutatenCheck.this.finish();


            }
        });
    }
}
