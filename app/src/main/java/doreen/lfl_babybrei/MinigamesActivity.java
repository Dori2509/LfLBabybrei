package doreen.lfl_babybrei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import doreen.lfl_babybrei.db.DBHelper;
import doreen.lfl_babybrei.games.tictactoe.TicTacToe;

/**
 * Created by Doreen on 26.10.2016.
 */
public class MinigamesActivity extends AppCompatActivity {
    /**
     * The Toolbar.
     */
    private Toolbar toolbar;
    /**
     * The Tic.
     */
    private Button tic;
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
        setContentView(R.layout.minigames);
        initToolBar();

        ImageView tictactoe = (ImageView) findViewById(R.id.tictactoe);
        tictactoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(getApplicationContext(), TicTacToe.class);
                startActivity(intent);
            }
        });

        ImageView close = (ImageView) findViewById(R.id.closeMinigames);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
            }
        });

        ImageView memory = (ImageView) findViewById(R.id.memory);
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getApplicationContext(), "Memory ist noch in der Entwicklung", Toast.LENGTH_LONG).show();
            }
        });

        ImageView fooddrop = (ImageView) findViewById(R.id.fooddrop);
        fooddrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getApplicationContext(), "Fooddrop ist noch in der Entwicklung", Toast.LENGTH_LONG).show();
            }
        });

        ImageView quiz = (ImageView) findViewById(R.id.quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getApplicationContext(), "Das Quiz ist noch in der Entwicklung", Toast.LENGTH_LONG).show();
            }
        });
        ImageView simonsays = (ImageView) findViewById(R.id.simonSays);
        simonsays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getApplicationContext(), "SimonSays ist noch in der Entwicklung", Toast.LENGTH_LONG).show();
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
