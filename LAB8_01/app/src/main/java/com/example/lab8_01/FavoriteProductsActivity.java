package com.example.lab8_01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab8_01.database.ProductRepository;
import com.example.lab8_01.model.Product;
import java.util.ArrayList;
import java.util.List;

public class FavoriteProductsActivity extends AppCompatActivity {
    private static final String TAG = "FavoriteProductsActivity";
    private RecyclerView recyclerViewFavorites;
    private TextView emptyTextView;
    private Button buttonBack;
    private FavoriteProductAdapter favoriteAdapter;
    private List<Product> favoriteList;
    private ProductRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_products);

        recyclerViewFavorites = findViewById(R.id.recyclerViewFavorites);
        emptyTextView = findViewById(R.id.emptyTextView);
        buttonBack = findViewById(R.id.buttonBack);

        favoriteList = new ArrayList<>();
        favoriteAdapter = new FavoriteProductAdapter(favoriteList, product -> {
            repository.delete(product);
        });
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFavorites.setAdapter(favoriteAdapter);

        repository = new ProductRepository(getApplication());

        repository.getFavoriteProducts().observe(this, products -> {
            Log.d(TAG, "LiveData emitted: " + (products != null ? products.size() + " products" : "null"));
            favoriteList.clear();
            favoriteList.addAll(products != null ? products : new ArrayList<>());
            favoriteAdapter.notifyDataSetChanged();
            emptyTextView.setVisibility(favoriteList.isEmpty() ? View.VISIBLE : View.GONE);
        });

        buttonBack.setOnClickListener(v -> {
            startActivity(new Intent(FavoriteProductsActivity.this, MainActivity.class));
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository.shutdown();
    }
}