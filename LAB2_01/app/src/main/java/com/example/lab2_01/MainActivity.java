package com.example.lab2_01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Communicator {
    DynamicFragment dynamicFragment;
    boolean isFragmentVisible = true; // Track visibility state
    private static final String TAG = "MainActivity";

    FragmentManager mgr ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mgr = getSupportFragmentManager();

        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreated Called");
        setContentView(R.layout.activity_main);

        mgr = getSupportFragmentManager();
        if (savedInstanceState == null) {
            dynamicFragment = new DynamicFragment();
            FragmentTransaction transaction = mgr.beginTransaction();
            transaction.add(R.id.DynamicContainerView2, dynamicFragment, "DynamicFragment");
            transaction.commit();
        } else {
            dynamicFragment = (DynamicFragment) mgr.findFragmentByTag("DynamicFragment");
        }

        Button btnAdd = findViewById(R.id.btnADD);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = mgr.beginTransaction();
                if (isFragmentVisible) {
                    transaction.hide(dynamicFragment);
                    btnAdd.setText("Show Fragment");
                } else {
                    transaction.show(dynamicFragment);
                    btnAdd.setText("Hide Fragment");
                }
                transaction.commit();
                isFragmentVisible = !isFragmentVisible; // Toggle state
            }
        });
    }

    @Override
    public void count(int Counter) {

        dynamicFragment.updateCounter(Counter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called");
    }



}