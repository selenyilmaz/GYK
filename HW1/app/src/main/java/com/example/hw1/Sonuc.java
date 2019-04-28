package com.example.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Sonuc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        TextView sonucText = (TextView) findViewById(R.id.sonucText);
        TextView sonuc = (TextView) findViewById(R.id.sonuc);
        TextView sonucAciklama = (TextView) findViewById(R.id.sonucAciklama);
        Button menuButton = (Button) findViewById(R.id.menu);

        Intent sonucIntent = getIntent();
        String bmi = sonucIntent.getStringExtra(Hosgeldin.EXTRA_BMI);
        sonuc.setText(bmi);


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(Sonuc.this, AnaMenu.class);
                startActivity(menuIntent);

            }
        });
    }
}
