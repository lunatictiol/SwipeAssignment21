package com.lunatictiol.swipeycs21assignment.presentaion.productsScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunatictiol.swipeycs21assignment.data.remote.responses.ProductDetails
import com.lunatictiol.swipeycs21assignment.repository.SwipeRepository
import com.lunatictiol.swipeycs21assignment.util.Resource
import kotlinx.coroutines.launch

class ProductsViewModel (
    private val repository: SwipeRepository
):ViewModel(){
    private val _state = mutableStateOf<List<ProductDetails>>(listOf())
    val state = _state
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var _sheetIsOpen = mutableStateOf(false)
    val  sheetIsOpen = _sheetIsOpen
    init {
        loadProducts()
    }
    fun changeSheetState(){
        _sheetIsOpen.value  = !(_sheetIsOpen.value)
    }

    private fun loadProducts() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getProducts()
            when(result) {
                is Resource.Success->{
                    _state.value= result.data ?: emptyList()
                    isLoading.value = false
                    Log.e("product Screen", result.message.toString())
                }
                is Resource.Error->{
                    loadError.value = result.message ?: "An unexpected error occured"

                }

            }
        }
    }





}