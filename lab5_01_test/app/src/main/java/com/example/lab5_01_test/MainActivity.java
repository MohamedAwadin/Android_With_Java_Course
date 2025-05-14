package com.example.lab5_01_test;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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
    private TextView textViewTitle, textViewPrice, textViewDescription; // Updated to TextView
    private RatingBar ratingBar;
    private ImageView imageView;
    private Button btnNext, btnBack;
    private List<Product> productList;
    private int currentIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewTitle = findViewById(R.id.textView9);
        textViewPrice = findViewById(R.id.textView10);
        textViewDescription = findViewById(R.id.textView11);
        ratingBar = findViewById(R.id.ratingBar);
        imageView = findViewById(R.id.imageView);
        btnNext = findViewById(R.id.button2);
        btnBack = findViewById(R.id.button);

        productList = new ArrayList<>();

        ratingBar.setEnabled(false);


        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    if (!productList.isEmpty()) {
                        updateUI(0); // Display the first product
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };


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
                    StringBuilder json_String = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        json_String.append(line);
                    }
                    reader.close();
                    inputStream.close();
                    connection.disconnect();

                    // c: Parsing
                    JSONObject root = new JSONObject(json_String.toString());
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
                handler.sendMessage(handler.obtainMessage(1));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                handler.sendMessage(handler.obtainMessage(1)); // Update UI even if there's an error
            }
        }).start();

        // Step 3: btnNext onClickListener
        btnNext.setOnClickListener(v -> {
            if (!productList.isEmpty()) {
                if (currentIndex < productList.size() - 1) {
                    currentIndex++;
                    updateUI(currentIndex);
                } else {
                    Toast.makeText(MainActivity.this, "No more products", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Step 4: btnBack onClickListener
        btnBack.setOnClickListener(v -> {
            if (!productList.isEmpty()) {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateUI(currentIndex);
                } else {
                    Toast.makeText(MainActivity.this, "This is the first product", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to update the UI with the product at the given index
    private void updateUI(int index) {
        Product product = productList.get(index);
        textViewTitle.setText(product.getTitle());
        textViewPrice.setText(String.format("$%.2f", product.getPrice()));
        textViewDescription.setText(product.getDescription());
        ratingBar.setRating(product.getRating());

        // Download and set the thumbnail image
        new DownloadImageTask().execute(product.getThumbnail());
    }

    // AsyncTask to download the thumbnail image
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            String imgUrl = urls[0];
            try {
                URL url = new URL(imgUrl);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                    InputStream is = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    connection.disconnect();
                    return bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                imageView.setImageBitmap(result);
            } else {
                Toast.makeText(MainActivity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}