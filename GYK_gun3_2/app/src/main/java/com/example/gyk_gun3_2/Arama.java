package com.example.gyk_gun3_2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Arama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama);

        final EditText telNo = (EditText) findViewById(R.id.telNo);
        Button ara = (Button) findViewById(R.id.ara);

        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = telNo.getText().toString();
                Intent aramaIntent = new Intent(Intent.ACTION_DIAL);
                aramaIntent.setData(Uri.parse("tel:" + phoneNumber));

                if(aramaIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(aramaIntent);
                }
            }
        });
    }
}
