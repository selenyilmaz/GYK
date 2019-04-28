package com.example.gyk_401_1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyk_401_1.R;
import com.example.gyk_401_1.models.Diet;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//önce onAttach-onCreate-onCreateView-onActivityCreated-onStart-onResume
//onPause-onStop-onDestroView-onDestroy-onDetach
//bunun xmlinde bulunan scroll view yazı sayfaya sığmazsa okuyabilmemiz için kaydırma özelliği koyabilmemiz için
public class HealthFragment extends Fragment {
    //private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HealthFragment(){
        Log.i("Constructor","Constructor"); //loglamak için kullanılır. i information demek
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate","Fragment");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("onCreateView","Fragment");
        hashmapExample();
        return inflater.inflate(R.layout.health_fragment, container,false);

    }
    public void hashmapExample(){
        HashMap hm = new HashMap();
        hm.put("H","Hidrojen");
        hm.put("Ag", "Gümüş");
        hm.put("Au", "Altın");
        hm.put("Li","Lityum");
        hm.put("Al", "Alüminyum");
        hm.put("N", "Azot");
        Set set = hm.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()){
            Map.Entry me = (Map.Entry) i.next();
            Log.i("HashMap Element",me.getKey()+ ": "+me.getValue());
        }
        Log.i("Deger:", hm.get("Au").toString());
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Selen", 22);
        hashMap.put("Cansın", 24);

        HashMap<String, Diet> hashMap1 = new HashMap<String, Diet>();
        hashMap1.put("Menu1", new Diet(R.drawable.menu_3, "Yumurta, Peynir, Zeytin", "5000C"));
        hashMap1.put("Menu2", new Diet(R.drawable.menu_1, "Mevsim Yeşillikleri", "2000C"));
        Set set1 = hashMap1.entrySet();
        Iterator i1 = set1.iterator();
        while (i1.hasNext()){
            Map.Entry me = (Map.Entry) i1.next();
            Log.i("Hashmap diet", me.getKey()+": "+me.getValue().toString()); //burdaki toString için diet içindekini override ettik
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("onAttach","Fragment");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("onActivityCreated","Fragment");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart","Fragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume","Fragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.i("onDetach","Fragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause","Fragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("onStop","Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroyView","Fragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","Fragment");
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction (Uri uri);
    }
}
