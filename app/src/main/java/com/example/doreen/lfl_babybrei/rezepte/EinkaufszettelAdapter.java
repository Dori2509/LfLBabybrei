package com.example.doreen.lfl_babybrei.rezepte;

/**
 * Created by Doreen on 27.11.2016.
 */
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.MyAdapter;
import com.example.doreen.lfl_babybrei.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class EinkaufszettelAdapter extends ArrayAdapter{
        ArrayList modelItems = null;
        Context context;



public EinkaufszettelAdapter(Context context, ArrayList resource) {
        super(context, R.layout.einkaufszettelcheck,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;
        }


@Override
public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.einkaufszettelcheck, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.checkedTextView1);
        final CheckedTextView cb = (CheckedTextView) convertView.findViewById(R.id.checkedTextView1);
        Zutaten f = (Zutaten) modelItems.get(position);
        if(f.getMenge()>0){
                name.setText(f.getMenge() + " " + f.getZutat());
        } else{
                name.setText(f.getZutat());
        }



        cb.setChecked(false);
        return convertView;
        }



   }