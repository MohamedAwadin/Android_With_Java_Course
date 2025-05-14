package com.example.lab8_01.network;

import android.util.Log;

import com.example.lab8_01.model.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://dummyjson.com/";

    private static final String TAG = RetrofitClient.class.getSimpleName();


    private static Retrofit retrofit = null ;





    public static ApiService getApiService(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }


    public void makeNetworkCall(NetworkCallback networkCallback){
        Call<ProductResponse> call = getApiService().getProducts();
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    networkCallback.onSuccessResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable throwable) {

                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();

            }
        });
    }

}
