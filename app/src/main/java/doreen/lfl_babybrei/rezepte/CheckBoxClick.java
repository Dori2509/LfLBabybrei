package doreen.lfl_babybrei.rezepte;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;

import java.util.ArrayList;

/**
 * Checkbox für Einkaufszettel
 * Created by Doreen on 27.11.2016.
 */
public class CheckBoxClick implements AdapterView.OnItemClickListener {
    /**
     * Einkaufszettelgröße.
     */
    private int einkaufszettelSize;
    /**
     * Liste, der abgekreuzten Zutaten
     */
    private ArrayList<Integer> checkedList;

    /**
     * Initialisierung der Checkboxen
     * @param a a
     */
    public CheckBoxClick(final int a) {
        this.einkaufszettelSize = a;
        checkedList = new ArrayList<>(einkaufszettelSize);
        int e = 0;

        while (e < einkaufszettelSize) {
            //0 bedeutet unchecked, 1 bedeutet checked
            checkedList.add(0);
            e++;
        }
    }

    /**
     * Auswirkungsbeschreibung, wenn Checkbox angeklickt.
     * @param arg0 AdapterView
     * @param arg1 View
     * @param arg2 position
     * @param arg3 long
     */
    @Override
    public void onItemClick(final AdapterView<?> arg0, final View arg1, final int arg2, final long arg3) {
        CheckedTextView ctv = (CheckedTextView) arg1;
        if (ctv.isChecked()) {
            checkedList.set(arg2, 0);
            ctv.setChecked(false);
        } else {
            checkedList.set(arg2, 1);
            ctv.setChecked(true);
        }
    }

    /**
     * Rückgabe der Liste aller abgeharkten Zutaten
     * @return Liste
     */
    public ArrayList<Integer> getCheckedList() {
        return checkedList;
    }
}
