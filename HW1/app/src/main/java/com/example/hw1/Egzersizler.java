package com.example.hw1;

import android.content.DialogInterface;
import android.graphics.drawable.RippleDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Egzersizler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egzersizler);

        ArrayList<Liste> egzersizListesi = new ArrayList<Liste>();

        egzersizListesi.add(new Liste("Kol Germe" , R.drawable.ust_isinma));
        egzersizListesi.add(new Liste("Bacak Germe", R.drawable.bacak_isinma));
        egzersizListesi.add(new Liste("Ağırlık Kaldırma", R.drawable.agirlik));
        egzersizListesi.add(new Liste("Koşu", R.drawable.kosu));
        egzersizListesi.add(new Liste("Bisiklet", R.drawable.bisiklet));
        egzersizListesi.add(new Liste("Yüzme", R.drawable.yuzme));
        egzersizListesi.add(new Liste("Tenis", R.drawable.tenis));

        ListView egzersizListView = (ListView) findViewById(R.id.egzersizListView);

        CustomAdapter customAdapter = new CustomAdapter(Egzersizler.this,egzersizListesi);
        egzersizListView.setAdapter(customAdapter);

        egzersizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder diyalogEgzersiz = new AlertDialog.Builder(Egzersizler.this);
                diyalogEgzersiz.setMessage("Kolay gelsin.").setCancelable(false).setPositiveButton("Tamam.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                diyalogEgzersiz.create().show();
            }
        });

    }
}

