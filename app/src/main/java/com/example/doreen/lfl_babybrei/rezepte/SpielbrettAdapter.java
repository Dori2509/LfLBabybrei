package com.example.doreen.lfl_babybrei.rezepte;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.R;

import java.util.ArrayList;

/**
 * Created by Doreen on 02.12.2016.
 */

public class SpielbrettAdapter extends ArrayAdapter {
    ArrayList modelItems = null;
    Context context;



    public SpielbrettAdapter(Context context, ArrayList resource) {
        super(context, R.layout.einkaufszettelcheck,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.spielbrettcheck, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.checkedTextView1);
        final CheckedTextView cb = (CheckedTextView) convertView.findViewById(R.id.checkedTextView1);
        Zutaten f = (Zutaten) modelItems.get(position);
        name.setText(f.getMenge() + " " + f.getZutat());


        cb.setChecked(false);
        return convertView;
    }



}