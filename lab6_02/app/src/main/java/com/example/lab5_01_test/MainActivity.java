package com.example.lab5_01_test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
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
    private ProductAdapter adapter;
    private List<Product> productList;

    private static final int MSG_UPDATE_UI = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Add PagerSnapHelper for snapping to each item
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MSG_UPDATE_UI) {
                    progressBar.setVisibility(View.GONE);
                    if (!productList.isEmpty()) {
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        progressBar.setVisibility(View.VISIBLE);

        new Thread(() -> {
            try {
                // a: Connect to the API
                URL url = new URL("https://dummyjson.com/products");
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                    // b: Streams
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

                    // c: Parsing
                    JSONObject root = new JSONObject(jsonString.toString());
                    JSONArray jsonArray = root.getJSONArray("products");

                    // d & e: Convert JSON to POJO and fill products array
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        double price = jsonObject.getDouble("price");
                        String description = jsonObject.getString("description");
                        float rating = (float) jsonObject.getDouble("rating");
                        String thumbnail = jsonObject.getString("thumbnail");
                        productList.add(new Product(title, price, description, rating, thumbnail));
                    }
                }

                // f: Send message to update UI
                handler.sendMessage(handler.obtainMessage(MSG_UPDATE_UI));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                handler.sendMessage(handler.obtainMessage(MSG_UPDATE_UI));
            }
        }).start();
    }
}