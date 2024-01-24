package com.lunatictiol.swipeycs21assignment.presentaion.common

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.lunatictiol.swipeycs21assignment.util.AddProductScreen
import com.lunatictiol.swipeycs21assignment.util.ProductScreen
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_inversePrimary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_onPrimaryContainer
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    navHostController: NavHostController,

){  // App bar for all the screens
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = md_theme_dark_onPrimaryContainer,
            titleContentColor = md_theme_dark_inversePrimary,
            actionIconContentColor = md_theme_dark_inversePrimary

        ),
        modifier = Modifier.background(Color.Cyan),
        //home icon
        navigationIcon = {
            if (navHostController.currentDestination?.route== ProductScreen.route){
                Icon(Icons.Default.Home, contentDescription = "home", tint =md_theme_dark_inversePrimary )
            }
            else{
                //back  button
                  IconButton(onClick = { navHostController.popBackStack() }) {
                      Icon(Icons.Default.ArrowBack, contentDescription ="back" , tint = md_theme_dark_inversePrimary)
                  }}
        },
        title = {
            Text(
                text = "SWIPE",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                )
                },
        actions = {
            if (navHostController.currentDestination?.route== ProductScreen.route){
            IconButton(onClick ={
                navHostController.navigate(AddProductScreen.route)
            }) {
                Icon(Icons.Default.AddCircle, contentDescription = "Search")
            }}
        }

    )
}