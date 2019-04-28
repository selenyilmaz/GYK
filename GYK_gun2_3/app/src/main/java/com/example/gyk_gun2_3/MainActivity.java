package com.example.gyk_gun2_3;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] haberler = {"Sabah", "Aksam" , "Ogle" , "Ikindi" , "Gunduz"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        final ArrayAdapter<String> haberAdaptoru = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1, haberler);
        listView.setAdapter(haberAdaptoru);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent haberintent = new Intent(MainActivity.this,yonlendir.class);
                startActivity(haberintent);

            }
        });

    }
}
