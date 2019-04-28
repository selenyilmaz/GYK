package com.example.gyk_gun3_2;

import android.content.pm.PackageManager;
import android.media.Image;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

//genelde çoklu butonu olan activitylerde bu şekilde OnClickListener methodu implement edilir
public class Ses extends AppCompatActivity implements View.OnClickListener {

    //kamera activitydeki gibi burda da ses için bir code verdik
    private static final int REQUEST_AUDIO_PERMISSION_CODE = 200;

    //sesi kaydetmek ve kaydedileni çalmak için media aralarını kullanıyoruz.
    private MediaRecorder recorder;
    private MediaPlayer player;

    //sesi kaydedeceğimiz yeri getPath ile belirliyoruz
    private final String filepath = Environment.getExternalStorageDirectory().getPath() + "/record.3gp";

    //butonları başka yerde de kullancağımız için onCreate'in içinde tanımlıyoruz. id'lerini içeride bunlara atadık.
    ImageView imageView;
    Button kaydetButton;
    Button durdurButton;
    Button calButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ses);

       imageView = (ImageView) findViewById(R.id.imageView3);
       kaydetButton = (Button) findViewById(R.id.kaydetButton);
       durdurButton = (Button) findViewById(R.id.durdurButton);
       calButton = (Button) findViewById(R.id.calButton);

       //burada butonları bu şekilde yazarak hepsini tek bir onClick fonksiyonundan çağırabilicez
       kaydetButton.setOnClickListener(this);
       durdurButton.setOnClickListener(this);
       calButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        //kaydet butonuna basıldığında
        if (view ==kaydetButton){
            //önce izinleri kontrol ediyor
            //buradaki checkPermissions, startRecording, stopRecorging gibi fonksiyonları kendimiz aşağıda tanımladık
            if(checkPermissions()) {
                startRecording();
            }
            else{
                requestPermissions();
                startRecording();
            }
        }

        //durdur butonuna basıldığında
        else if (view == durdurButton){
            stopRecording();
        }

        //çal butonuna basıldığında
        else if (view == calButton){
            playRecording();
        }
    }

    //startRecording fonksiyonu
    private void startRecording (){
        //recorder ı yukarıda tanımlamıştık
        recorder = new MediaRecorder();

        //ses ayarları
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        //kaydedileceği yeri yazıyor
        recorder.setOutputFile(filepath);

        try{
            //hata tespit etmezse
            recorder.prepare();
            recorder.start();
            Toast.makeText(getApplicationContext(), "Kayıt yapılıyor.", Toast.LENGTH_LONG).show();
        }

        //hata alırsa
        catch(IllegalStateException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //stopRecording fonksiyonu
    private void stopRecording(){
        if (recorder != null){
            Toast.makeText(getApplicationContext(), "Kayıt durduruldu.", Toast.LENGTH_LONG).show();
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
        }
    }

    //playRecording fonksiyonu
    private void playRecording(){
        //yukarıda tanımladığımız player ı kullanıyoruz
        player = new MediaPlayer();

        //buna frekans atıyoruz
        player.setVolume(1.0f, 1.0f);

        try{
            player.setDataSource(filepath);
            player.prepare();
            player.start();
            Toast.makeText(getApplicationContext(), "Katıt çalıyor.", Toast.LENGTH_LONG).show();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer arg0) {
                    player.stop();
                    player.release();
                    player = null;
                }
            });
        }
        catch (Exception e){
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode){

           case REQUEST_AUDIO_PERMISSION_CODE:
               if (grantResults.length > 0){
                   boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                   boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                   if (permissionToRecord && permissionToStore){
                       Toast.makeText(getApplicationContext(), "Permission granted.", Toast.LENGTH_LONG).show();
                   }
                   else {
                       Toast.makeText(getApplicationContext(), "Permission denied.", Toast.LENGTH_LONG).show();
                   }
               }
               break;
       }
    }

    //izinleri kontrol ediyoruz
    public boolean checkPermissions(){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions(){
        ActivityCompat.requestPermissions(Ses.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }
}
