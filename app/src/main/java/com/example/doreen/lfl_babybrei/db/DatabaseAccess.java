package com.example.doreen.lfl_babybrei.db;

/**
 * Created by Doreen on 15.11.2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.doreen.lfl_babybrei.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    public Context c;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
        this.c = context;
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }



    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<MyAdapter.Item> getBeitraege(String value) {
        List<MyAdapter.Item> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM beitraege WHERE assignement='" + value + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int resID = c.getResources().getIdentifier(cursor.getString(4) , "drawable", c.getPackageName());
            String str = cursor.getString(1);
            if(str.length() > 22)
                str = str.substring(0,18) + "...";
            list.add(new MyAdapter.Item(str, resID, cursor.getInt(3), cursor.getString(5)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<Integer> getAllID(String value) {
        ArrayList<Integer> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM beitraege WHERE assignement='" + value + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getInt(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getAllEnabled() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM beitraege", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(5));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public String getArticleTitle(int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from beitraege WHERE _id=" + id , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            title = res.getString(res.getColumnIndex("title"));
            res.moveToNext();
        }
        return title;

    }

    public List<MyAdapter.Item> getRezepte(int value) {
        List<MyAdapter.Item> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM rezepte WHERE month='" + value + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int resID = c.getResources().getIdentifier(cursor.getString(4) , "drawable", c.getPackageName());
            String str = cursor.getString(1);
            if(str.length() > 22)
                str = str.substring(0,18) + "...";
            list.add(new MyAdapter.Item(str, resID, cursor.getInt(3), cursor.getString(9)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<Integer> getAllRezepteID(int value) {
        ArrayList<Integer> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM rezepte WHERE month='" + value + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getInt(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getAllRezepteEnabled() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM rezepte", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(9));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public boolean updateRezepteEnable(Integer id, String en)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("enable", en);
        database.update("rezepte", contentValues, "_id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateEnable(Integer id, String en)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("enable", en);
        database.update("beitraege", contentValues, "_id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


    public String getArticleText(int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from beitraege WHERE _id=" + id , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            title = res.getString(res.getColumnIndex("fulltext"));
            res.moveToNext();
        }
        return title;

    }
    public int getArticleImage(int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from beitraege WHERE _id=" + id , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            title = res.getString(res.getColumnIndex("image"));
            res.moveToNext();
        }
        int resID = c.getResources().getIdentifier(title , "drawable", c.getPackageName());
        return resID;

    }

    public int getArticleRate(int id) {
        int title = 0;
        Cursor res = database.rawQuery("select * from beitraege WHERE _id=" + id , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            title= res.getColumnIndex("rate");
            res.moveToNext();
        }
        return title;

    }

    public String getRezeptitle(int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            title = res.getString(res.getColumnIndex("title"));
            res.moveToNext();
        }
        return title;

    }

    public String getRezeptText(int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            title = res.getString(res.getColumnIndex("description"));
            res.moveToNext();
        }
        return title;

    }
    public int getRezeptImage(int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            title = res.getString(res.getColumnIndex("image"));
            res.moveToNext();
        }
        int resID = c.getResources().getIdentifier(title , "drawable", c.getPackageName());
        return resID;

    }

    public String getRezeptPortion(int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            title = res.getString(res.getColumnIndex("portion"));
            res.moveToNext();
        }
        return title;

    }

    public int getRezeptRate(int id) {
        int title = 0;
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id , null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            title= res.getColumnIndex("rate");
            res.moveToNext();
        }
        return title;

    }


}
