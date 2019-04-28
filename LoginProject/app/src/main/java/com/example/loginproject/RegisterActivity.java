package com.example.loginproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText newUserMail;
    private EditText newUserPassword;
    private EditText newUserPasswordCheck;
    private Button newUserRegBtn;

    //kullanıcının girdiği değerleri almak için
    private String girilenMail;
    private String girilenParola;
    private String girilenParolaTekrar;

    //Firebase kütüphanesi ile işlem yapabilmek için nesne oluşturduk
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newUserMail = findViewById(R.id.yeniEmail);
        newUserPassword = findViewById(R.id.yeniParola);
        newUserPasswordCheck = findViewById(R.id.yeniParolaKontrol);
        newUserRegBtn = findViewById(R.id.yeniKayit);

        //nesne ile işlemleri gerçekleştirebilmek için
        mAuth = FirebaseAuth.getInstance();

        newUserRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kullanıcının girdiği değerlerin uygunluğunu kontrol etmek için kontrol fonksiyonunu kullanıyoruz
                kontrol();
            }
        });
    }

    //girilenMail ve parolayı bu class içinde gloabl tanımladığımız için parametre olarak atamamıza gerek yok
    private void kontrol(){
        //kullanıcı tarafından girilen verileri kendi tanımladıklarımıza atıyoruz
        girilenMail = newUserMail.getText().toString().trim();
        girilenParola = newUserPassword.getText().toString().trim();
        girilenParolaTekrar = newUserPasswordCheck.getText().toString().trim();

        if(!girilenMail.isEmpty() && !girilenParola.isEmpty() && !girilenParolaTekrar.isEmpty()){
            //alanlardan hiçbiri boş değil ise kontrole başlıyoruz
            if(girilenParola.equals(girilenParolaTekrar)){
                kayitIslemi();
            }
        }
        else{//boş alan bırakıldıysa uyarı mesajı çıkıyor
            Toast.makeText(getApplicationContext(), "Kayıt için tüm alanları doldurunuz.", Toast.LENGTH_SHORT).show();
        }
    }
    private void kayitIslemi(){
        mAuth.createUserWithEmailAndPassword(girilenMail, girilenParola).addOnFailureListener(this, new OnFailureListener() {
            @Override //addOnFailureListener aynı email var mı onu kontrol edeceğiz
            public void onFailure(@NonNull Exception e) {
                if(e instanceof FirebaseAuthException){ //tespit edilen failurelara erişmek için e
                    //Firebase içinde tanımlı hata kodları ile kullanıcının girdiği mail ve parolaları kontrol ediyoruz
                    if(((FirebaseAuthException) e).getErrorCode().equals("ERROR_WEAK_PASSWORD")){
                        Toast.makeText(getApplicationContext(), "Eksik şifre!", Toast.LENGTH_SHORT).show();
                    }
                    if(((FirebaseAuthException) e).getErrorCode().equals("ERROR_INVALID_EMAIL")){
                        Toast.makeText(getApplicationContext(), "Geçersiz mail adresi!", Toast.LENGTH_SHORT).show();
                    }
                    if(((FirebaseAuthException) e).getErrorCode().equals("ERROR_EMAIL_ALREADY_IN_USE")){
                        Toast.makeText(getApplicationContext(), "Mail zaten kayıtlı!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){ //bunu anlamadım
                    //girilen veriler uygun ise firebaseuser ile yeni kullanıcı oluşturuyoruz
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), "Kayıt başarılı" , Toast.LENGTH_SHORT).show();
                    //kullanıcı oluşturulduktan sonra login ekranından giriş yapacak
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                    //klasik intent yöntemiyle de yapabiliriz
                }
            }

        });
    }

}
