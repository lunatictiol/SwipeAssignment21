package com.lunatictiol.swipeycs21assignment.repository
import android.util.Log
import com.lunatictiol.swipeycs21assignment.data.model.AddProductDetails
import com.lunatictiol.swipeycs21assignment.data.remote.SwipeApi
import com.lunatictiol.swipeycs21assignment.data.model.responses.AddProductResponse
import com.lunatictiol.swipeycs21assignment.data.model.responses.GetProductResponse
import com.lunatictiol.swipeycs21assignment.util.Resource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class  SwipeRepository (
    private val api: SwipeApi
){
    suspend fun getProducts(): Resource<GetProductResponse> {
        val response = try {
            api.getProducts()
        } catch(e: Exception) {
            return Resource.Error(e)
        }
        return Resource.Success(response)
    }



     suspend fun addProduct(addProductDetails: AddProductDetails):Resource<AddProductResponse> {
        return try {
            val name = addProductDetails.product_name.toRequestBody("text/plain".toMediaTypeOrNull())
            val type=addProductDetails.product_type.toRequestBody("text/plain".toMediaTypeOrNull())
            val price =addProductDetails.price.toRequestBody("text/plain".toMediaTypeOrNull())
            val tax= addProductDetails.tax.toRequestBody("text/plain".toMediaTypeOrNull())

             val response = api.postProduct(
                 productName =name ,
                 price = type,
                 productType = price,
                 tax = tax,
                 files = addProductDetails.image
             )
              Log.v("ADD DETAILS",response.product_details.toString() )
              Resource.Success(data = response)
         }
        catch (e:Exception){
             Resource.Error(e)
         }



     }

}
