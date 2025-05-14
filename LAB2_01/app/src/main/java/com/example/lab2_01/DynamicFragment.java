package com.example.lab2_01;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DynamicFragment extends Fragment {
    private static final String TAG = "DynamicFragment";

    private TextView txt;
    private int pendingCounter = 0;
    private boolean isViewCreated = false;

    public DynamicFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate called");

        if (savedInstanceState != null) {
            pendingCounter = savedInstanceState.getInt("pendingCounter", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView called");
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated called");
        txt = view.findViewById(R.id.txtcounter);
        isViewCreated = true;
        if (pendingCounter > 0) {
            updateCounter(pendingCounter);
        }
    }

    public void updateCounter(int counter) {
        pendingCounter = counter;
        if (isViewCreated && txt != null) {
            txt.setText("" + counter);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("pendingCounter", pendingCounter);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView called");
        isViewCreated = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy called");
    }
}