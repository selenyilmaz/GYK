package com.example.gyk_401_1;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.gyk_401_1.fragments.DietFragment;
import com.example.gyk_401_1.fragments.ExerciseFragment;
import com.example.gyk_401_1.fragments.HealthFragment;
import com.example.gyk_401_1.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hangi xml ile ilişkili olduğunu setContentten anlıyoruz.
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //default olarak uygulama açıldığında ilk home fragment gelsin diye tanımladık
        //frame layout fragmentı üstüne koymamıza yarıyor
        //add yaparsak uygulama şişebilir. onun yerine o an olan fragmenti replace ederek gelmesini istedğimiz fragmentin gelmesini sağlıyoruz
        //add kullansaydık devamlı add-remove ikilisi çalışmalıydı. replacele buna gerek kalmıyor. (onAttach onDetach replace ile beraber çalışır)
        transaction.replace(R.id.frameLayout, new HomeFragment());
        //bölünmemesi veya interrupt edilmemesi gereken işlemler için kullanılır.
        transaction.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) { //bu method sayesinde hangisine tıklandığındığı bilgisini alıyoruz.
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_health:
                    selectedFragment = new HealthFragment();
                    break;
                case R.id.navigation_diet:
                    selectedFragment = new DietFragment(); //hocanınkinde DietList fragment
                    break;
                case R.id.navigation_exercise:
                    selectedFragment = new ExerciseFragment();
                    break;
            }
            //start activity gibi fragmenti başlatıyoruz
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, selectedFragment);
            transaction.commit();
            return true;
        }
    };
}
