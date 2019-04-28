package com.example.hw1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Yemekler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemekler);

        final ArrayList<Liste> yemekListesi = new ArrayList<Liste>();

        yemekListesi.add(new Liste("Çorba", R.drawable.corba));
        yemekListesi.add(new Liste("Kahvalti", R.drawable.kahvalti));
        yemekListesi.add(new Liste("Izgara", R.drawable.izgara));
        yemekListesi.add(new Liste("Balık", R.drawable.balik));
        yemekListesi.add(new Liste("Salata", R.drawable.salata));
        yemekListesi.add(new Liste("Elma", R.drawable.elma));
        yemekListesi.add(new Liste("Çilek", R.drawable.cilek));
        yemekListesi.add(new Liste("Portakal", R.drawable.portakal));
        yemekListesi.add(new Liste("Üzüm", R.drawable.uzum));

        ListView yemekListView = (ListView) findViewById(R.id.yemekListView);
        CustomAdapter customAdapter = new CustomAdapter(Yemekler.this,yemekListesi);
        yemekListView.setAdapter(customAdapter);

        yemekListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder diyalogYemek = new AlertDialog.Builder(Yemekler.this);
                diyalogYemek.setMessage("Afiyet olsun.").setCancelable(false).setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                diyalogYemek.create().show();
            }
        });
    }
}


