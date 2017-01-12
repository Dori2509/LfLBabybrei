package doreen.lfl_babybrei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import doreen.lfl_babybrei.db.DBHelper;


/**
 * Created by Doreen on 05.12.2016.
 */
public class ImpressumActivity extends AppCompatActivity {
    /**
     * The Toolbar.
     */
    private Toolbar toolbar;
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
        setContentView(R.layout.impressum);
        initToolBar();
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
