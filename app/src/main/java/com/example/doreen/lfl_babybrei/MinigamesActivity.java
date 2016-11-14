package com.example.doreen.lfl_babybrei;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.games.tictactoe.TicTacToe;

import java.io.File;

/**
 * Created by Doreen on 26.10.2016.
 */
public class MinigamesActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button tic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigames);
        initToolBar();

        Button tictactoe = (Button) findViewById(R.id.tictactoe);
        tictactoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicTacToe.class);
                startActivity(intent);
            }
        });


    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }






}
