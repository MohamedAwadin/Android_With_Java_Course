package com.example.lab8_01.database;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.lab8_01.model.Product;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepository {
    private static final String TAG = "ProductRepository";
    private ProductDao productDao;
    private LiveData<List<Product>> favoriteProducts;
    private final ExecutorService executorService;

    public ProductRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        productDao = db.productDao();
        favoriteProducts = productDao.getAllFavoriteProducts();
        executorService = Executors.newSingleThreadExecutor();
    }

    public interface InsertCallback {
        void onInsertResult(boolean success);
    }

    public void insert(Product product, InsertCallback callback) {
        executorService.execute(() -> {
            Product existingProduct = productDao.getProductById(product.getId());
            if (existingProduct != null) {
                Log.d(TAG, "Product with ID " + product.getId() + " already exists in favorites");
                callback.onInsertResult(false);
            } else {
                productDao.insert(product);
                Log.d(TAG, "Inserted product with ID " + product.getId() + " into favorites");
                callback.onInsertResult(true);
            }
        });
    }

    public void delete(Product product) {
        executorService.execute(() -> {
            productDao.delete(product);
            Log.d(TAG, "Deleted product with ID " + product.getId() + " from favorites");
        });
    }

    public LiveData<List<Product>> getFavoriteProducts() {
        return favoriteProducts;
    }

    public LiveData<List<Integer>> getFavoriteProductIds() {
        return productDao.getFavoriteProductIds();
    }

    public void shutdown() {
        executorService.shutdown();
    }
}