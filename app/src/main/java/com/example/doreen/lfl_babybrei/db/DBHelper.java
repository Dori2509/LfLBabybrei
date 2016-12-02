package com.example.doreen.lfl_babybrei.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Doreen on 09.11.2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BABYNAME = "babyname";
    public static final String COLUMN_BIRTHDATE = "birthdate";
    public static final String COLUMN_DIAMANTS = "diamants";
    public static final String COLUMN_IMAGE = "image";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // Datenbank wird erstellt.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table profile " +
                        "(id integer primary key, " +
                        "name text, " +
                        "babyname text, " +
                        "birthdate text, " +
                        "diamants integer, " +
                        "image text)"
        );
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS profile");
        onCreate(db);
    }

    //alle Daten auslesen.
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile where id=" + id , null);
        return res;
    }

    public String getName() {
        String array_list = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile" , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list = res.getString(res.getColumnIndex(COLUMN_NAME));
            res.moveToNext();
        }
        return array_list;
    }

    public String getBabyName() {
        String array_list = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile" , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list = res.getString(res.getColumnIndex(COLUMN_BABYNAME));
            res.moveToNext();
        }
        return array_list;
    }

    public int getDiamants() {
        int array_list = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile" , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list = res.getInt(res.getColumnIndex(COLUMN_DIAMANTS));
            res.moveToNext();
        }
        return array_list;
    }

    public boolean updateDiamants(int dia)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DIAMANTS, dia);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    public String getBirthday() {
        String array_list = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile" , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list = res.getString(res.getColumnIndex(COLUMN_BIRTHDATE));
            res.moveToNext();
        }
        return array_list;
    }

    public boolean updateBirthday(String bi)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BIRTHDATE, bi);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    public boolean updateName(String bi)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, bi);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    public boolean updateBabyName(String bi)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BABYNAME, bi);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    public boolean updateImage(String bi)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IMAGE, bi);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    public boolean insertProfile(String name, String babyname, String birthdate, int diamants, String image) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("babyname", babyname);
        contentValues.put("birthdate", birthdate);
        contentValues.put("diamants", diamants);
        contentValues.put("image", image);
        db.insert("profile", null, contentValues);
        return true;
    }


    public String getImage() {
        String array_list = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile" , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list = res.getString(res.getColumnIndex(COLUMN_IMAGE));
            res.moveToNext();
        }
        return array_list;
    }

}