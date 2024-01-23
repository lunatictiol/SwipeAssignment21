package com.lunatictiol.swipeycs21assignment.data.remote

import com.lunatictiol.swipeycs21assignment.data.model.responses.AddProductResponse
import com.lunatictiol.swipeycs21assignment.data.model.responses.GetProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SwipeApi{
    @GET("get")
  suspend fun getProducts(): GetProductResponse
    @Multipart
    @POST("add")
    suspend fun postProduct(
        @Part("product_name") productName: RequestBody,
        @Part("product_type") productType: RequestBody,
        @Part("price") price: RequestBody,
        @Part("tax") tax: RequestBody,
        @Part files: MultipartBody.Part?
    ): AddProductResponse


}
