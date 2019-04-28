package com.example.gyk_gun2_3;
//GYK 2. gun Custom Adapter

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewExtended extends AppCompatActivity {
    final List<Kisi> kisiler=new ArrayList<Kisi>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_extended);

        kisiler.add(new Kisi("Ahmet Yılmaz", false));
        kisiler.add(new Kisi("Ayşe Küçük", true));
        kisiler.add(new Kisi("Fatma Bulgurcu", true));
        kisiler.add(new Kisi("İzzet Altınmeşe", false));
        kisiler.add(new Kisi("Melek Subaşı", true));
        kisiler.add(new Kisi("Selim Serdilli",false));
        kisiler.add(new Kisi("Halil İbrahim",false));

        ListView listView = (ListView) findViewById(R.id.listViewExt);




    }
}
