package doreen.lfl_babybrei.db;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Doreen on 15.11.2016.
 */
public class DatabaseOpenHelper extends SQLiteAssetHelper {
    /**
     * Name der Datenbank.
     */
    private static final String DATABASE_NAME = "LfL-data.db";
    /**
     * Version der Datenbank.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Neue Datenbank wird initialisiert.
     * @param context the context
     */
    public DatabaseOpenHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
