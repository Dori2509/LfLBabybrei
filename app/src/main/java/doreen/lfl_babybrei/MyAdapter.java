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
 * Created by Doreen on 18.11.2016.
 */
public class MyAdapter extends BaseAdapter {
    /**
     *
     */
    private List<Item> mItems = new ArrayList<Item>();
    /**
     *
     */
    private final LayoutInflater mInflater;
    /**
     *
     */
    private Context c;

        /**
         * Instantiates a new My adapter.
         *
         * @param context   the context
         * @param beitraege the beitraege
         */
        public MyAdapter(final Context context, final List<Item> beitraege) {
        mInflater = LayoutInflater.from(context);
        mItems = beitraege;
        this.c = context;
        }

    /**
     *
     * @return
     */
    @Override
    public int getCount() {
        return mItems.size();
        }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public Item getItem(final int i) {
                return mItems.get(i);
                }

    /**
     *
      * @param i
     * @return
     */
    @Override
    public long getItemId(final int i) {
                return mItems.get(i).drawableId;
                }

    /**
         *
          * @param i
         * @param view
         * @param viewGroup
         * @return
     */
        @Override
    public View getView(final int i, final View view, final ViewGroup viewGroup) {
                View v = view;
                ImageView picture;
                TextView name;
                RatingBar ratb;
                ImageView enable;

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

                   // LayerDrawable stars = (LayerDrawable) ratb.getProgressDrawable();
                   // stars.getDrawable(2).setColorFilter(Color.parseColor("#175D2D"),PorterDuff.Mode.SRC_ATOP);
                }

                return v;
                }

                /**
                 * The type Item.
                 */
                public static class Item {
                        /**
                         * The Name.
                         */
                        private final String name;
                        /**
                         * The Drawable id.
                         */
                        private final int drawableId;
                        /**
                         * The Rate.
                         */
                        private final int rate;
                        /**
                         * The Enable.
                         */
                        private final String enable;

                        /**
                         * Instantiates a new Item.
                         *
                         * @param name       the name
                         * @param drawableId the drawable id
                         * @param rate       the rate
                         * @param en         the en
                         */
                        public Item(final String name, final int drawableId, final int rate, final String en) {
                this.name = name;
                this.drawableId = drawableId;
                this.rate = rate;
                this.enable = en;
            }

                }
        }
