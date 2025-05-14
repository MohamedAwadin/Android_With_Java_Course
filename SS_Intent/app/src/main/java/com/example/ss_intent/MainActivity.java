package com.example.ss_intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button b_URL = findViewById(R.id.buttonURL);
        EditText ed_URL = findViewById(R.id.edtURL);

        b_URL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse(ed_URL.getText().toString());
                Intent intent_url = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent_url);
            }
        });

        Button b_dial  = findViewById(R.id.buttonPhone);
        EditText ed_dial = findViewById(R.id.edtPhone);

        b_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:"+ed_dial.getText().toString());
                Intent intent_uri = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent_uri);
            }
        });

        Button b_loc  = findViewById(R.id.buttonLoc);
        EditText ed_loc = findViewById(R.id.edtLoc);

        b_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:0,0?q="+ed_loc.getText().toString());
                Intent intent_uri = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent_uri);
            }
        });


        Button b_share  = findViewById(R.id.buttonShare);
        EditText ed_share = findViewById(R.id.edtShare);

        b_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder
                        .from(MainActivity.this)
                        .setType("text/plain")
                        .setText(ed_share.getText().toString())
                        .startChooser();
            }
        });

        Button b_aactv = findViewById(R.id.buttonAActivity);

        b_aactv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_aactv = new Intent(MainActivity.this, AnotherActivity.class);
                startActivity(i_aactv);
            }
        });


    }

}