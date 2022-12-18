package com.example.magis.api;

import com.example.magis.model.BarangResponse;
import com.example.magis.model.InsertProductResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("get-products")
    Call<BarangResponse> getProducts();

    @POST("insert-product")
    @FormUrlEncoded
    Call<InsertProductResponse> insertProduct(
            @Field("kode") int kode,
            @Field("nama") String nama,
            @Field("berat") int berat,
            @Field("qty") int qty
    );

    @POST("update-product/{id}")
    @FormUrlEncoded
    Call<InsertProductResponse> updateProduct(
            @Path(value = "id", encoded = true) int id,
            @Field("kode") int kode,
            @Field("nama") String nama,
            @Field("berat") int berat,
            @Field("qty") int qty
    );

    @GET("delete-product/{id}")
    Call<InsertProductResponse> deleteProduct(
            @Path(value = "id", encoded = true) int id
    );
}
