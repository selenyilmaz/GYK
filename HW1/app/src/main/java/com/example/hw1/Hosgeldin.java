package com.example.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Hosgeldin extends AppCompatActivity {
    public final static String EXTRA_BMI = "com.example.hw1.BMI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosgeldin);

        TextView isimPE = (TextView) findViewById(R.id.isimPE);
        TextView hosgeldin = (TextView) findViewById(R.id.hosgeldin);
        final EditText boy = (EditText) findViewById(R.id.boy);
        final EditText kilo = (EditText) findViewById(R.id.kilo);
        final Button hesaplaButton = (Button) findViewById(R.id.hesapla);

        //putExtra ile gelen isim ve parolayı intent ile alıyoruz
        Intent peIntent = getIntent();
        String message = peIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        isimPE.setText(message);

        //Intent peIntent2 = getIntent();
        //String password = peIntent2.getStringExtra(MainActivity.EXTRA_PASSWORD);

        hesaplaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hesaplaIntent = new Intent(Hosgeldin.this, Sonuc.class);

                //boyu edittexten alıyoruz ve double yapıyoruz
                double height = Double.parseDouble(boy.getText().toString());

                //kiloyu edittexten aldık ve putextra ile gönderiyoruz
                double weight = Double.parseDouble(kilo.getText().toString());

                double bmi = weight/(height*height);
                String sonuc = String.format("%.2f",bmi);
                //String sonuc = Double.toString(bmi);

                // DecimalFormat numberFormat = new DecimalFormat("#.00");
                //sonuc = numberFormat.format(sonuc);

                hesaplaIntent.putExtra(EXTRA_BMI,sonuc);
                startActivity(hesaplaIntent);
            }
        });
    }
}
