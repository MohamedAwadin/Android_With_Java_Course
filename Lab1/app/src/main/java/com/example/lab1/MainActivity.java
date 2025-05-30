package com.example.lab1;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG , "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG , "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG , "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG , "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG , "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG , "onDestroy: ");
    }


}
