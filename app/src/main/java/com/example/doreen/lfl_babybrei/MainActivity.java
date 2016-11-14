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
    String Name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progBar);

        mydb = new DBHelper(this);
        Name = mydb.getName();


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
                    if (Name == ""){
                        System.out.println("Name ist leer");
                        Intent intent = new Intent(getApplicationContext(), ProfileDataActivity.class);
                        startActivityForResult(intent, 1);
                    } else{
                        Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                        startActivity(intent);
                    }
                }

                 }

            private int doWork() {

                return i * 3;
            }

        }).start();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            Name = data.getStringExtra("Name");
            String BabyName = data.getStringExtra("BabyName");
            String Birthday = data.getStringExtra("Birthday");
            System.out.println("Bernd:" + Name);
            mydb.insertProfile(Name, BabyName, Birthday, 25, null);

            Intent intent = new Intent(getApplicationContext(),MainMenu.class);
            startActivity(intent);
        }

    }
}
