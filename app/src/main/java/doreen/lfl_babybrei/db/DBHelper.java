package doreen.lfl_babybrei.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Datenbank aktualisieren, erstellen und auslesen.
 * Created by Doreen on 09.11.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * Kontante: Datenbankname
     */
    private static final String DATABASE_NAME = "MyDBName.db";
    /**
     * Kontante: Spaltenname
     */
    private static final String COLUMN_NAME = "name";
    /**
     * Babyname
     */
    private static final String COLUMN_BABYNAME = "babyname";
    /**
     * Geburtstag des Babys
     */
    private static final String COLUMN_BIRTHDATE = "birthdate";
    /**
     * Menge der Diamanten
     */
    private static final String COLUMN_DIAMANTS = "diamants";
    /**
     * Profilbild
     */
    private static final String COLUMN_IMAGE = "image";

    /**
     *
     * @param context the context
     */
    public DBHelper(final Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Datenbank wird erstellt.
     * @param db Datenbank
     */
    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(
                "create table profile "
                        + "(id integer primary key, "
                        + "name text, "
                        + "babyname text, "
                        + "birthdate text, "
                        + "diamants integer, "
                        + "image text)"
        );
    }


    /**
     * Aktualisierung der Datenbank
     * @param db Datenbank
     * @param oldVersion alte Version
     * @param newVersion neue Version
     */
    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS profile");
        onCreate(db);
    }

    /**
     * Auslesen des Namens.
     * @return Name
     */
    public String getName() {
        String array_list = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile" , null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list = res.getString(res.getColumnIndex(COLUMN_NAME));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    /**
     * Auslesen des Babynamens.
     * @return Babyname
     */
    public String getBabyName() {
        String array_list = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile" , null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list = res.getString(res.getColumnIndex(COLUMN_BABYNAME));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    /**
     * Auslesen der Diamanten.
     * @return Diamanten
     */
    public int getDiamants() {
        int array_list = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile" , null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list = res.getInt(res.getColumnIndex(COLUMN_DIAMANTS));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    /**
     * Aktualisierung der Diamanten.
     * @param dia diamanten
     * @return boolean
     */
    public boolean updateDiamants(int dia)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DIAMANTS, dia);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    /**
     * Auslesen des Geburtstags
     * @return Geburtstag
     */
    public String getBirthday() {
        String array_list = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from profile" , null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list = res.getString(res.getColumnIndex(COLUMN_BIRTHDATE));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    /**
     * Aktualisierung des Geburtstags.
     * @param bi Geburtstag
     * @return boolean
     */
    public boolean updateBirthday(String bi)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BIRTHDATE, bi);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    /**
     * Aktualisierung des Namens.
     * @param name Name
     * @return boolean
     */
    public boolean updateName(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    /**
     * Aktualisierung des Babynamens.
     * @param name Name
     * @return boolean
     */
    public boolean updateBabyName(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BABYNAME, name);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    /**
     * Aktualisierung des Bildes.
     * @param bi Bild
     * @return boolean
     */
    public boolean updateImage(String bi)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IMAGE, bi);
        db.update("profile", contentValues, "id = ? ", new String[] { "1" } );
        return true;
    }

    /**
     * Profil erstellen
     * @param name      Name
     * @param babyname  Babyname
     * @param birthdate Geburtstag
     * @param diamants  Diamanten
     * @param image     Bild
     * @return boolean
     */
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
}
