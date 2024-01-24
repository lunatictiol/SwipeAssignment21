package com.lunatictiol.swipeycs21assignment.presentaion.productsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lunatictiol.swipeycs21assignment.R
import com.lunatictiol.swipeycs21assignment.util.SearchMenu
import com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.components.ListItem
import com.lunatictiol.swipeycs21assignment.presentaion.common.MyAppBar
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_inversePrimary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_background
import org.koin.androidx.compose.koinViewModel

//main screen
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = koinViewModel(),
    navHostController: NavHostController
){


   Scaffold(
       containerColor = md_theme_light_background ,
       topBar = { MyAppBar(navHostController)  },
       floatingActionButton = {
           Box(modifier = Modifier.background(md_theme_dark_inversePrimary, shape =
               RoundedCornerShape(30.dp)
           ), contentAlignment = Alignment.Center ) {
               IconButton(onClick = {
                   navHostController.navigate(SearchMenu.route)


               }) {
               Icon(Icons.Default.Search, contentDescription = "Search Bar", tint = Color.White )
           }
       }}
   ) { paddingValues->
       //local states
        val list by viewModel.state.observeAsState(emptyList())
       val isloading =viewModel.isLoading
       val isRefreshing by viewModel.isRefreshing.collectAsState()
       val pullRefreshState = rememberPullRefreshState(isRefreshing, { viewModel.refresh() })
       //box for main layout
        if (!isloading.value){
        Box(modifier = Modifier.fillMaxSize().pullRefresh(pullRefreshState)){

         LazyColumn(

                       modifier = Modifier
                           .padding(top = paddingValues.calculateTopPadding())
                           .background(MaterialTheme.colorScheme.background)
                           .fillMaxSize()

                   ) {
                       items(list!!) { products ->
                           Box(
                               modifier = Modifier
                                   .padding(10.dp)

                           ) {

                               ListItem(productDetails = products)
                           }


                       }

                   }
            //refresh
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)

                )
        }
               }
       else{
   //loading animation
            Box (modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())  ){
                AnimatedPreloader(modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center),isloading.value)
            }

       }
   }




}
@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier, isLoading:Boolean) {
    //animation logic
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.loading_animation)
    )


    val clipSpecs = LottieClipSpec.Progress(
        min =  0.0f,
        max =  0.9f
    )


    LottieAnimation(
        composition = composition,
          clipSpec = clipSpecs,
        modifier = modifier,
        iterations = if (isLoading)  LottieConstants.IterateForever else 3,
    )
}


