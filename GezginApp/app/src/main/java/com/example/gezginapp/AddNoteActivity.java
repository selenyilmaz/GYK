package com.example.gezginapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteActivity extends AppCompatActivity {
    EditText girilenNot;
    Button ekleButton;
    Button listeleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        girilenNot = (EditText) findViewById(R.id.notYaz);
        ekleButton = (Button) findViewById(R.id.notEkle);
        listeleButton = (Button) findViewById(R.id.notlariGoster);

        ekleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notEkleFonksiyonu();
            }
        });

        listeleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notListeleFonksiyonu();
            }
        });
    }

    private void notEkleFonksiyonu(){
        //FirebaseDatabase nesnelerini kullanabilmek için getInstance kullandık
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //child olusturarak gezdiğim yerler adında bir tablo oluşturuyoruz
        //daha önce oluşturulmamışsa oluşturduk. oluşmuşsa ise referansı ile o tabloya ulaşır
        DatabaseReference myRef = firebaseDatabase.getReference().child("GezdigimYerler");
       //eklediğimiz notlara id atadık
        String notesId = myRef.push().getKey();
        //kullanıcı tarafından girilen notu aldık
        String notYaz = girilenNot.getText().toString();

        if (notYaz.length() > 0) {
            //yukarıda oluşturduğumuz GezdiğimYerler tablosunun altına sehirAdi diye bir bölüm daha oluşturuyoruz
            //girilen veriyi veritabanına ekiyoruz
            myRef.child(notesId).child("sehirAdi").setValue(notYaz);
            showDialog("Başarılı" , "Notunuz kaydedildi!");

        }
        else{
            showDialog("İşlem Başarısız", "Not alanı boş bırakılamaz.");
        }
        //girilen notu aldıktan sonra o alanı temizliyoruz
        girilenNot.setText("");
    }

    private void notListeleFonksiyonu(){

    }
    //yukarıda kullandığımız showDialog fonksiyonu
    private void showDialog(String title, String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("TAMAM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
