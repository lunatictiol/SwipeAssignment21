package com.lunatictiol.swipeycs21assignment.presentaion.productsScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunatictiol.swipeycs21assignment.data.model.responses.ProductDetails
import com.lunatictiol.swipeycs21assignment.repository.SwipeRepository
import com.lunatictiol.swipeycs21assignment.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel (
    private val repository: SwipeRepository
):ViewModel(){
    //refresh
    private val _state = MutableLiveData<List<ProductDetails>?>(listOf())
    val state: MutableLiveData<List<ProductDetails>?> get() = _state
    val isLoading = mutableStateOf(true)

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing



    init {
        loadProducts()


    }
//load products
    private fun loadProducts() {
        viewModelScope.launch {
            when(val result = repository.getProducts()) {
                is Resource.Success->{
                    _state.value= result.data
                   //delay for the animation to play

                    delay(1000)
                   isLoading.value=false



                }
                is Resource.Error->{
                   isLoading.value=false



                }

            }
        }
    }
    //refresh handler
    fun refresh() {

        viewModelScope.launch {
           _isRefreshing.value = true
            // Doing the data refresh here
            async(Dispatchers.IO) {
                // Emitting the fetched data to the list
                loadProducts()
            }
            // Set _isRefreshing to false to indicate the refresh is complete
            _isRefreshing.value =false
        }
    }





}