package doreen.lfl_babybrei;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import doreen.lfl_babybrei.db.DBHelper;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Bar.
     */
    private ProgressBar bar;
    /**
     * The Label.
     */
    private TextView label;
    /**
     * The Handler.
     */
    private Handler handler = new Handler();
    /**
     *
     */
    private DBHelper mydb;
    /**
     * The Name.
     */
    private String name;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progBar);



        mydb = new DBHelper(this);
        name = mydb.getName();


        new Thread(new Runnable() {

            private int i = 0;
            private int progressStatus = 0;

            public void run() {
                while (progressStatus < 100) {
                    progressStatus += doWork();
                    try {
                        Thread.sleep(300);
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

                if (progressStatus >= 100) {
                    if (name == "") {
                        Intent intent = new Intent(getApplicationContext(), ProfileDataActivity.class);
                        startActivityForResult(intent, 1);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                        startActivity(intent);
                    }
                }

                 }

            private int doWork() {

                return i * 3;
            }

        }).start();



    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

        if (resultCode == RESULT_OK) {
            name = data.getStringExtra("Name");
            String babyName = data.getStringExtra("BabyName");
            String birthday = data.getStringExtra("Birthday");
            mydb.insertProfile(name, babyName, birthday, 25, "R.drawable.profil_xl");
            MonatRechner m = new MonatRechner(birthday);
            Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
            startActivity(intent);
        }

    }
}
