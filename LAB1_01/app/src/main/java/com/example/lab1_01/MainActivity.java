package com.example.lab1_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnNext ;
    private Button btnExit ;

    private EditText edtMessage;

    private EditText edtPhone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnExit = findViewById(R.id.btnExit);
        btnNext = findViewById(R.id.btnNext);
        edtMessage = findViewById(R.id.edtMessage);
        edtPhone = findViewById(R.id.edtPhone);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this , SecondActivity.class);
                Intent intent = new Intent(getApplicationContext() , SecondActivity.class);
                intent.putExtra("MOBILE_NUM",edtPhone.getText().toString());
                intent.putExtra("MESSAGE" , edtMessage.getText().toString());
                startActivity(intent);
            }
        });





    }

    public void exit(View view) {
        finish();
    }
}