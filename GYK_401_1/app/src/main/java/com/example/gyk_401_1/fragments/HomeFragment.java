package com.example.gyk_401_1.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gyk_401_1.R;

public class HomeFragment extends Fragment {
    EditText userWeightEt;
    EditText userHeightEt;
    TextView userResultTv;
    Button calculateBtn;

    private OnFragmentInteractionListener mListener;

    public HomeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //bu java dosyasının görünümünün layout altındaki home_fragment olduğunu belirttik
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        //home_frament içinden alması gerektiği için view nesnesi ile çağırıyoruz
        userWeightEt = (EditText) view.findViewById(R.id.userWeight);
        userHeightEt = (EditText) view.findViewById(R.id.userHeight);
        userResultTv = (TextView) view.findViewById(R.id.userResult);

        calculateBtn = (Button) view.findViewById(R.id.calculateButton);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
        return view;
    }

    private void calculate(){
        if (userWeightEt.getText().toString().length() > 0 && userHeightEt.getText().toString().length() > 0){
            float userWeight = Float.parseFloat(userWeightEt.getText().toString());
            float userHeight = Float.parseFloat(userHeightEt.getText().toString());

            if(userHeight > 0){
                userHeight = userHeight/100;
                float result = (userWeight / ((userHeight * userHeight)));
                String resultDescription = "";

                if (result < 15){
                    resultDescription = "Aşırı Zayıf";
                }
                else if (result > 15 && result <= 30){
                    resultDescription = "Zayıf";
                }
                else if (result > 30 && result <= 40){
                    resultDescription = "Normal";
                }
                else if (result > 40 && result <= 50){
                    resultDescription = "Kilolu";
                }
                else
                    resultDescription = "Aşırı Kilolu (Morbid Obez)";

                userResultTv.setText("Vücut kitle endeksiniz: " + result + "\n" + resultDescription);

            }
        }

        else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Hata!");
            builder.setMessage("Kilo ya da boy boş bırakılamaz.");
            builder.setNegativeButton("TAMAM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.show();
        }
    }

    @Override
    public void onAttach(Context context) {
        //fragment ilk açıldığında çalışır
        super.onAttach(context);
    }

    @Override
    public void onDetach() { //killer
        //fragmenti kapatmak için çalışır.( mesela farklı fragmente geçerken)
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener{ //onDetachi null yapabilmek için
        void onFragmentInteraction(Uri uri);
    }
}
