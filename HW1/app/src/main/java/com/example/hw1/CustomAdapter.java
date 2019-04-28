package com.example.hw1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<Liste> listedekiler;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Liste> listedekiler) {
        this.context = context;
        this.listedekiler=listedekiler;
    }
    @Override
    public int getCount() {
       return listedekiler.size();
    }

    @Override
    public Object getItem(int position) {
        return listedekiler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listedekiler.indexOf(getItem(position));
    }
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //xml dosyalarını javaya donusturecek?
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom, null);

            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.customText);
            holder.imageView = (ImageView) convertView.findViewById(R.id.customImage);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Liste kisiItem = (Liste) getItem(position);

        holder.txtTitle.setText(kisiItem.getName());
        holder.imageView.setImageResource(kisiItem.getPhoto());


        return convertView;
    }
}
