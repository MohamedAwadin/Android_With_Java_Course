package com.example.lab7_01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView errorTextView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);


        // Set up RecyclerView for horizontal scrolling
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Add PagerSnapHelper for snapping to each item
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        // Show ProgressBar while loading
        progressBar.setVisibility(View.VISIBLE);

        // Set up Retrofit
        final String BASE_URL = "https://dummyjson.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Fetch products using Retrofit
        Call<ProductResponse> call = apiService.getProducts();
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                progressBar.setVisibility(View.GONE);
//                Log.d("MainActivity", "Response Code: " + response.code());
//                Log.d("MainActivity", "Response Body: " + response.body());
//                Log.d("MainActivity", "Response Success: " + response.isSuccessful());
//                if (response.body() != null) {
//                    Log.d("MainActivity", "Product List Size: " + response.body().getProducts().size());
//                }

                if (response.isSuccessful() && response.body() != null && response.body().getProducts() != null && !response.body().getProducts().isEmpty()) {
                    productList.clear();
                    productList.addAll(response.body().getProducts());
                    adapter.notifyDataSetChanged();

                } else {

                    if (!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "HTTP Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    } else if (response.body() == null) {
                        Toast.makeText(MainActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No products found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

                Log.e("MainActivity", "Request Failed: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage() + ". Tap to retry", Toast.LENGTH_LONG).show();
                recyclerView.setOnClickListener(v -> {
                    progressBar.setVisibility(View.VISIBLE);
                    call.clone().enqueue(this);
                });
            }
        });
    }
}