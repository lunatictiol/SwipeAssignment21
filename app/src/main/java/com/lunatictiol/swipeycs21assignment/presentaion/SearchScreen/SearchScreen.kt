package com.lunatictiol.swipeycs21assignment.presentaion.SearchScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.components.ListItem
import com.lunatictiol.swipeycs21assignment.presentaion.common.MyAppBar
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_inversePrimary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_onPrimary
import org.koin.androidx.compose.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(viewModel: SearchScreenViewModel = koinViewModel(),navHostController:NavHostController){
   val searchResults by viewModel.result
    // Local keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current
    Log.e("search",searchResults.toString())
    Scaffold(topBar = { MyAppBar(navHostController =navHostController,{} )  }) { paddingValues ->
        SearchBar(
            colors = SearchBarDefaults.colors(md_theme_light_onPrimary),
            modifier = Modifier.padding(top=paddingValues.calculateTopPadding()) ,
            query = viewModel.searchQuery,
            onQueryChange = { viewModel.onSearchQueryChange(it)
                            if (it.isEmpty()){viewModel.rest()}
                            },
            onSearch = { viewModel.search()
                // Hide the keyboard
                keyboardController?.hide()},
            placeholder = {

            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    tint = md_theme_dark_inversePrimary,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (viewModel.searchQuery.isNotEmpty()) {
                    IconButton(onClick = {
                        viewModel.search()
                        // Hide the keyboard
                        keyboardController?.hide()

                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = md_theme_dark_inversePrimary,
                            contentDescription = "search"
                        )
                    }
                }
            },
            content = {
                if(searchResults.isNotEmpty()){
                LazyColumn(
                    modifier = Modifier
                        .padding(top = paddingValues.calculateTopPadding())
                        .background(md_theme_light_onPrimary)
                        .fillMaxSize()

                ) {
                    items(searchResults) { products ->
                        Box(
                            modifier = Modifier
                                .padding(10.dp)

                        ) {
                            ListItem(productDetails = products)
                        }


                    }

                }

                }
                else{
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text("No Products found")
                    } 
                }
            },
                active = true,
                onActiveChange = {},
                tonalElevation = 10.dp,

                )
    }

}