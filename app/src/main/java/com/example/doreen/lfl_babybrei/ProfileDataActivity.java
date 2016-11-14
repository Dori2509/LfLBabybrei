package com.example.doreen.lfl_babybrei;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Doreen on 12.11.2016.
 */

public class ProfileDataActivity extends Activity {

    Button saveButton;
    EditText Name;
    EditText BabyName;
    EditText Birthday;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledata);

        Name = (EditText) findViewById(R.id.nameEingabe);
        BabyName = (EditText) findViewById(R.id.babynameEingabe);
        Birthday = (EditText) findViewById(R.id.birthdayEingabe);
        saveButton = (Button) findViewById(R.id.saveButton);

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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ProfileData Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
