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
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Doreen on 26.10.2016.
 */
public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView img;
    ImageView changeName;
    ImageView changeBabyname;
    ImageView changeBirthday;

    TextView Babyname;
    TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        initToolBar();

        img = (ImageView) findViewById(R.id.profileImage);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //img.setImageResource(R.drawable.infobox);
                pickImage();
            }
        });
        changeName = (ImageView) findViewById(R.id.chName);
        Name = (TextView) findViewById(R.id.username);
        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name.setText("Hallo");
            }
        });


        changeBabyname = (ImageView) findViewById(R.id.changeNameBaby);
        changeBirthday = (ImageView) findViewById(R.id.changeBirthBaby);
        changeBirthday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DateDialog dialog=new DateDialog(v);
                FragmentTransaction ft =getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
            }
        });
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }



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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap newProfilePic = extras.getParcelable("data");
                Drawable de = new BitmapDrawable(getResources(), newProfilePic);
                img.setImageDrawable(de);
            }
        }
    }

}
