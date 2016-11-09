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
    public static final String COLUMN_NAME = "Anna";
    public static final String COLUMN_BABYNAME = "Bruno";
    public static final String COLUMN_BIRTHDATE = "25.09.2016";
    public static final int COLUMN_DIAMANTS = 50;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // Datenbank wird erstellt.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table notes " +
                        "(id integer primary key, " +
                        "name text," +
                        "babyname text," +
                        "birthdate text, " +
                        "diamants integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
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

    //alle Daten auslesen.
    public Cursor getData(int id, String orderby) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from notes where id=" + id + " ORDER BY " + orderby, null);
        return res;
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

    //Änderungen eines normalen/wichten Note abspeichern.
    public boolean updateImportance(Integer id, String imp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("priority", imp);
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
    }

    //Alle Note-Wichtigkeiten werden ausgelesen.
    public ArrayList<String> getAllPriority(String orderby) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from notes ORDER BY " + orderby, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(COLUMN_PRIORITY)));
            res.moveToNext();
        }
        return array_list;
    }

    //Alle Note-Datum(s) werden ausgelesen.
    public ArrayList<String> getAllDate(String orderby) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from notes ORDER BY " + orderby, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(COLUMN_DATE)));
            res.moveToNext();
        }
        return array_list;
    }

    //Alle Note-Erledigt werden ausgelesen.
    public ArrayList<String> getAllFinished(String orderby) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from notes ORDER BY " + orderby, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(COLUMN_FINISHED)));
            res.moveToNext();
        }
        return array_list;
    }

    //Alle Note-IDs werden ausgelesen.
    public ArrayList<Integer> getAllID(String orderby) {
        ArrayList<Integer> array_list = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from notes ORDER BY " + orderby, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getInt(res.getColumnIndex(COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }*/

}