package com.example.gyk_gun2;
//GYK 2. gun.
// Burclar uygulamasi

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        public final static String EXTRA_MESSAGE = "com.example.gyk_gun2_MESAJ";
        public final static String EXTRA_PASSWORD = "com.example.gyk_gun2.PAROLA";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView = (TextView) findViewById(R.id.textView);
        final EditText name = (EditText) findViewById(R.id.editText);
        final EditText password = (EditText) findViewById(R.id.editText3);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aragecis = new Intent(MainActivity.this,AraEkran.class);

                String mesaj = name.getText().toString();
                aragecis.putExtra(EXTRA_MESSAGE, mesaj);

                String parola = password.getText().toString();
                aragecis.putExtra(EXTRA_PASSWORD, parola);

                startActivity(aragecis);

            }
        });


    }
}
