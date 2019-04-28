package com.example.gyk_gun3_2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Harita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harita);

        ImageView imageView = (ImageView) findViewById(R.id.imageView4);
        final Button haritaAc = (Button) findViewById(R.id.haritaAc);

        haritaAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uri cinsinden haritada açılmak istenen koordinatlar yazdık
                //geo: kalıbı değiştirilmemeli
                Uri geoLocation = Uri.parse("geo:41.0138400,28.9496600");

                //haritaIntent olarak intent nesnesi oluşturduk ve koordinatları imageview olarak intente aktardık
                Intent haritaIntent = new Intent(Intent.ACTION_VIEW);
                haritaIntent.setData(geoLocation);

                if (haritaIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(haritaIntent);
                }
            }
        });
    }
}
