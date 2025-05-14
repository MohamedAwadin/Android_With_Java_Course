package com.example.lab5_01_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product , parent , false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.textViewDescription.setText(product.getDescription());
        holder.ratingBar.setRating(product.getRating());

        new DownloadImageTask(holder.thumbnailImageView).execute(product.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private static class DownloadImageTask extends AsyncTask<String , Void , Bitmap>{
        private ImageView imageView ;


        public DownloadImageTask(ImageView imageView){
            this.imageView = imageView ;
        }
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
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return  null ;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewPrice, textViewDescription;
        RatingBar ratingBar;
        ImageView thumbnailImageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
        }
    }
}
