package com.example.lec_listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Corrected array syntax and fixed spelling mistakes
    String[] days = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    Cake[] cakes = {
            new Cake("Cake1" , "Cake1 Desc" , R.drawable.)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Corrected ListView ID (assuming 'listview' in layout)
        ListView listView = findViewById(R.id.listviw);

        // Corrected ArrayAdapter parameters
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                days
//        );
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                days
//        );

        Cake_Adapter adapter = new Cake_Adapter(this , cakes);

        listView.setAdapter(adapter);

        // Changed to setOnItemClickListener for ListView items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedDay = adapter.getItem(position);
//                Toast.makeText(MainActivity.this, selectedDay, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, adapter.getItem(id), Toast.LENGTH_SHORT).show();
            }
        });
    }
}