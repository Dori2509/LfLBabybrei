package com.example.doreen.lfl_babybrei.games.tictactoe;

/**
 * Created by Doreen on 14.11.2016.
 */


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.MinigamesActivity;
import com.example.doreen.lfl_babybrei.R;
import com.example.doreen.lfl_babybrei.beitraege.Beitrag;
import com.example.doreen.lfl_babybrei.db.DBHelper;


public class TicTacToe extends AppCompatActivity {

    private TicTacToeModel mGame;
    private Button mBoardButtons[];
    private TextView mInfoTextView;
    private TextView mHumanCount;
    private TextView mTieCount;
    private TextView mAndroidCount;
    private boolean mHumanFirst = true;
    private boolean mGameOver = false;
    Toolbar toolbar;
    private DBHelper mydb ;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);
        initToolBar();

        mBoardButtons = new Button[mGame.getBOARD_SIZE()];
        mBoardButtons[0] = (Button) findViewById(R.id.one);
        mBoardButtons[1] = (Button) findViewById(R.id.two);
        mBoardButtons[2] = (Button) findViewById(R.id.three);
        mBoardButtons[3] = (Button) findViewById(R.id.four);
        mBoardButtons[4] = (Button) findViewById(R.id.five);
        mBoardButtons[5] = (Button) findViewById(R.id.six);
        mBoardButtons[6] = (Button) findViewById(R.id.seven);
        mBoardButtons[7] = (Button) findViewById(R.id.eight);
        mBoardButtons[8] = (Button) findViewById(R.id.nine);
        Button newGa = (Button) findViewById(R.id.newGame);
        newGa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TicTacToe.class);
                startActivity(i);
            }
        });

        ImageView close = (ImageView) findViewById(R.id.closeTicTacToe);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MinigamesActivity.class);
                startActivity(i);
            }
        });

        mInfoTextView = (TextView) findViewById(R.id.information);


        mGame = new TicTacToeModel();

        startNewGame();

    }

    private void startNewGame() {
        mGame.clearBoard();

        for (int i = 0; i < mBoardButtons.length; i++) {
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
        }

        if (mHumanFirst) {
            mInfoTextView.setText(R.string.first_human);
            mHumanFirst = false;
        } else {
            mInfoTextView.setText(R.string.turn_computer);
            int move = mGame.getComputerMove();
            setMove(mGame.ANDROID_PLAYER, move);
            mHumanFirst = true;
        }
    }

    private class ButtonClickListener implements View.OnClickListener {
        int location;

        public ButtonClickListener(int location) {
            this.location = location;
        }

        public void onClick(View view) {
            if (!mGameOver) {
                if (mBoardButtons[location].isEnabled()) {
                    setMove(mGame.HUMAN_PLAYER, location);

                    int winner = mGame.checkForWinner();

                    if (winner == 0) {
                        mInfoTextView.setText(R.string.turn_computer);
                        int move = mGame.getComputerMove();
                        setMove(mGame.ANDROID_PLAYER, move);
                        winner = mGame.checkForWinner();
                    }

                    if (winner == 0)
                        mInfoTextView.setText(R.string.turn_human);
                    else if (winner == 1) {
                        mInfoTextView.setText(R.string.result_tie);
                        mGameOver = true;
                    } else if (winner == 2) {
                        mInfoTextView.setText(R.string.result_human_wins);
                        mydb.updateDiamants((mydb.getDiamants()+5));
                        initToolBar();
                        mGameOver = true;
                    } else {
                        mInfoTextView.setText(R.string.result_android_wins);
                        mGameOver = true;
                    }
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setMove(char player, int location) {
        mGame.setMove(player, location);
        mBoardButtons[location].setEnabled(false);
        if (player == mGame.HUMAN_PLAYER) {
            mBoardButtons[location].setBackground(this.getResources().getDrawable(R.drawable.flasche));

        } else {
            mBoardButtons[location].setBackground(this.getResources().getDrawable(R.drawable.schuessel));
        }
    }

    public void initToolBar() {
        mydb = new DBHelper(this);
        String Name = mydb.getName();
        int diamants = mydb.getDiamants();

        TextView ProfileName = (TextView) findViewById(R.id.username);
        TextView Dia = (TextView) findViewById(R.id.points);

        ProfileName.setText(Name);
        Dia.setText(String.valueOf(diamants));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

}