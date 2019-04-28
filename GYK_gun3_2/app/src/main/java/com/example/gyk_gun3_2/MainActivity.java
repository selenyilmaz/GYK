package com.example.gyk_gun3_2;
//coklu menulu olan uygulama
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button kamera = (Button) findViewById(R.id.kamera);
        Button ses = (Button) findViewById(R.id.ses);
        Button harita = (Button) findViewById(R.id.harita);
        Button web = (Button) findViewById(R.id.web);
        Button sms = (Button) findViewById(R.id.sms);
        Button arama = (Button) findViewById(R.id.arama);

        kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kameraIntent = new Intent(MainActivity.this, Kamera.class);
                startActivity(kameraIntent);
            }
        });

        ses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sesIntent = new Intent(MainActivity.this, Ses.class);
                startActivity(sesIntent);
            }
        });

        harita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent haritaIntent = new Intent(MainActivity.this, Harita.class);
                startActivity(haritaIntent);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(MainActivity.this,Web.class);
                startActivity(webIntent);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(MainActivity.this, Sms.class);
                startActivity(smsIntent);
            }
        });

        arama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aramaIntent = new Intent(MainActivity.this, Arama.class);
                startActivity(aramaIntent);
            }
        });
    }
}
