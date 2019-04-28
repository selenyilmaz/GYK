package com.example.hw1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DoktorWeb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doktor_web);
        WebView doktorWeb = (WebView) findViewById(R.id.doktorWeb);

        Intent webIntent = getIntent();
        String goUrl = webIntent.getStringExtra(Doktorlar.EXTRA_WEBURL);
        //String goUrl = "https://www.bayindirhastanesi.com.tr/";
        doktorWeb.getSettings().setDomStorageEnabled(true);
        doktorWeb.getSettings().setJavaScriptEnabled(true);
        doktorWeb.loadUrl(goUrl);

        //progress isimli bir nesne ile progress dialog ekleyerek sayfa acildiginda cikacak yaziyi belirledik
        final ProgressDialog progress = ProgressDialog.show(this, "Bayındır Hastanesi",
                "Yükleniyor...", true);
        progress.show();

        //linki gönderdikten sonra siteden gelen cevaba göre yapılacak işleri tanımladık
        doktorWeb.setWebViewClient(new WebViewClient() {


            //eğer işlem başarılıysa
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                Toast.makeText(getApplicationContext(),
                        "Sayfa yüklendi", Toast.LENGTH_SHORT).show();

                //yükleniyor yazısının işi bitince sayfada kalksın diye dismiss dedik
                progress.dismiss();

            }

            //eğer işlem başarısızsa
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(),"Bir hata oluştu", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

    }
}
