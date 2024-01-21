package com.lunatictiol.swipeycs21assignment.repository

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lunatictiol.swipeycs21assignment.data.AddProductDetails
import com.lunatictiol.swipeycs21assignment.data.remote.SwipeApi
import com.lunatictiol.swipeycs21assignment.data.remote.responses.AddProductResponse
import com.lunatictiol.swipeycs21assignment.data.remote.responses.GetProductResponse
import com.lunatictiol.swipeycs21assignment.data.remote.responses.ProductDetails
import com.lunatictiol.swipeycs21assignment.util.Resource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class  SwipeRepository (
    private val api: SwipeApi
){
    suspend fun getProducts(): Resource<GetProductResponse> {
        val response = try {
            api.getProducts()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }



     suspend fun addProduct(addProductDetails: AddProductDetails) : Resource<AddProductResponse> {

         val imageFile = addProductDetails.image
         val imagePart = imageFile?.asRequestBody()
             ?.let { MultipartBody.Part.createFormData("image", imageFile.name, it) }
         val response = try {


             api.postProduct(
                 productName = addProductDetails.product_name,
                 price = addProductDetails.price.toString(),
                 productType = addProductDetails.product_type,
                 tax = addProductDetails.tax.toString(),
                 files = imagePart
             )
         } catch (e:Exception){
             return Resource.Error(message = "couldn't upload")
         }
         Log.e("Repo log", addProductDetails.toString())
         return Resource.Success(response)
     }


}
