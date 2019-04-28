package com.example.gyk_gun3_2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Kamera extends AppCompatActivity {

    private static final int VIDEO_ACTION_CODE = 101;
    private static final int IMAGE_ACTION_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamera);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        Button photo = (Button) findViewById(R.id.showPhoto);
        Button video = (Button) findViewById(R.id.playVideo);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, IMAGE_ACTION_CODE);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(videoIntent, VIDEO_ACTION_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK) return; //eger gelen parametre bekledigimiz bir seyin disinda ise bir sey yapmiyor

        switch (requestCode){
            case VIDEO_ACTION_CODE :
                //videoview idsini buraya tanittik
                VideoView videoView = ((VideoView) findViewById(R.id.videoView));
                videoView.setVideoURI(data.getData());
                //media controller ile videoyu oynatirken durdurabilir, ileri geri sarabiliriz.
                videoView.setMediaController(new MediaController(this));
                videoView.requestFocus();
                videoView.start();
                break;

            case IMAGE_ACTION_CODE :
                Bundle extras = data.getExtras();
                //fotografi bitmap ile imageView a ceviriyoruz
                ((ImageView) findViewById(R.id.imageView)).setImageBitmap((Bitmap) extras.get("data"));
                break;
            default:
                break;

        }
    }
}
