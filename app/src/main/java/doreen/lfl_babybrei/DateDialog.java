package doreen.lfl_babybrei;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import doreen.lfl_babybrei.db.DBHelper;

import java.util.Calendar;

/**
 * Created by Doreen on 06.06.2016.
 * DateDialog wird erzeugt um eine Auswahl des Zieldatums zu erleichtern.
 */
@SuppressLint("ValidFragment")
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    /**
     * Txtdate.
     */
    private TextView txtdate;
    /**
     * Datenbankverbindung
     */
    private DBHelper mydb;
    /**
     * Zugehörigkeit
     */
    private String w;

    /**
     * Initialisiert ein neuen DateDialog
     *
     * @param view view
     * @param mydb Datenbank
     * @param wo Zugehörigkeit
     */
    public DateDialog(final View view, final DBHelper mydb, final String wo) {
        txtdate = (TextView) view;
        this.mydb = mydb;
        this.w = wo;
    }

    /**
     * Initialisierung des gewählten Datums
     * @param savedInstanceState Status
     * @return Dialog
     */
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    /**
     * Nutze das ausgewählte datum, bringe es in die geeignete Form und zeige diese an.
     * @param view
     * @param year
     * @param month
     * @param day
     */
    public void onDateSet(final DatePicker view, final int year, int month, final int day) {
        String sday;
        if (day < 10) {
            sday = String.format("%02d", day);
        } else {
            sday = String.valueOf(day);
        }

        String smonth;
        month = month + 1;
        if (month < 10) {
            smonth = String.format("%02d", month);
        } else {
            smonth = String.valueOf(month);
        }

        String date = sday + "-" + (smonth) + "-" + year;
        txtdate.setText(date);
        if (w.equals("Wochenrechner")) {
            //
        } else {
            mydb.updateBirthday(date);
        }
    }
}
