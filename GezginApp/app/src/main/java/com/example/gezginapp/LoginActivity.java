package com.example.gezginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private Button girisButton;
    private Button kayitButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = (EditText) findViewById(R.id.mailAdress);
        userPassword = (EditText) findViewById(R.id.password);
        girisButton = (Button) findViewById(R.id.girisButton);
        kayitButton = (Button) findViewById(R.id.registrationButton);
        //mAuth ile firebase kütüphanesinden yararlanabilmek için getInstance yaptık
        mAuth = FirebaseAuth.getInstance();

        kayitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kayit için intent ile kullanıcıyı kayıt ekranına yönlendirdik
                //kontrol etmek için fonksiyon da kullanılabilir
                Intent kayiIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(kayiIntent);
            }
        });

        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trim ile sondaki ve baştaki boşlukları sildik
                String girilenMail = userEmail.getText().toString().trim();
                String girilenParola = userPassword.getText().toString().trim();

                if(!girilenMail.isEmpty() && !girilenParola.isEmpty()){
                    //girisYap fonksiyonu ile girilen bilgileri kontrol edicez
                    girisYap(girilenMail, girilenParola);
                }

                else{
                    Toast.makeText(getApplicationContext(),"Email ya da parola alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void girisYap(String girilenMail, String girilenParola){
        //fonksiyona parametre olarak gelen mail ve şifreyi firebaseden mAuth nesnesi ile kontrol ediyoruz
        //eğer var ise ve credentiallar doğru ise MainActivitye yönlendiren bir intent başlatıp LoginActivityi finish() ile kapatıyoruz
        mAuth.signInWithEmailAndPassword(girilenMail,girilenParola).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                    Log.d("Email", "signInWithEmail: success");
                }
                else{
                    Log.d("Email", "signInWithEmail: failed");
                }
            }
        });
    }
}
