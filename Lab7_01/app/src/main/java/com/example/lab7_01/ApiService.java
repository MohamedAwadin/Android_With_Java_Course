package com.example.lab7_01;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    //Call<List<Product>> getProducts();
    Call<ProductResponse> getProducts();
}