package com.example.doreen.lfl_babybrei.rezepte;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doreen.lfl_babybrei.R;

import java.util.ArrayList;

/**
 * Created by Doreen on 21.11.2016.
 */

public class listviewAdapter extends BaseAdapter {

    public ArrayList<Zutaten> zutatList;
    Activity activity;

    public listviewAdapter(Activity activity, ArrayList<Zutaten> zutatList) {
        super();
        this.activity = activity;
        this.zutatList = zutatList;
    }

    @Override
    public int getCount() {
        return zutatList.size();
    }

    @Override
    public Object getItem(int position) {
        return zutatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mMenge;
        TextView mZutat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.mMenge = (TextView) convertView.findViewById(R.id.sMenge);
            holder.mZutat = (TextView) convertView.findViewById(R.id.sZutat);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Zutaten item = zutatList.get(position);
        String a;
        if(item.getMenge()>0){
            a = "" + item.getMenge();
        }else{
            a ="";
        }

        holder.mMenge.setText(a);
        holder.mZutat.setText(item.getZutat().toString());

        holder.mMenge.setTextColor(Color.BLACK);
        holder.mZutat.setTextColor(Color.BLACK);
        return convertView;
    }
}