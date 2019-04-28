package com.example.gezginapp.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.gezginapp.AddNoteActivity;
import com.example.gezginapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyNotesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyNotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyNotesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String myPlaces;
    Button ekle;
    ListView notlar_lv;
    ArrayAdapter arrayAdapter;
    ArrayList<String > arrayList = new ArrayList<String>();
    private ProgressDialog progressDialog;
    //Button deneme;
    //ImageView foto;
    private OnFragmentInteractionListener mListener;

    public MyNotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyNotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyNotesFragment newInstance(String param1, String param2) {
        MyNotesFragment fragment = new MyNotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        arrayList = getMyNotes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_notes, container, false);
        /*deneme = view.findViewById(R.id.denemeBtn);
        foto = view.findViewById(R.id.fotik);*/
        ekle = view.findViewById(R.id.ekle);
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });
        notlar_lv = view.findViewById(R.id.notlar_lv);
        arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        notlar_lv.setAdapter(arrayAdapter);
        return view;
    }
    private ArrayList<String> getMyNotes(){
        showProgressDialog();
        final ArrayList<String> myNotes = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("GezdigimYerler");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                //burdan veritabanına yazılmış notları Listviewa çekiyoruz
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    myPlaces = ds.child("sehirAdi").getValue().toString();
                    myNotes.add(myPlaces);
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

        return myNotes;
    }
    private void showProgressDialog(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
