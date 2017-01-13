package doreen.lfl_babybrei.rezepte;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import doreen.lfl_babybrei.R;

import java.util.ArrayList;

/**
 * Created by Doreen on 02.12.2016.
 */
public class SpielbrettAdapter extends ArrayAdapter {
    /**
     * Modelitems.
     */
    private ArrayList modelItems = null;
    /**
     * Context.
     */
    private Context context;


    /**
     * Initialisiert den Spielbrettadapter
     *
     * @param context   context
     * @param resource  resource
     */
    public SpielbrettAdapter(final Context context, final ArrayList resource) {
        super(context, R.layout.einkaufszettelcheck, resource);
        this.context = context;
        this.modelItems = resource;
    }

    /**
     * Anzeige der View und deren Befüllung
     * @param position Position
     * @param convertView View
     * @param parent Abstammung
     * @return View
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.spielbrettcheck, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.checkedTextView1);
        final CheckedTextView cb = (CheckedTextView) convertView.findViewById(R.id.checkedTextView1);
        Zutaten f = (Zutaten) modelItems.get(position);
        // Anzeige wird befüllt mit Text
        if (f.getMenge() > 0) {
            name.setText(f.getMenge() + " " + f.getZutat());
        } else {
            name.setText(f.getZutat());
        }

        cb.setChecked(false);
        return convertView;
    }

}
