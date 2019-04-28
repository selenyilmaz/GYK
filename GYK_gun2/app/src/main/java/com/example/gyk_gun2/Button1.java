package com.example.gyk_gun2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Button1 extends AppCompatActivity {
    private String[] burclar = {"kova" , "Balik" , "Koc" , "Boga" , "Ikizler" , "Yengec" , "Aslam" , "Basak" , ""}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button1);

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> burcAdaptoru = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,burclar);
        listView.setAdapter(burcAdaptoru);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder burcDiyalog = new AlertDialog.Builder(Button1.this);

            }
        });
    }
}
