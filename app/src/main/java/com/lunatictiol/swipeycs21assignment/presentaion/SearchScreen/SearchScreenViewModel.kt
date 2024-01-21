package com.lunatictiol.swipeycs21assignment.presentaion.SearchScreen


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunatictiol.swipeycs21assignment.data.remote.responses.ProductDetails
import com.lunatictiol.swipeycs21assignment.repository.SwipeRepository
import com.lunatictiol.swipeycs21assignment.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel( private val repository: SwipeRepository):ViewModel() {
    private val _list = mutableStateOf<List<ProductDetails>>(listOf())
    val result = mutableStateOf<List<ProductDetails>>(listOf())
    var searchQuery by mutableStateOf("")
    val list = MutableStateFlow(_list.value)

    init {
        loadProducts()
    }




    fun search(){
        rest()
       _list.value.forEach{

            if (it.product_name.contains(searchQuery,ignoreCase = true)){
               result.value = result.value + it
                Log.e("searchSS",result.value.toString())
          }
          }
        Log.e("searchSS",result.value.toString())

    }




    private fun loadProducts() {
        viewModelScope.launch {
            val result = repository.getProducts()
            when(result) {
                is Resource.Success->{
                    _list.value= result.data ?: emptyList()

                    Log.e("search message", searchQuery)
                }
                is Resource.Error->{
                    Log.e("search Error", searchQuery )

                }

            }
        }
    }
    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }
    fun rest(){
        result.value = emptyList()
    }
    }




