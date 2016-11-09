package com.example.doreen.lfl_babybrei;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.db.DBHelper;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar;
    TextView label;
    Handler handler = new Handler();
    private DBHelper mydb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progBar);

        mydb = new DBHelper(this);

        new Thread(new Runnable() {

            int i = 0;
            int progressStatus = 0;

            public void run() {
                while (progressStatus < 100) {
                    progressStatus += doWork();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        public void run() {
                            bar.setProgress(progressStatus);
                            i++;
                        }
                    });
                }
                if(progressStatus >= 100){
                    Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                    startActivity(intent);
                }
            }

            private int doWork() {

                return i * 3;
            }

        }).start();

    }
}
