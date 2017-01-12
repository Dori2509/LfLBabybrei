package doreen.lfl_babybrei;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import doreen.lfl_babybrei.db.DBHelper;

/**
 * Created by Doreen on 26.10.2016.
 */
public class ProfileActivity extends AppCompatActivity {
    /**
     * The Toolbar.
     */
    private Toolbar toolbar;
    /**
     * The Img.
     */
    private ImageView img;
    /**
     * The Change name.
     */
    private ImageView changeName;
    /**
     * The Change babyname.
     */
    private ImageView changeBabyname;
    /**
     * The Change birthday.
     */
    private ImageView changeBirthday;
    /**
     * The Context.
     */
    private final Context context = this;
    /**
     *
     */
    private DBHelper mydb;
    /**
     * The babyname.
     */
    private TextView babyname;
    /**
     * The name.
     */
    private TextView name;
    /**
     * The geburtstag.
     */
    private TextView geburtstag;
    /**
     * The alter.
     */
    private TextView alter;

    /**
     *
     */
    @Override
    public void onRestart() {
        super.onRestart();
        name.setText(mydb.getName());
        babyname.setText(mydb.getBabyName());
        geburtstag.setText(mydb.getBirthday());
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        initToolBar();

        img = (ImageView) findViewById(R.id.profileImage);
        //img.setImageDrawable(Drawable.createFromPath(mydb.getImage()));
        /*img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });*/
        changeName = (ImageView) findViewById(R.id.chName);
        changeBabyname = (ImageView) findViewById(R.id.changeNameBaby);
        name = (TextView) findViewById(R.id.username);
        babyname = (TextView) findViewById(R.id.NameKind_ch);
        geburtstag = (TextView) findViewById(R.id.DateKind_ch);
        alter = (TextView) findViewById(R.id.AlterKind);
        name.setText(mydb.getName());
        babyname.setText(mydb.getBabyName());

        geburtstag.setText(mydb.getBirthday());
        geburtstag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                MonatRechner m = new MonatRechner(geburtstag.getText().toString());
                if (m.getAlter() > 1) {
                    alter.setText(mydb.getBabyName() + " ist " + m.getAlter() + " Monate alt.");
                } else {
                    alter.setText(mydb.getBabyName() + " ist " + m.getAlter() + " Monat alt.");
                }
            }
        });
        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.pop, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog, final int id) {
                                        // get user input and set it to result
                                        // edit text
                                        name.setText(userInput.getText());
                                        mydb.updateName(userInput.getText().toString());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog, final int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        changeBabyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.pop, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog, final int id) {
                                        // get user input and set it to result
                                        // edit text
                                        babyname.setText(userInput.getText());
                                        mydb.updateBabyName(userInput.getText().toString());
                                        MonatRechner m = new MonatRechner(mydb.getBirthday());
                                        if (m.getAlter() > 1) {
                                            alter.setText(mydb.getBabyName() + " ist " + m.getAlter() + " Monate alt.");
                                        } else {
                                            alter.setText(mydb.getBabyName() + " ist " + m.getAlter() + " Monat alt.");
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog, final int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });


        MonatRechner m = new MonatRechner(mydb.getBirthday());

        if (m.getAlter() > 1) {
            alter.setText(mydb.getBabyName() + " ist " + m.getAlter() + " Monate alt.");
        } else {
            alter.setText(mydb.getBabyName() + " ist " + m.getAlter() + " Monat alt.");
        }

        changeBabyname = (ImageView) findViewById(R.id.changeNameBaby);
        changeBirthday = (ImageView) findViewById(R.id.changeBirthBaby);
        changeBirthday.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                DateDialog dialog = new DateDialog(geburtstag, mydb, "Profil");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
            }
        });


    }

    /**
     * Init tool bar.
     */
    public void initToolBar() {
        mydb = new DBHelper(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    /**
     * Pick image.
     */
    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap newProfilePic = extras.getParcelable("data");
                Drawable de = new BitmapDrawable(getResources(), newProfilePic);
                Toast.makeText(getApplicationContext(), de.toString(), Toast.LENGTH_LONG).show();
                mydb.updateImage(String.valueOf(de));
                img.setImageDrawable(de);
            }
        }
    }

}
