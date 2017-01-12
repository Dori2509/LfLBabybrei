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
     * The Model items.
     */
    private ArrayList modelItems = null;
    /**
     * The Context.
     */
    private Context context;


    /**
     * Instantiates a new Spielbrett adapter.
     *
     * @param context  the context
     * @param resource the resource
     */
    public SpielbrettAdapter(final Context context, final ArrayList resource) {
        super(context, R.layout.einkaufszettelcheck, resource);
        this.context = context;
        this.modelItems = resource;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.spielbrettcheck, parent, false);
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
