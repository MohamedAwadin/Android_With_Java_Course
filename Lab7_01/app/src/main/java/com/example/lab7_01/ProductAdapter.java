package com.example.lab7_01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.textViewDescription.setText(product.getDescription());
        holder.ratingBar.setRating(product.getRating());

        Glide.with(holder.thumbnailImageView.getContext())
                .load(product.getThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
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

//package com.example.lab7_01;




//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//import com.bumptech.glide.Glide;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
//
//import javax.net.ssl.HttpsURLConnection;
//
//public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
//    private List<Product> productList;
//
//    public ProductAdapter(List<Product> productList) {
//        this.productList = productList;
//    }
//
//    @NonNull
//    @Override
//    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product , parent , false);
//        return new ProductViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
//        Product product = productList.get(position);
//        holder.textViewTitle.setText(product.getTitle());
//        holder.textViewPrice.setText(String.format("$%.2f", product.getPrice()));
//        holder.textViewDescription.setText(product.getDescription());
//        holder.ratingBar.setRating(product.getRating());
//
//        Glide.with(holder.thumbnailImageView.getContext())
//                .load(product.getThumbnail())
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(holder.thumbnailImageView);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//
//
//    public class ProductViewHolder extends RecyclerView.ViewHolder{
//        TextView textViewTitle, textViewPrice, textViewDescription;
//        RatingBar ratingBar;
//        ImageView thumbnailImageView;
//
//        public ProductViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewTitle = itemView.findViewById(R.id.textViewTitle);
//            textViewPrice = itemView.findViewById(R.id.textViewPrice);
//            textViewDescription = itemView.findViewById(R.id.textViewDescription);
//            ratingBar = itemView.findViewById(R.id.ratingBar);
//            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
//        }
//    }
//}
