package com.example.hw1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DoktorInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doktor_info);

        ImageView imageView = (ImageView) findViewById(R.id.imageView4);
        TextView doktorIsim = (TextView) findViewById(R.id.doktorIsim);
        Button webButton = (Button) findViewById(R.id.webButton);
        Button araButton = (Button) findViewById(R.id.araButton);
        Button mesajButton = (Button) findViewById(R.id.mesajButton);

        final String randevuMesaj = "10 Nisan 2019 Saat 14:30'a randevu almak istiyorum.";

        //putExtra ile gelen isim ve parolayı intent ile alıyoruz
        Intent isimIntent = getIntent();
        String isim = isimIntent.getStringExtra(Doktorlar.EXTRA_ISIM);
        doktorIsim.setText(isim);

        final Intent telIntent = getIntent();
        final String telNo = telIntent.getStringExtra(Doktorlar.EXTRA_TEL);

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(DoktorInfo.this, DoktorWeb.class);
                startActivity(webIntent);
            }
        });

        araButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent aramaIntent = new Intent(Intent.ACTION_DIAL);
               aramaIntent.setData(Uri.parse("tel:" + telNo));

               if(aramaIntent.resolveActivity(getPackageManager()) != null){
                   startActivity(aramaIntent);

               }
            }
        });

        mesajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:" + telNo);
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);

                smsIntent.putExtra("sms_body:", randevuMesaj);
                startActivity(smsIntent);
            }
        });
    }
}
