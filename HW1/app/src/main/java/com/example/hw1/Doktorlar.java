package com.example.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Doktorlar extends AppCompatActivity {

    public final static String EXTRA_ISIM = "com.example.hw1.ISIM";
    public final static String EXTRA_WEBURL = "com.example.hw1.WEBURL";
    public final static String EXTRA_TEL = "com.example.hw1.TEL";

    private String[] doktorlar ={"Elif Şenyiğit", "Sıla Yaren Güneş", "Betül", "Selen Yılmaz"};
    String[] webUrl = {"https://www.bayindirhastanesi.com.tr/doktorlar/almila-bulun-1248", "https://www.bayindirhastanesi.com.tr/doktorlar/esma-nazli-kutluoglu-1370",
    "https://www.bayindirhastanesi.com.tr/doktorlar/isil-iltar-68", "https://www.bayindirhastanesi.com.tr/doktorlar/laden-jaferi-70"};
    String[] telNo = {"05374656003", "05374656004", "05374656005", "05374656003"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doktorlar);

        ListView doktorListView = (ListView) findViewById(R.id.doktorListView);

        ArrayAdapter<String> veriAdaptoru = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, doktorlar);
        doktorListView.setAdapter(veriAdaptoru);

        doktorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent doktorIntent = new Intent(Doktorlar.this, DoktorInfo.class);

                String doktorIsim = doktorlar[position];
                doktorIntent.putExtra(EXTRA_ISIM, doktorIsim);

                String doktorWeb = webUrl[position];
                doktorIntent.putExtra(EXTRA_WEBURL,doktorWeb);

                String doktorTel = telNo[position];
                doktorIntent.putExtra(EXTRA_TEL, doktorTel);

                startActivity(doktorIntent);
            }
        });
    }
}
