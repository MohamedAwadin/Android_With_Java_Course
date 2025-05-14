package com.example.lab1_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView txtMessage ;
    TextView txtMobile ;

    Button btnBack ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        txtMobile = findViewById(R.id.txtMobile);
        txtMessage = findViewById(R.id.txtMessage);
        btnBack = findViewById(R.id.btn_back);

        Intent incomingIntent = getIntent();
        String mobile= incomingIntent.getStringExtra("MOBILE_NUM");
        String msg= incomingIntent.getStringExtra("MESSAGE");

        txtMobile.setText(mobile);
        txtMessage.setText(msg);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}