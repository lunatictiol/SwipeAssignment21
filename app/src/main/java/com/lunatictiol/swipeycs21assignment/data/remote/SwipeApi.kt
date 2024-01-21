package com.lunatictiol.swipeycs21assignment.data.remote

import com.lunatictiol.swipeycs21assignment.data.remote.responses.AddProductResponse
import com.lunatictiol.swipeycs21assignment.data.remote.responses.GetProductResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SwipeApi{
    @GET("get")
  suspend fun getProducts():GetProductResponse
    @Multipart
    @POST("add")
    suspend fun postProduct(
        @Part("product_name") productName: String,
        @Part("product_type") productType: String,
        @Part("price") price: String,
        @Part("tax") tax: String,
        @Part ("files[]")files: MultipartBody.Part?
    ): AddProductResponse
}