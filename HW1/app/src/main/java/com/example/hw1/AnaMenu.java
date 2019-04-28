package com.example.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AnaMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_menu);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        Button doktorButton = (Button) findViewById(R.id.doktorlar);
        Button yemekButton = (Button) findViewById(R.id.yemekler);
        Button egzersizButton = (Button) findViewById(R.id.egzersizler);

        doktorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doktorIntent = new Intent(AnaMenu.this, Doktorlar.class);
                startActivity(doktorIntent);
            }
        });

        yemekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yemekIntent = new Intent(AnaMenu.this, Yemekler.class);
                startActivity(yemekIntent);
            }
        });

        egzersizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent egzersizIntent = new Intent(AnaMenu.this, Egzersizler.class);
                startActivity(egzersizIntent);
            }
        });
    }
}
