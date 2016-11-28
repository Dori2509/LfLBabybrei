package com.example.doreen.lfl_babybrei.beitraege;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.R;
import com.example.doreen.lfl_babybrei.db.DBHelper;
import com.example.doreen.lfl_babybrei.db.DatabaseAccess;

/**
 * Created by Doreen on 18.11.2016.
 */

public class Beitrag extends AppCompatActivity {
    Toolbar toolbar;
    private DBHelper mydb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);
        initToolBar();

        TextView article_title = (TextView) findViewById(R.id.article_title);
        TextView article_text = (TextView) findViewById(R.id.article_text);
        ImageView article_img = (ImageView) findViewById(R.id.article_img);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {

            int Value = extras.getInt("_id");
            article_title.setText(databaseAccess.getArticleTitle(Value));
            article_text.setText(databaseAccess.getArticleText(Value));
            article_img.setImageResource(databaseAccess.getArticleImage(Value));
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
