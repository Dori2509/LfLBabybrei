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
        System.out.println(array_list);
        return array_list;
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

    /*//neuen Kontakt einfügen.
    public boolean insertNote(String note, String description, String date, String time, String priority, String finished, String contact, String phone, String mail) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("note", note);
        contentValues.put("description", description);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("priority", priority);
        contentValues.put("finished", finished);
        contentValues.put("contact", contact);
        contentValues.put("phone", phone);
        contentValues.put("mail", mail);
        db.insert("notes", null, contentValues);
        return true;
    }



    //Änderungen eines Note werden abgespeichert.
    public boolean updateNote(Integer id, String note, String description, String date, String time, String priority, String finished, String contact, String phone, String mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("note", note);
        contentValues.put("description", description);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("priority", priority);
        contentValues.put("finished", finished);
        contentValues.put("phone", phone);
        contentValues.put("mail", mail);
        contentValues.put("contact", contact);
        db.update("notes", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }


    //Änderungen eines erledigten/nicht-erledigten Note abspeichern.
    public boolean updateFinished(Integer id, String finished) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("finished", finished);
        db.update("notes", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }


    //Löschen eines Note.
    public Integer deleteNote(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("notes",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    //Alle Note-Namen werden ausgelesen.
    public ArrayList<String> getAllNotes(String orderby) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from notes ORDER BY " + orderby, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NOTE)));
            res.moveToNext();
        }
        return array_list;
    }*/

}