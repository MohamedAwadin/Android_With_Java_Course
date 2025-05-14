package com.example.lab5_02;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private com.example.myapplication.ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        // Replace with your API URL
        String apiUrl = "https://your-api-url.com/products"; // Update with the actual URL
        new FetchProductsTask().execute(apiUrl);
    }

    // AsyncTask to fetch and parse JSON data
    private class FetchProductsTask extends AsyncTask<String, Void, List<Product>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Product> doInBackground(String... urls) {
            String apiUrl = urls[0];
            List<Product> products = new ArrayList<>();
            try {
                URL url = new URL(apiUrl);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder jsonString = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        jsonString.append(line);
                    }
                    reader.close();
                    inputStream.close();
                    connection.disconnect();

                    // Parse JSON
                    JSONObject root = new JSONObject(jsonString.toString());
                    JSONArray jsonArray = root.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        double price = jsonObject.getDouble("price");
                        String description = jsonObject.getString("description");
                        float rating = (float) jsonObject.getDouble("rating");
                        String thumbnail = jsonObject.getString("thumbnail");
                        products.add(new Product(title, price, description, rating, thumbnail));
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return products;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            progressBar.setVisibility(View.GONE);
            if (products != null && !products.isEmpty()) {
                productList.clear();
                productList.addAll(products);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch products", Toast.LENGTH_SHORT).show();
            }
        }
    }
}