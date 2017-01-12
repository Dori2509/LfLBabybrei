package doreen.lfl_babybrei.db;

/**
 * Created by Doreen on 15.11.2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import doreen.lfl_babybrei.MyAdapter;
import doreen.lfl_babybrei.rezepte.Zutaten;
import java.util.ArrayList;
import java.util.List;

/**
 * Öffnet, schreibt, liest Datenbank.
 */
public final class DatabaseAccess {
    /**
     * SQLiteOpenhelper.
     */
    private SQLiteOpenHelper openHelper;
    /**
     * Datenbank.
     */
    private SQLiteDatabase database;
    /**
     * Instanz zum Öffnen der Datenbank.
     */
    private static DatabaseAccess instance;
    /**
     * Kontext.
     */
    private Context c;

    /**
     * Privates Konstrukt, um Objekterstellung von außen zu verhindern.
     * @param context the Context
     */
    private DatabaseAccess(final Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
        this.c = context;
    }

    /**
     * Gibt eine Instanz der DatabaseAccess zurück.
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(final Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Öffnet Datenbank.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Schließt Datenbank.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }


    /**
     * Liest alle Beiträge von der Datenbank aus.
     * @param value the value
     * @return Beitragsliste
     */
    public List<MyAdapter.Item> getBeitraege(final String value) {
        List<MyAdapter.Item> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM beitraege WHERE assignement='" + value + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int resID = c.getResources().getIdentifier(cursor.getString(4), "drawable", c.getPackageName());
            String str = cursor.getString(1);
            if (str.length() > 22) {
                str = str.substring(0, 18) + "...";
                list.add(new MyAdapter.Item(str, resID, cursor.getInt(3), cursor.getString(5)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    /**
     * Auslesen der ID-Liste
     *
     * @param value the value
     * @return ID-Liste
     */
    public ArrayList<Integer> getAllID(final String value) {
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

    /**
     * Auslesen der Enabled-Liste
     *
     * @return Enabled-Liste
     */
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

    /**
     * Auslesen des Artikeltitels
     *
     * @param id the id
     * @return Artikeltitel
     */
    public String getArticleTitle(final int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from beitraege WHERE _id=" + id, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            title = res.getString(res.getColumnIndex("title"));
            res.moveToNext();
        }
        res.close();
        return title;

    }

    /**
     * Auslesen aller Rezepte
     *
     * @param value the value
     * @return Rezeptliste
     */
    public List<MyAdapter.Item> getRezepte(final int value) {
        List<MyAdapter.Item> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM rezepte WHERE month='" + value + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int resID = c.getResources().getIdentifier(cursor.getString(4), "drawable", c.getPackageName());
            String str = cursor.getString(1);
            if (str.length() > 22) {
                str = str.substring(0, 18) + "...";
                list.add(new MyAdapter.Item(str, resID, cursor.getInt(3), cursor.getString(9)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    /**
     * Auslesen einer Liste aller Rezeptid´s
     *
     * @param value the value
     * @return Liste aller Rezeptid´s
     */
    public ArrayList<Integer> getAllRezepteID(final int value) {
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

    /**
     * Auslesen aller Rezepte, die im Kochbuch gespeichert sind.
     * @return Liste von Rezepten der Kochbuchrezepte.
     */
    public List<MyAdapter.Item> getKochbuchrezepte() {
        List<MyAdapter.Item> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM rezepte WHERE kochbuch='" + "true" + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int resID = c.getResources().getIdentifier(cursor.getString(4), "drawable", c.getPackageName());
            String str = cursor.getString(1);
            if (str.length() > 22) {
                str = str.substring(0, 18) + "...";
                list.add(new MyAdapter.Item(str, resID, cursor.getInt(3), cursor.getString(9)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    /**
     * Auslesen aller RezepteID´s, die im Kochbuch gespeichert sind.
     * @return Liste der ID´s
     */
    public ArrayList<Integer> getKochbuchRezepteID() {
        ArrayList<Integer> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM rezepte WHERE kochbuch='" + "true" + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getInt(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Auslesen einer Liste der Rezepte, die freigeschalten sind.
     * @return Liste aller Enabled-Rezepte
     */
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

    /**
     * Aktualisieren der Enabled-Eigenschaft eines Rezept.
     * @param id id
     * @param en en
     * @return boolean
     */
    public boolean updateRezepteEnable(final Integer id, final String en) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("enable", en);
        database.update("rezepte", contentValues, "_id = ? ", new String[] {Integer.toString(id) });
        return true;
    }

    /**
     * Aktualisieren der Enabled-Eigenschaft eines Beitrags.
     * @param id id
     * @param en en
     * @return boolean
     */
    public boolean updateEnable(final Integer id, final String en) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("enable", en);
        database.update("beitraege", contentValues, "_id = ? ", new String[] {Integer.toString(id) });
        return true;
    }


    /**
     * Auslesen des Artikeltextes.
     * @param id id
     * @return Artikeltext
     */
    public String getArticleText(final int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from beitraege WHERE _id=" + id , null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            title = res.getString(res.getColumnIndex("fulltext"));
            res.moveToNext();
        }
        res.close();
        return title;

    }

    /**
     * Auslesen des Artikelbildes.
     * @param id id
     * @return Artikelbild
     */
    public int getArticleImage(final int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from beitraege WHERE _id=" + id, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            title = res.getString(res.getColumnIndex("image"));
            res.moveToNext();
        }

        int resID = c.getResources().getIdentifier(title , "drawable", c.getPackageName());
        res.close();
        return resID;

    }

    /**
     * Auslesen des Rezepttitels.
     * @param id id
     * @return Rezepttitel
     */
    public String getRezeptitle(final int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            title = res.getString(res.getColumnIndex("title"));
            res.moveToNext();
        }
        res.close();
        return title;

    }

    /**
     * Auslesen des Rezepttextes
     * @param id id
     * @return Rezepttext
     */
    public String getRezeptText(final int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            title = res.getString(res.getColumnIndex("description"));
            res.moveToNext();
        }
        res.close();
        return title;

    }

    /**
     * Auslesen des Rezeptbildes
     *
     * @param id id
     * @return Rezeptbild
     */
    public int getRezeptImage(final int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            title = res.getString(res.getColumnIndex("image"));
            res.moveToNext();
        }
        int resID = c.getResources().getIdentifier(title, "drawable", c.getPackageName());
        res.close();
        return resID;

    }

    /**
     * Auslesen aller Rezepte im Kochbuch
     *
     * @param id id
     * @return Kochbuch
     */
    public String getKochbuch(final int id) {
        String title = "";
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            title = res.getString(res.getColumnIndex("kochbuch"));
            res.moveToNext();
        }
        res.close();
        return title;

    }

    /**
     * Auslesen der Portionmenge eines Rezeptes
     *
     * @param id the id
     * @return Portion
     */
    public String getRezeptPortion(final int id) {
        String portion = "";
        Cursor res = database.rawQuery("select * from rezepte WHERE _id=" + id, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            portion = res.getString(res.getColumnIndex("portion"));
            res.moveToNext();
        }
        res.close();
        return portion;

    }

    /**
     * Auslesen einer Liste der Zutaten
     *
     * @param value the value
     * @return Zutatenliste
     */
    public ArrayList<Zutaten> getZutaten(final int value) {
        ArrayList<Zutaten> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Zutaten" + value, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
               list.add(new Zutaten(cursor.getInt(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Auslesen einer Liste der Mengen
     *
     * @param value the value
     * @return Mengenliste
     */
    public ArrayList<Long> getMengen(final int value) {
        ArrayList<Long> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Zutaten" + value, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getLong(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Auslesen einer Liste der Zutaten, für den Einkaufszettel
     *
     * @param value the value
     * @return Einkaufszettelzutaten
     */
    public ArrayList<Zutaten> getZutatenEinkaufszettel(final int value) {
        ArrayList<Zutaten> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Zutaten" + value, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Zutaten(cursor.getLong(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Aktualisiert die Mengen
     * @param i     i
     * @param id    id
     * @param menge menge
     * @return boolean
     */
    public boolean updateMenge(final Integer i, final Integer id, final int menge) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", menge);
        database.update("Zutaten" + i, contentValues, "_id = ? ", new String[] {Integer.toString(id) });
        return true;
    }


    /**
     * Aktualisiert die Portionen
     * @param id    id
     * @param menge menge
     * @return boolean
     */
    public boolean updatePortion(final Integer id, final int menge) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("portion", menge);
        database.update("rezepte", contentValues, "_id = ? ", new String[] {Integer.toString(id) });
        return true;
    }

    /**
     * Aktualisiert das Kochbuch
     *
     * @param id id
     * @param kochbuch kochbuch
     * @return boolean
     */
    public boolean updateKochbuch(final Integer id, final String kochbuch)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("kochbuch", kochbuch);
        database.update("rezepte", contentValues, "_id = ? ", new String[] {Integer.toString(id) });
        return true;
    }

}
