package com.example.lab8_01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab8_01.database.ProductRepository;
import com.example.lab8_01.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private OnFavoriteClickListener listener;

    private List<Integer> favoriteProductIds;



    public interface OnFavoriteClickListener{
        void onFavoriteClick(Product product);
    }

    public ProductAdapter(List<Product> productList, OnFavoriteClickListener listener, List<Integer> favoriteProductIds) {

        this.productList = productList;
        this.listener = listener ;
        this.favoriteProductIds = favoriteProductIds;
    }

    public void updateFavoriteProductIds(List<Integer> newFavoriteProductIds) {
        this.favoriteProductIds = newFavoriteProductIds;
        notifyDataSetChanged();
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

        if (favoriteProductIds.contains(product.getId())) {
            holder.buttonFavorite.setEnabled(false);
            holder.buttonFavorite.setText("In Favorites");
        } else {
            holder.buttonFavorite.setEnabled(true);
            holder.buttonFavorite.setText("Add to Favorites");
        }

        holder.buttonFavorite.setOnClickListener(v -> listener.onFavoriteClick(product));
    }

    @Override
    public int getItemCount() {

        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewPrice, textViewDescription;
        RatingBar ratingBar;
        ImageView thumbnailImageView;

        Button buttonFavorite;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
            buttonFavorite = itemView.findViewById(R.id.buttonFavorite);
        }
    }
}

