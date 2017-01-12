package doreen.lfl_babybrei.rezepte;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import doreen.lfl_babybrei.R;

import java.util.ArrayList;

/**
 * Created by Doreen on 21.11.2016.
 */
public class ListviewAdapter extends BaseAdapter {

    /**
     * Zutatenliste
     */
    private ArrayList<Zutaten> zutatList;
    /**
     * Activity.
     */
    private Activity activity;

    /**
     * Initialisierung des ListViewAdapter
     * @param activity  Activity
     * @param zutatList Zutatenliste
     */
    public ListviewAdapter(final Activity activity, final ArrayList<Zutaten> zutatList) {
        super();
        this.activity = activity;
        this.zutatList = zutatList;
    }

    /**
     * Größe der Zutatenliste
     * @return Anzahl der Zutaten
     */
    @Override
    public int getCount() {
        return zutatList.size();
    }

    /**
     * Zutat wird ausgelesen aus der Liste.
     * @param position Position
     * @return Zutat
     */
    @Override
    public Object getItem(final int position) {
        return zutatList.get(position);
    }

    /**
     * Auslesen der ID
     * @param position Position
     * @return ID
     */
    @Override
    public long getItemId(final int position) {
        return position;
    }

    private class ViewHolder {
        /**
         * Menge.
         */
        private TextView menge;
        /**
         * The M zutat.
         */
        private TextView zutat;
    }

    /**
     * View wird erstellt und befüllt.
     * @param position Position
     * @param convertView View
     * @param parent Abstammung
     * @return View
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.menge = (TextView) convertView.findViewById(R.id.sMenge);
            holder.zutat = (TextView) convertView.findViewById(R.id.sZutat);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Zutaten item = zutatList.get(position);
        String a;
        //Falls die Menge keine Angabe besitzt, sollte nichts angezeigt werden
        if (item.getMenge() > 0) {
            a = "" + item.getMenge();
        } else {
            a = "";
        }

        holder.menge.setText(a);
        holder.zutat.setText(item.getZutat().toString());

        holder.menge.setTextColor(Color.BLACK);
        holder.zutat.setTextColor(Color.BLACK);
        return convertView;
    }
}
