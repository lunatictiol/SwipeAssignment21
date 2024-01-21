package com.lunatictiol.swipeycs21assignment


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lunatictiol.swipeycs21assignment.presentaion.ProductScreen
import com.lunatictiol.swipeycs21assignment.presentaion.SearchScreen.SearchScreen
import com.lunatictiol.swipeycs21assignment.presentaion.Searchmenu

import com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.ProductsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController= rememberNavController()
            NavHost(navController=navController,startDestination = ProductScreen.route) {

                composable(ProductScreen.route) {
                    ProductsScreen(navHostController = navController)
                }
                composable(Searchmenu.route){
                    SearchScreen(navHostController = navController)
                }

            }

        }
    }
}

