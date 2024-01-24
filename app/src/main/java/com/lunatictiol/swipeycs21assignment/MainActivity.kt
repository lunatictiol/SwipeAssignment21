package com.lunatictiol.swipeycs21assignment


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lunatictiol.swipeycs21assignment.util.AddProductScreen
import com.lunatictiol.swipeycs21assignment.util.ProductScreen
import com.lunatictiol.swipeycs21assignment.util.SearchMenu
import com.lunatictiol.swipeycs21assignment.presentaion.searchScreen.SearchScreen
import com.lunatictiol.swipeycs21assignment.presentaion.addProductScreen.AddProductScreen

import com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.ProductsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initial splash screen
        installSplashScreen()

        setContent {
            //navigation controller
            val navController= rememberNavController()
            NavHost(navController=navController,startDestination = ProductScreen.route) {

                composable(ProductScreen.route) {
                    ProductsScreen(navHostController = navController)
                }
                composable(SearchMenu.route){
                    SearchScreen(navHostController = navController)
                }
                composable(AddProductScreen.route){
                   AddProductScreen(context = navController.context, navHostController = navController )
                }

            }

        }
    }
}

