package com.example.gyk_gun3_2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        final EditText mesaj = (EditText) findViewById(R.id.mesaj);
        final EditText numara = (EditText) findViewById(R.id.numara);
        Button gonder = (Button) findViewById(R.id.gonder);

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = mesaj.getText().toString();
                String phoneNumber = numara.getText().toString();

                //bunlarin smsto ve sms_body olarak kullanilmasi lazim. yoksa calismiyor.
                Uri uri = Uri.parse("smsto:" + phoneNumber);
                Intent mesajGonder = new Intent(Intent.ACTION_SENDTO, uri);

                mesajGonder.putExtra("sms_body" , message);
                startActivity(mesajGonder);

            }
        });
    }
}
