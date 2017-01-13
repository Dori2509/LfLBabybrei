package doreen.lfl_babybrei;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter
 * Created by Doreen on 18.11.2016.
 */
public class MyAdapter extends BaseAdapter {
    /**
     * Liste
     */
    private List<Item> mItems = new ArrayList<Item>();
    /**
     * Inflater
     */
    private final LayoutInflater mInflater;
    /**
     * Kontext
     */
    private Context c;

        /**
         * Initialisierung des Adapters
         *
         * @param context   context
         * @param beitraege beitraege
         */
        public MyAdapter(final Context context, final List<Item> beitraege) {
        mInflater = LayoutInflater.from(context);
        mItems = beitraege;
        this.c = context;
        }

    /**
     * Menge der Items
     * @return Menge
     */
    @Override
    public int getCount() {
        return mItems.size();
        }

    /**
     * bestimmtes Item auslesen
     * @param i
     * @return Item
     */
    @Override
    public Item getItem(final int i) {
                return mItems.get(i);
                }

    /**
     * Auslesen der Item-ID
     * @param i
     * @return ID
     */
    @Override
    public long getItemId(final int i) {
                return mItems.get(i).drawableId;
                }

    /**
     * Liste befüllen
     * @param i i
     * @param view View
     * @param viewGroup ViewGroup
     * @return View
     */
        @Override
    public View getView(final int i, final View view, final ViewGroup viewGroup) {
                View v = view;
                ImageView picture;
                TextView name;
                RatingBar ratb;
                ImageView enable;

                //Alle Bestandteile werden befüllt
                if (v == null) {
                v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
                v.setTag(R.id.ratingBar, v.findViewById(R.id.ratingBar));
                v.setTag((R.id.overlapImage), v.findViewById(R.id.overlapImage));
                }

                picture = (ImageView) v.getTag(R.id.picture);
                name = (TextView) v.getTag(R.id.text);
                ratb = (RatingBar) v.getTag(R.id.ratingBar);
                enable = (ImageView) v.getTag(R.id.overlapImage);

                Item item = getItem(i);

                picture.setImageResource(item.drawableId);

                name.setText(item.name);
                ratb.setRating(item.rate);

                if (item.enable.equals("false")) {
                    enable.setImageResource(R.drawable.enabled);
                    name.setTextColor(ContextCompat.getColor(c, R.color.overlaygreen));
                }

                return v;
                }

                /**
                 * Item.
                 */
                public static class Item {
                        /**
                         * Name.
                         */
                        private final String name;
                        /**
                         * ID
                         */
                        private final int drawableId;
                        /**
                         * Rate.
                         */
                        private final int rate;
                        /**
                         * Enable.
                         */
                        private final String enable;

                        /**
                         * Initialisierung eines Items mit deren Eigenschaften
                         *
                         * @param name        name
                         * @param drawableId  drawable id
                         * @param rate        rate
                         * @param en          en
                         */
                        public Item(final String name, final int drawableId, final int rate, final String en) {
                            this.name = name;
                            this.drawableId = drawableId;
                            this.rate = rate;
                            this.enable = en;
                        }
                }
        }
