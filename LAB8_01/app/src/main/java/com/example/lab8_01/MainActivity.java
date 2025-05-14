package com.example.lab8_01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab8_01.database.AppDatabase;
import com.example.lab8_01.database.ProductDao;
import com.example.lab8_01.model.Product;

public class MainActivity extends AppCompatActivity {

    private Button btn_Shw_allProducts;
    private Button btn_shw_favProducts;
    private Button btn_exit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn_Shw_allProducts = findViewById(R.id.btn_getAll);
        btn_shw_favProducts = findViewById(R.id.btn_getFav);
        btn_exit=findViewById(R.id.btn_Exit);


        btn_Shw_allProducts.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this , AllProductsActivity.class));
        });

        btn_shw_favProducts.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this , FavoriteProductsActivity.class));
        });

        btn_exit.setOnClickListener(v -> {
            finish();
        });









    }
}