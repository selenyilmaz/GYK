package com.example.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.hw1.MESSAGE";

    public final static String EXTRA_PASSWORD = "com.example.hw1.PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText isim = (EditText) findViewById(R.id.isim);
        final EditText parola = (EditText) findViewById(R.id.parola);
        Button girisButton = (Button) findViewById(R.id.giris);

        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent girisIntent = new Intent(MainActivity.this, Hosgeldin.class);

                //putExtrayı sonraki sayfaya geçirmek için isim nesnesi üzerinden yazılan texti aldık
                //intent üzerinden putExtra ile aktardık
                String message = isim.getText().toString();
                girisIntent.putExtra(EXTRA_MESSAGE, message);

                String password = parola.getText().toString();
                girisIntent.putExtra(EXTRA_PASSWORD,password);

                startActivity(girisIntent);
            }
        });
    }
}
