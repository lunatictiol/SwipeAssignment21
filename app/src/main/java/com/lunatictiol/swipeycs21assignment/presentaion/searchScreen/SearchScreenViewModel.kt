package com.lunatictiol.swipeycs21assignment.presentaion.searchScreen



import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunatictiol.swipeycs21assignment.data.model.responses.ProductDetails
import com.lunatictiol.swipeycs21assignment.repository.SwipeRepository
import com.lunatictiol.swipeycs21assignment.util.Resource
import kotlinx.coroutines.launch

class SearchScreenViewModel( private val repository: SwipeRepository):ViewModel() {
    private val _list = mutableStateOf<List<ProductDetails>>(listOf())
    val result = mutableStateOf<List<ProductDetails>>(listOf())
    var searchQuery by mutableStateOf("")

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
            when(val result = repository.getProducts()) {
                is Resource.Success->{
                    _list.value= result.data

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





