package doreen.lfl_babybrei;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import doreen.lfl_babybrei.db.DBHelper;

/**
 * Profil Dateneingabe
 * Created by Doreen on 12.11.2016.
 */
public class ProfileDataActivity extends Activity {

    /**
     * Savebutton.
     */
    Button saveButton;
    /**
     * Name.
     */
    EditText Name;
    /**
     * Babyname.
     */
    EditText BabyName;
    /**
     * Geburtstag.
     */
    EditText Birthday;
    /**
     * Datenbankverbindung
     */
    DBHelper mydb;

    /**
     * Initialisierung aller notwendigen Daten und der Ansicht.
     * @param savedInstanceState Status
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledata);
        mydb = new DBHelper(this);

        Name = (EditText) findViewById(R.id.nameEingabe);
        BabyName = (EditText) findViewById(R.id.babynameEingabe);
        Birthday = (EditText) findViewById(R.id.birthdayEingabe);
        saveButton = (Button) findViewById(R.id.saveButton);

        //Dateneingabe mit anschließendem Abspeichern in der lokalen Datenbank
       Name.setFocusable(true);
       Name.setClickable(true);
       Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Name.setText("");
            }
        });

        BabyName.setFocusable(true);
        BabyName.setClickable(true);
        BabyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BabyName.setText("");
            }
        });

        Birthday.setFocusable(true);
        Birthday.setClickable(true);
        Birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Birthday.setText("");
                DateDialog dialog=new DateDialog(v, mydb, "Profil");
                FragmentTransaction ft =getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              String NameEin = Name.getText().toString();
                                              String BabyNameEin = BabyName.getText().toString();
                                              String BirthdayEin = Birthday.getText().toString();

                                              Intent BackIntent = new Intent();
                                              BackIntent.putExtra("Name", NameEin);
                                              BackIntent.putExtra("BabyName", BabyNameEin);
                                              BackIntent.putExtra("Birthday", BirthdayEin);
                                              setResult(RESULT_OK, BackIntent);
                                              finish();
                                          }
                                      }
        );
    }
}
