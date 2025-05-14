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
import com.example.lab8_01.model.Product;
import java.util.List;

public class FavoriteProductAdapter extends RecyclerView.Adapter<FavoriteProductAdapter.FavoriteViewHolder> {
    private List<Product> favoriteList;
    private OnDeleteClickListener listener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Product product);
    }

    public FavoriteProductAdapter(List<Product> favoriteList, OnDeleteClickListener listener) {
        this.favoriteList = favoriteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_product, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Product product = favoriteList.get(position);
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.textViewDescription.setText(product.getDescription());
        holder.ratingBar.setRating(product.getRating());
        Glide.with(holder.thumbnailImageView.getContext())
                .load(product.getThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.thumbnailImageView);

        holder.buttonDelete.setOnClickListener(v -> listener.onDeleteClick(product));
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewPrice, textViewDescription;
        RatingBar ratingBar;
        ImageView thumbnailImageView;
        Button buttonDelete;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}