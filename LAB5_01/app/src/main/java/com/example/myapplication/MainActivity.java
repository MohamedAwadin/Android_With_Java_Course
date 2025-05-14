package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button btnDown;
    EditText edtURL;
    ImageView ImgViw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnDown = findViewById(R.id.button);
        edtURL = findViewById(R.id.editTextText);
        ImgViw = findViewById(R.id.imageView);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtURL.getText().toString();
                if (!url.isEmpty()) {
                    new DownloadImageTask().execute(url);
                }
            }
        });
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            String imgurl = urls[0];
            try {
                return download(imgurl);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                ImgViw.setImageBitmap(result);
            }
        }
    }

    public Bitmap download(String imgurl) throws IOException {
        Bitmap result = null;
        URL url = new URL(imgurl);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            InputStream is = connection.getInputStream();
            result = BitmapFactory.decodeStream(is);
            is.close();
            connection.disconnect();
        }
        return result;
    }
}