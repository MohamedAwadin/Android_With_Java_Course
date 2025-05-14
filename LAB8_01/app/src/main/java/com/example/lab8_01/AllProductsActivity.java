package com.example.lab8_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab8_01.database.ProductRepository;
import com.example.lab8_01.model.Product;
import com.example.lab8_01.model.ProductResponse;
import com.example.lab8_01.network.NetworkCallback;
import com.example.lab8_01.network.RetrofitClient;
import java.util.ArrayList;
import java.util.List;

public class AllProductsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private Button buttonBack;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Integer> favoriteProductIds;
    private ProductRepository repository;
    private RetrofitClient retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        buttonBack = findViewById(R.id.buttonBack);

        productList = new ArrayList<>();
        favoriteProductIds = new ArrayList<>(); // Initialize with empty list
        repository = new ProductRepository(getApplication());
        productAdapter = new ProductAdapter(productList, product -> {
            repository.insert(product, success -> {
                runOnUiThread(() -> {
                    if (success) {
                        Toast.makeText(this, product.getTitle() + " added to favorites", Toast.LENGTH_SHORT).show();
                        // Favorite product IDs will be updated via LiveData
                    } else {
                        Toast.makeText(this, product.getTitle() + " is already in favorites", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        }, favoriteProductIds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        // Observe favorite product IDs
        repository.getFavoriteProductIds().observe(this, ids -> {
            favoriteProductIds = ids != null ? ids : new ArrayList<>();
            productAdapter.updateFavoriteProductIds(favoriteProductIds);
        });

        retrofitClient = new RetrofitClient();

        buttonBack.setOnClickListener(v -> {
            startActivity(new Intent(AllProductsActivity.this, MainActivity.class));
            finish();
        });

        fetchProducts();
    }

    private void fetchProducts() {
        progressBar.setVisibility(View.VISIBLE);


        retrofitClient.makeNetworkCall(new NetworkCallback() {
            @Override
            public void onSuccessResult(ProductResponse result) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    if (result != null && result.getProducts() != null) {
                        productList.clear();
                        productList.addAll(result.getProducts());
                        productAdapter.notifyDataSetChanged();
                    } else {

                        Toast.makeText(AllProductsActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailureResult(String message) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(AllProductsActivity.this, "Error: " + message, Toast.LENGTH_LONG).show();
                    recyclerView.setOnClickListener(v -> fetchProducts());
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository.shutdown();
    }
}