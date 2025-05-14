package com.example.lab6_01;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


Cake[] cakes = {
        new Cake("Cake1" , "Cake1 Desc" , R.drawable.o),
        new Cake("Cake2" , "Cake2 Desc" , R.drawable.t),
        new Cake("Cake3" , "Cake3 Desc" , R.drawable.th),
        new Cake("Cake4" , "Cake4 Desc" , R.drawable.fo),
        new Cake("Cake5" , "Cake5 Desc" , R.drawable.fi),
        new Cake("Cake6" , "Cake6 Desc" , R.drawable.si),
        new Cake("Cake7" , "Cake7 Desc" , R.drawable.seven),
        new Cake("Cake8" , "Cake8 Desc" , R.drawable.eight),
        new Cake("Cake9" , "Cake9 Desc" , R.drawable.nine),
        new Cake("Cake10" , "Cake10 Desc" , R.drawable.ten),
        new Cake("Cake11" , "Cake11 Desc" , R.drawable.eleven),
        new Cake("Cake12" , "Cake12 Desc" , R.drawable.twelve)
};

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<Cake> input = Arrays.asList(cakes);



        RCake_Adapter adapter = new RCake_Adapter(this , input);
        recyclerView.setAdapter(adapter);


    }
}