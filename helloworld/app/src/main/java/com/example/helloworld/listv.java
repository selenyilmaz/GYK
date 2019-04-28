package com.example.helloworld;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listv extends AppCompatActivity {
    private String[] ulkeler = {"Turkiye" , "Almanya", "Belcika" , "Portekiz", "Polonya", "Avusturya","Hollanda","Macaristan","Bulgaristan","Norveç","Danimarka"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listv);

        ListView listView = (ListView) findViewById(R.id.listView);

        //listview kullanırken mutlaka ArrayAdapter kullanmalısın
        ArrayAdapter<String> veriadaptoru = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , android.R.id.text1 , ulkeler);
        listView.setAdapter(veriadaptoru);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder diyalogOlustur = new AlertDialog.Builder(listv.this);
                diyalogOlustur.setMessage(ulkeler[position]).setCancelable(false).setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                diyalogOlustur.create().show();
            }
        });
    }
}