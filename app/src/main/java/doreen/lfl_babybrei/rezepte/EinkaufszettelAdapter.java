package doreen.lfl_babybrei.rezepte;

/**
 * Hilfe für Einkaufszettel.
 * Created by Doreen on 27.11.2016.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;

import doreen.lfl_babybrei.R;


/**
 * EinkaufszettelAdapter
 */
public class EinkaufszettelAdapter extends ArrayAdapter{
        /**
         * The Model items.
         */
        private ArrayList modelItems = null;
        /**
         * The Context.
         */
        private Context context;


        /**
         * Initialisierung des EinkaufszettelAdapters
         *
         * @param context context
         * @param resource resource
         */
        public EinkaufszettelAdapter(final Context context, final ArrayList resource) {
        super(context, R.layout.einkaufszettelcheck, resource);
        this.context = context;
        this.modelItems = resource;
        }

    /**
     * Checkboxen mit Zutaten für Liste werden angezeigt.
     * @param position Position
     * @param convertView View
     * @param parent Abstammung
     * @return View
     */
        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.einkaufszettelcheck, parent, false);
                TextView name = (TextView) convertView.findViewById(R.id.checkedTextView1);
                final CheckedTextView cb = (CheckedTextView) convertView.findViewById(R.id.checkedTextView1);
                Zutaten f = (Zutaten) modelItems.get(position);
                if (f.getMenge() > 0) {
                        name.setText(f.getMenge() + " " + f.getZutat());
                } else {
                        name.setText(f.getZutat());
                }
                cb.setChecked(false);
                return convertView;
                }
   }