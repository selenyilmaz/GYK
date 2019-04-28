package com.example.loginproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Musteriler extends AppCompatActivity {
    String cekilenMusteri;
    EditText customerInfo;
    Button addCustomerBtn;
    ListView listViewCustomer;
    ArrayAdapter arrayAdapter;
    ArrayList<String > customerList = new ArrayList<String>();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteriler);

        customerInfo = findViewById(R.id.musteriBilgisi);
        addCustomerBtn = findViewById(R.id.ekleButon);
        listViewCustomer = findViewById(R.id.musterilerLV);
        customerList = listCustomer();

         arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , android.R.id.text1 , customerList);
        listViewCustomer.setAdapter(arrayAdapter);

        listViewCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Musteriler.this);
                builder.setTitle("Müşteri silme.");  //başlığını tanımlıyoruz

                builder.setMessage("Müşteri silinsin mi?").setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCustomer(customerList.get(position));
                        //Log.e("AD:", customerList.get(position));
                    }
                }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        addCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musteriEkleFonksiyonu();
            }
        });

    }
    private void deleteCustomer(String silinecekMusteri){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query deleteQuery = myRef.child("MusteriBilgileri").orderByChild("musteriAdi").equalTo(silinecekMusteri);

        deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Tag", "onCancelled", databaseError.toException());
            }
        });

    }
    private void musteriEkleFonksiyonu(){
        //FirebaseDatabase nesnelerini kullanabilmek için getInstance kullandık
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //child olusturarak gezdiğim yerler adında bir tablo oluşturuyoruz
        //daha önce oluşturulmamışsa oluşturduk. oluşmuşsa ise referansı ile o tabloya ulaşır
        DatabaseReference myRef = firebaseDatabase.getReference().child("MusteriBilgileri");
        //eklediğimiz notlara id atadık
        String customerId = myRef.push().getKey();
        //kullanıcı tarafından girilen notu aldık
        String customerNames = customerInfo.getText().toString();

        if (customerNames.length() > 0) {
            //yukarıda oluşturduğumuz GezdiğimYerler tablosunun altına sehirAdi diye bir bölüm daha oluşturuyoruz
            //girilen veriyi veritabanına ekiyoruz
            myRef.child(customerId).child("musteriAdi").setValue(customerNames);
            showDialog("Başarılı" , "Müşteri kaydedildi!");

        }
        else{
            showDialog("İşlem Başarısız", "İsim alanı boş bırakılamaz.");
        }
        //girilen notu aldıktan sonra o alanı temizliyoruz
        customerInfo.setText("");
    }

    private void showDialog(String title, String message){

        final AlertDialog.Builder builder = new AlertDialog.Builder(Musteriler.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("TAMAM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

    private ArrayList<String> listCustomer(){ //müşterileri listemelek için
        showProgressDialog();

        final ArrayList<String> customers = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("MusteriBilgileri");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                //burdan veritabanına yazılmış notları Listviewa çekiyoruz
                customers.clear(); //for döngüsü içerisinde databaseden cekilen müşteri değişkene müşteriler teker teker alınıyor.
                //customers değişkenini sıfırlamazsak databaseden her seferinde baştan sona müşterileri okuyarak customers listesine ekler
                //yeni müşteri eklendikçe listeyi baştan tekrar yazar
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.child("musteriAdi").getValue() != null) {
                        cekilenMusteri = ds.child("musteriAdi").getValue().toString();
                        customers.add(cekilenMusteri);
                    }
                }
                //databasede bir değişiklik yapılırsa arrayListi de bundan haberdar ediyoruz
                //ortak kullanılan databasede kullanışlı
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        return customers;
    }
    private void showProgressDialog(){
        progressDialog = new ProgressDialog(Musteriler.this);
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


}
