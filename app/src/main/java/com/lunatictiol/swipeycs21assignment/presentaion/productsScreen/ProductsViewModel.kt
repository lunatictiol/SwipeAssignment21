package com.lunatictiol.swipeycs21assignment.presentaion.productsScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunatictiol.swipeycs21assignment.data.model.responses.ProductDetails
import com.lunatictiol.swipeycs21assignment.repository.SwipeRepository
import com.lunatictiol.swipeycs21assignment.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductsViewModel (
    private val repository: SwipeRepository
):ViewModel(){
    private val _state = MutableLiveData<List<ProductDetails>?>(listOf())
    val state: MutableLiveData<List<ProductDetails>?> get() = _state
    val isLoading = mutableStateOf(true)



    init {
        loadProducts()


    }

    private fun loadProducts() {
        viewModelScope.launch {
            when(val result = repository.getProducts()) {
                is Resource.Success->{
                    _state.value= result.data
                    delay(1000)
                   isLoading.value=false



                }
                is Resource.Error->{
                   isLoading.value=false



                }

            }
        }
    }





}