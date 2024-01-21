package com.lunatictiol.swipeycs21assignment.presentaion.productsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lunatictiol.swipeycs21assignment.presentaion.Searchmenu
import com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.components.ListItem
import com.lunatictiol.swipeycs21assignment.presentaion.common.MyAppBar
import com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.components.addProductBottomSheet.AddProductBottomSheet
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_inversePrimary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_background
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = koinViewModel(),
    navHostController: NavHostController
){
    val sheetState = rememberModalBottomSheetState()
    val isOpened = viewModel.sheetIsOpen
   Scaffold(
       containerColor = md_theme_light_background ,
       topBar = { MyAppBar(navHostController) { viewModel.changeSheetState() } },
       floatingActionButton = {
           Box(modifier = Modifier.background(md_theme_dark_inversePrimary, shape =
               RoundedCornerShape(30.dp)
           ), contentAlignment = Alignment.Center ) {
               IconButton(onClick = {
                   navHostController.navigate(Searchmenu.route)


               }) {
               Icon(Icons.Default.Search, contentDescription = "Search Bar", tint = Color.White )
           }
       }}
   ) { paddingValues->
        val list = viewModel.state.value
         LazyColumn(

                       modifier = Modifier
                           .padding(top = paddingValues.calculateTopPadding())
                           .background(MaterialTheme.colorScheme.background)
                           .fillMaxSize()

                   ) {
                       items(list) { products ->
                           Box(
                               modifier = Modifier
                                   .padding(10.dp)

                           ) {
                               ListItem(productDetails = products)
                           }


                       }

                   }
               }
    if (isOpened.value){
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {viewModel.changeSheetState()}) {
         AddProductBottomSheet(
             context = navHostController.context,
             onClick = { viewModel.changeSheetState() }
         )

    }}



}


