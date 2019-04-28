package com.example.gyk_401_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gyk_401_1.R;
import com.example.gyk_401_1.models.Diet;

import java.util.List;

public class DietListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Diet> dietList;

    public DietListAdapter(LayoutInflater layoutInflater, List<Diet> dietList){
        this.layoutInflater = layoutInflater;
        this.dietList = dietList;
    }
    @Override
    public int getCount() {
        return dietList.size();
    }

    @Override
    public Object getItem(int i) {
        return dietList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent){
        View dietView = layoutInflater.inflate(R.layout.diet_fragment,null);
        ImageView foodPicture = (ImageView) dietView.findViewById(R.id.dietFoodPicture);
        TextView foodName = (TextView) dietView.findViewById(R.id.dietFoodName);
        TextView foodCalorie = (TextView) dietView.findViewById(R.id.dietFoodCalorie);

        Diet diet = dietList.get(i);
        foodPicture.setImageResource(diet.getFoodPicture());
        foodName.setText(diet.getFoodName());
        foodCalorie.setText(diet.getTotalCalorie());
        return dietView;
    }
}
