package com.example.doreen.lfl_babybrei;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
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
private List<Item> mItems = new ArrayList<Item>();
private final LayoutInflater mInflater;
private Context c;

public MyAdapter(Context context, List<Item> beitraege) {
        mInflater = LayoutInflater.from(context);
        mItems = beitraege;
        this.c = context;
        }

@Override
public int getCount() {
        return mItems.size();
        }

@Override
public Item getItem(int i) {
        return mItems.get(i);
        }

@Override
public long getItemId(int i) {
        return mItems.get(i).drawableId;
        }

@Override
public View getView(int i, View view, ViewGroup viewGroup) {
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

        if(item.enable.equals("false")){
            enable.setImageResource(R.drawable.enabled);
            name.setTextColor(ContextCompat.getColor(c, R.color.overlaygreen));

           // LayerDrawable stars = (LayerDrawable) ratb.getProgressDrawable();
           // stars.getDrawable(2).setColorFilter(Color.parseColor("#175D2D"),PorterDuff.Mode.SRC_ATOP);
        }

        return v;
        }

public static class Item {
    public final String name;
    public final int drawableId;
    public final int rate;
    public final String enable;

    public Item(String name, int drawableId, int rate, String en) {
        this.name = name;
        this.drawableId = drawableId;
        this.rate = rate;
        this.enable = en;
    }


}
}