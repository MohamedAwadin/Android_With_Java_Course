package com.example.lab8_01.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.lab8_01.model.Product;
import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM favorite_products")
    LiveData<List<Product>> getAllFavoriteProducts();

    @Query("SELECT * FROM favorite_products WHERE id = :productId LIMIT 1")
    Product getProductById(int productId);

    @Query("SELECT id FROM favorite_products")
    LiveData<List<Integer>> getFavoriteProductIds();
}