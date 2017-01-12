package doreen.lfl_babybrei.games.tictactoe;

/**
 * Spiel Tic Tac Toe.
 * Created by Doreen on 14.11.2016.
 */


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import doreen.lfl_babybrei.MinigamesActivity;
import doreen.lfl_babybrei.R;
import doreen.lfl_babybrei.db.DBHelper;


/**
 * The type Tic tac toe.
 */
public class TicTacToe extends AppCompatActivity {
    /**
     * Logik.
     */
    private TicTacToeModel logik;
    /**
     * Buttons
     */
    private Button buttonsBoard[ ];
    /**
     * Spielstandinfo
     */
    private TextView infoTextView;
    /**
     * Angabe, wer zuerst dran ist mit dem Zug.
     */
    private boolean spielerZuErst = true;
    /**
     * Spielende.
     */
    private boolean gameOver = false;
    /**
     * Toolbar.
     */
    private Toolbar toolbar;
    /**
     * Datenbankverbindung
     */
    private DBHelper mydb;


    /**
     * Initialisierung des Spielbrettes
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);
        initToolBar();

        buttonsBoard = new Button[TicTacToeModel.getspielbrettGroesse()];
        buttonsBoard[0] = (Button) findViewById(R.id.one);
        buttonsBoard[1] = (Button) findViewById(R.id.two);
        buttonsBoard[2] = (Button) findViewById(R.id.three);
        buttonsBoard[3] = (Button) findViewById(R.id.four);
        buttonsBoard[4] = (Button) findViewById(R.id.five);
        buttonsBoard[5] = (Button) findViewById(R.id.six);
        buttonsBoard[6] = (Button) findViewById(R.id.seven);
        buttonsBoard[7] = (Button) findViewById(R.id.eight);
        buttonsBoard[8] = (Button) findViewById(R.id.nine);
        Button newGa = (Button) findViewById(R.id.newGame);
        newGa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getApplicationContext(), TicTacToe.class);
                startActivity(i);
            }
        });

        ImageView close = (ImageView) findViewById(R.id.closeTicTacToe);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getApplicationContext(), MinigamesActivity.class);
                startActivity(i);
            }
        });

        infoTextView = (TextView) findViewById(R.id.information);


        logik = new TicTacToeModel();

        startNewGame();

    }

    /**
     * Neues Spiel wird gestartet.
     */
    private void startNewGame() {
        logik.clearBoard();

        for (int i = 0; i < buttonsBoard.length; i++) {
            buttonsBoard[i].setText("");
            buttonsBoard[i].setEnabled(true);
            buttonsBoard[i].setOnClickListener(new ButtonClickListener(i));
        }

        if (spielerZuErst) {
            infoTextView.setText(R.string.first_human);
            spielerZuErst = false;
        } else {
            infoTextView.setText(R.string.turn_computer);
            int move = logik.getComputerMove();
            setMove(TicTacToeModel.computer, move);
            spielerZuErst = true;
        }
    }

    /**
     * ButtonClickListener
     */
    private class ButtonClickListener implements View.OnClickListener {
        /**
         * Location.
         */
        private int location;

        /**
         * Button ClickListener
         *
         * @param location the location
         */
        ButtonClickListener(final int location) {
            this.location = location;
        }

        /**
         * Vergleich, ob Spiel gewonnen, unentschieden oder verloren.
         * @param view View.
         */
        public void onClick(final View view) {
            if (!gameOver) {
                if (buttonsBoard[location].isEnabled()) {
                    setMove(TicTacToeModel.user, location);

                    int winner = logik.checkForWinner();

                    if (winner == 0) {
                        infoTextView.setText(R.string.turn_computer);
                        int move = logik.getComputerMove();
                        setMove(TicTacToeModel.computer, move);
                        winner = logik.checkForWinner();
                    }

                    if (winner == 0) {
                        infoTextView.setText(R.string.turn_human);
                    } else if (winner == 1) {
                        infoTextView.setText(R.string.result_tie);
                        gameOver = true;
                    } else if (winner == 2) {
                        infoTextView.setText(R.string.result_human_wins);
                        mydb.updateDiamants((mydb.getDiamants() + 5));
                        initToolBar();
                        gameOver = true;
                    } else {
                        infoTextView.setText(R.string.result_android_wins);
                        gameOver = true;
                    }
                }
            }
        }
    }

    /**
     * Spielzug setzen.
     *@param player Spieler.
     * @param location Platz.
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setMove(final char player, final int location) {
        logik.setMove(player, location);
        buttonsBoard[location].setEnabled(false);
        if (player == TicTacToeModel.user) {
            buttonsBoard[location].setBackground(this.getResources().getDrawable(R.drawable.flasche));

        } else {
            buttonsBoard[location].setBackground(this.getResources().getDrawable(R.drawable.schuessel));
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
