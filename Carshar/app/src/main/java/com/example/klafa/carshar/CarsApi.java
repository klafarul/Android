package com.example.klafa.carshar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CarsApi {
    @Headers("Content-Type: application/json")
    @POST("/fetch")
    Call<ArrayList<JsonResponse>> getStringScalar(@Body JsonSend body);

}
