package com.example.lab8_01.network;

import com.example.lab8_01.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<ProductResponse> getProducts();

}
