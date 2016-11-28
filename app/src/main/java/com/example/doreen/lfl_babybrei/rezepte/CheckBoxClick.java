package com.example.doreen.lfl_babybrei.rezepte;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Doreen on 27.11.2016.
 */

public class CheckBoxClick implements AdapterView.OnItemClickListener {
    public int einkaufszettelSize;
    public ArrayList<Integer> checkedList;

    public CheckBoxClick(int a){
        this.einkaufszettelSize = a;
        checkedList = new ArrayList<>(einkaufszettelSize);
        int e = 0;

        while (e < einkaufszettelSize){
            //0 bedeutet unchecked, 1 bedeutet checked
            checkedList.add(0);
            e++;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        CheckedTextView ctv = (CheckedTextView)arg1;
        if(ctv.isChecked()){
            checkedList.set(arg2, 0);
            ctv.setChecked(false);
        }else{
            checkedList.set(arg2, 1);
            ctv.setChecked(true);
        }
    }

    public ArrayList<Integer> getCheckedList(){
        return checkedList;
    }
}
