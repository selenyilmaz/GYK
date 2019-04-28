package com.example.gyk_401_1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.gyk_401_1.adapter.DietListAdapter;
import com.example.gyk_401_1.models.Diet;
import com.example.gyk_401_1.R;
import java.util.ArrayList;

public class DietFragment extends Fragment{
    final ArrayList<Diet> dietList = new ArrayList<Diet>();
    private OnFragmentInteractionListener mListener;

public  DietFragment(){
}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null); //listViewlu olanı inflate etmen gerektğini unutma.
        dietList.add(new Diet(R.drawable.menu_3, "Yumurta, peynir, zeytin" , "5000C"));
        dietList.add(new Diet(R.drawable.menu_1 , "Mevsim Yeşillikleri Salata", "1500C"));
        dietList.add(new Diet(R.drawable.menu_2, "Badem, fındık, ceviz", "4000C"));
        dietList.add(new Diet(R.drawable.menu_4, "Kivi, çilek, muz", "1000C"));


        ListView myDietList = (ListView) view.findViewById(R.id.fragmentListView);
        DietListAdapter dietListAdapter = new DietListAdapter(getLayoutInflater(),dietList);
        myDietList.setAdapter(dietListAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction (Uri uri);
    }
}
