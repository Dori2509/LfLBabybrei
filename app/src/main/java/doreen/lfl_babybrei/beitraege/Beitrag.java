package doreen.lfl_babybrei.beitraege;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.db.DatabaseAccess;

/**
 * Created by Doreen on 18.11.2016.
 */
public class Beitrag extends AppCompatActivity {
    /**
     * Die Toolbar.
     */
    private Toolbar toolbar;
    /**
     * Datenbankhilfe.
     */
    private DBHelper mydb;

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * Einzelner Beitrag.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);
        initToolBar();

        TextView articleTitle = (TextView) findViewById(R.id.article_title);
        TextView articleText = (TextView) findViewById(R.id.article_text);
        ImageView articleImg = (ImageView) findViewById(R.id.article_img);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        //Daten werden zugeordnet.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("_id");
            articleTitle.setText(databaseAccess.getArticleTitle(value));
            articleText.setText(databaseAccess.getArticleText(value));
            articleImg.setImageResource(databaseAccess.getArticleImage(value));
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
