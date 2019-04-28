package com.example.gezginapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.Inet4Address;

public class AddPhotoActivity extends AppCompatActivity {

    ImageView userPhoto;
    Button selectPhoto;
    Button savePhoto;
    FirebaseStorage firebaseStorage;
    FirebaseAuth mAuth;
    Uri filepath;
    private ProgressDialog progressDialog;
    private static final int IMAGE_REQUEST = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        mAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        userPhoto = (ImageView) findViewById(R.id.foto);
        selectPhoto = (Button) findViewById(R.id.foto_sec);
        savePhoto = (Button) findViewById(R.id.foto_kaydet);

        showPhoto();

        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secmeFonksiyonu();
            }
        });

        savePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kaydetmeFonksiyonu();
            }
        });
    }

    private void secmeFonksiyonu(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Resim seçiniz."),IMAGE_REQUEST);

    }
    private void kaydetmeFonksiyonu(){ //çektiğimiz fotoğrafı firebasedeki userprofilephoto olarak kaydediyoruz
        if(filepath != null){
            showProgressDialog();
            //fotoğrafın kaydedileceği yeri referans etmek için kullanacağımız nesne
            StorageReference storageRef = firebaseStorage.getReference();
            storageRef.child("userprofilephoto").putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) { //fotoğraf kaydetme işlemi başarılı olduğunda neler yapılacağını tanımlıyoruz
                    dismissProgressDialog();
                    Toast.makeText(AddPhotoActivity.this,"Fotoğraf başarılı bir şekilde kaydedildi.", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }).addOnFailureListener(new OnFailureListener() { //fotoğraf kaydedilemezse neler yapılacağını tanılmıyoruz
                @Override
                public void onFailure(@NonNull Exception e) {
                    dismissProgressDialog();
                    Toast.makeText(AddPhotoActivity.this, "Fotoğraf kaydedilemedi.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void showProgressDialog(){
        progressDialog = new ProgressDialog(AddPhotoActivity.this);
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    private void dismissProgressDialog(){
        progressDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //oncreate'in içinden çağırılan secmefonksiyonundan sonra çalışıyor
        //secme fonskiyonunda startActivityForResult ile başlattığımız activity başarılı olursa buraya geliyor.
        //galeriden seçeceğimiz resmi filepathinden alma işlemi de burada gerçekleşiyor.
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null){
            filepath = data.getData();
            try {
                Picasso.with(AddPhotoActivity.this).load(filepath).fit().centerCrop().into(userPhoto);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void showPhoto(){ //storage a kaydetmiş olduğumuz fotoğrafı storagedan çekip tanımladığımız imageViewa basıyor
        //telefonda basmadı
        //fotoğrafın boyutundan kaynaklı olabilirmiş
        showProgressDialog();
        StorageReference storageRef = firebaseStorage.getReference();
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                dismissProgressDialog();
                Picasso.with(AddPhotoActivity.this).load(uri).fit().centerCrop().into(userPhoto); //imageviewa basma işlemi burada
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dismissProgressDialog();
                Toast.makeText(AddPhotoActivity.this,"Fotoğraf yükleme işlemi başarısız.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
