package com.example.lab1_01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    private static final String PREF_FILE = "PREF_FILE";

    private static final String PRIV_FILE = "PRIV_FILE";

    public static final String MOBILE_NUMBER = "MOBILE_NUM";

    public static final String MESSAGE = "MESSAGE";

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

        Button btnWSH = findViewById(R.id.btnWSH);
        Button btnRSH = findViewById(R.id.btnRSH);

        Button btnWIS = findViewById(R.id.btnWIS);
        Button btnRIS = findViewById(R.id.btnRIS);

        Button btnWDB = findViewById(R.id.btnWDB);
        Button btnRDB = findViewById(R.id.btnRDB);


        Intent incomingIntent = getIntent();
        String mobile= incomingIntent.getStringExtra(SecondActivity.MOBILE_NUMBER);
        String msg= incomingIntent.getStringExtra(SecondActivity.MESSAGE);

        txtMobile.setText(mobile);
        txtMessage.setText(msg);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnWSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(SecondActivity.PREF_FILE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString(SecondActivity.MOBILE_NUMBER, txtMobile.getText().toString());
                editor.putString(SecondActivity.MESSAGE, txtMessage.getText().toString());
                editor.commit();

                txtMessage.setText("");
                txtMobile.setText("");


            }
        });


        btnRSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(SecondActivity.PREF_FILE, Context.MODE_PRIVATE);


                txtMobile.setText(preferences.getString(SecondActivity.MOBILE_NUMBER, getString(R.string.n_a)));
                txtMessage.setText(preferences.getString(SecondActivity.MESSAGE, getString(R.string.n_a)));


            }
        });

        btnWIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(SecondActivity.PRIV_FILE, Context.MODE_PRIVATE);
                    DataOutputStream dos = new DataOutputStream(fos);
                    dos.writeUTF(txtMobile.getText().toString());
                    dos.writeUTF(txtMessage.getText().toString());
                    dos.close();
                    fos.close();
                    txtMobile.setText("");
                    txtMessage.setText("");
                } catch (FileNotFoundException e) {
                    Toast.makeText(SecondActivity.this , "Couldn't Locate This File", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(SecondActivity.this , "Couldn't Create This File", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null ;

                try {
                    fis = openFileInput(SecondActivity.PRIV_FILE);
                    DataInputStream dis = new DataInputStream(fis);




                    txtMessage.setText(dis.readUTF());




                } catch (FileNotFoundException e) {
                    Toast.makeText(SecondActivity.this , "Couldn't Locate This File", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(SecondActivity.this , "Couldn't Read This File", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWDB.setOnClickListener(v -> {
            MsgAdapter adapter = new MsgAdapter(this);
            long id = adapter.insertMessage(new Message(txtMobile.getText().toString(), txtMessage.getText().toString()));

            txtMessage.setText(" ");
        });

        btnRDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgAdapter adapter = new MsgAdapter(SecondActivity.this);
                Message msg = adapter.findMessage(txtMobile.getText().toString());
                txtMessage.setText(msg.getMessage());

            }
        });








    }
}