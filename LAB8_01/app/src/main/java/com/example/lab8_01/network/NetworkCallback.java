package com.example.lab8_01.network;

import com.example.lab8_01.model.ProductResponse;

public interface NetworkCallback {

    void onSuccessResult(ProductResponse result);
    void onFailureResult(String message);
}
