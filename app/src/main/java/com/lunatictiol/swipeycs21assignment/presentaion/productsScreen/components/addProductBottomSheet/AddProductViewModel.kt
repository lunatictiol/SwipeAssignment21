package com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.components.addProductBottomSheet

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunatictiol.swipeycs21assignment.data.AddProductDetails
import com.lunatictiol.swipeycs21assignment.data.remote.responses.ProductDetails
import com.lunatictiol.swipeycs21assignment.repository.SwipeRepository
import com.lunatictiol.swipeycs21assignment.util.Resource
import kotlinx.coroutines.launch
import java.io.File

class AddProductViewModel(
private val repository: SwipeRepository

):ViewModel() {
    private var _toastMessage = mutableStateOf("fields can't be empty")
    var toast  = _toastMessage
    var imageUri = mutableStateOf("")
    private var _success = mutableStateOf(false)
    var success = _success
    var nameIsValid =mutableStateOf(false)
    var typeIsValid  =mutableStateOf(false)
    var priceIsValid =mutableStateOf(false)
    var taxIsValid = mutableStateOf(false)



    private  var  _productDetailsState  = mutableStateOf<AddProductDetails>(
        AddProductDetails(
            product_name = "",
            price = 0.0,
            product_type = "",
            image = null,
            tax = 0.0
        )
    )



    // Expose the productDetailsState as a read-only state
    val productDetailsState: MutableState<AddProductDetails> = _productDetailsState

    // Function to update the state with new data
    fun setProductDetails(addProductDetails: AddProductDetails) {
        productDetailsState.value.copy(
            product_name = addProductDetails.product_name,
            product_type = addProductDetails.product_type,
            price = addProductDetails.price,
            tax = addProductDetails.tax
        )
        _productDetailsState.value = productDetailsState.value
        if(validate()){
        viewModelScope.launch {
            uploadData()

        }
        }



    }
    fun setImage(imageFile: File){

    productDetailsState.value.copy(image = imageFile)

    }

    private  fun uploadData(){
        viewModelScope.launch {
_productDetailsState.value=productDetailsState.value
        val result= repository.addProduct(_productDetailsState.value)
         when(result){
             is Resource.Success->{

                 _toastMessage.value = "Product Added successfully"
                 _success.value = true
                 // Reset states after a successful upload
                 nameIsValid.value = false
                 typeIsValid.value = false
                 priceIsValid.value = false
                 taxIsValid.value = false

                 // Reset the product details state to a new empty state
                 _productDetailsState.value = AddProductDetails(
                     product_name = "",
                     price = 0.0,
                     product_type = "",
                     image = null,
                     tax = 0.0
                 )


             }
             is Resource.Error->{
                 _toastMessage.value = result.data?.message ?: "error"
                 Log.e("ADD",_productDetailsState.value.toString())
             }
         }


        }

    }
    fun validate():Boolean{

        if (!(priceIsValid.value && nameIsValid.value && typeIsValid.value && taxIsValid.value)){

            return false
        }
        return true
    }

}