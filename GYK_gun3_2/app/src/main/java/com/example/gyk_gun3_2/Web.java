package com.example.gyk_gun3_2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Web extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = (WebView) findViewById(R.id.webView);

        //string seklinde gidilecek olan linki tanimladik
        String goUrl = "https://www.youtube.com/watch?v=Xnv4l7Unrus";

        //webView objesi ile sayfa ayalari yaptik
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(goUrl);

        //progress isimli bir nesne ile progress dialog ekleyerek sayfa acildiginda cikacak yaziyi belirledik
        final ProgressDialog progress = ProgressDialog.show(this, "Geleceği Yazanlar",
                "Yükleniyor...", true);
        progress.show();

        //linki gönderdikten sonra siteden gelen cevaba göre yapılacak işleri tanımladık
        webView.setWebViewClient(new WebViewClient() {


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
