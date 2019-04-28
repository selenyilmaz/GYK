package com.example.gyk_gun2_3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OzelAdaptor extends BaseAdapter {

    Context context;
    ArrayList<Kisi> kisiler;

    public int getCount(){
        return kisiler.size();
    }

    public Object getItem(int position){
        return kisiler.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int positio, View convertView, ViewGroup parent){
        View v = View.inflate(context , R.layout.satir, null);
        ImageView imageView = v.findViewById(R.id.imageView);
        TextView textView = v.findViewById(R.id.textView);
        return v;


    }


}
