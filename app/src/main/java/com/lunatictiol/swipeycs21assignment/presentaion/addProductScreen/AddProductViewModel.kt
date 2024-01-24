package com.lunatictiol.swipeycs21assignment.presentaion.addProductScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunatictiol.swipeycs21assignment.data.model.AddProductDetails
import com.lunatictiol.swipeycs21assignment.repository.SwipeRepository
import com.lunatictiol.swipeycs21assignment.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AddProductViewModel(
    private val repository: SwipeRepository

) : ViewModel() {
    //states
    private var _toastMessage = mutableStateOf("")
    var toast = _toastMessage
    var success = mutableStateOf(false)
    var isLoading =mutableStateOf(false)
    var failed = mutableStateOf(false)
    var showAddAnotherButton = mutableStateOf(false)
    private var imagePart: MultipartBody.Part? = null
    private var _productDetailsState = mutableStateOf(
        AddProductDetails(
            product_name = "",
            price = "",
            product_type = "",
            image = null,
            tax = ""
        )
    )
//function to save image state
    fun saveImage(part: MultipartBody.Part) {
        imagePart = part
    }
//function reset image state
    fun removeImage() {
        imagePart = null
    }
// add data state
    fun addData(
        name: String,
        type: String,
        price: String,
        tax: String
    ) {
         if (validate(name, type, price, tax)) {
             _productDetailsState.value = AddProductDetails(
                 product_name = name,
                 product_type = type,
                 price = price,
                 tax = tax,
                 image = imagePart
             )
             uploadData()
         }



    }
//upload the data to the server

    private fun uploadData() {
       isLoading.value = true

        viewModelScope.launch {

            val addProductDetails = _productDetailsState.value
            val result = repository.addProduct(addProductDetails)
            if (result is Resource.Success){
                success.value = true
                _toastMessage.value= result.data.message
                delay(4700)
                showAddAnotherButton.value =true

            }
            else if(result is Resource.Error ){

                failed.value = true
                _toastMessage.value = result.exception.message.toString()
                delay(4700)
                showAddAnotherButton.value =true

            }

        }

    }
//validate if data is empty
    fun validate(
        name: String,
        type: String,
        price: String,
        tax: String
    )

            : Boolean {
        return if (name.isNotEmpty() && type.isNotEmpty() && price.isNotEmpty() && tax.isNotEmpty()) {
            true
        } else {
            _toastMessage.value = "Fields can't be empty"
            false
        }

    }
}
