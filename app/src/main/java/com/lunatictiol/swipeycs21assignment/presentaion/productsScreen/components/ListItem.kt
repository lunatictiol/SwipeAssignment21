package com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lunatictiol.swipeycs21assignment.R
import com.lunatictiol.swipeycs21assignment.data.remote.responses.ProductDetails
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_onPrimary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_primary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_secondaryContainer

@Composable
fun ListItem(productDetails:ProductDetails){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = md_theme_light_secondaryContainer)
        .padding(10.dp),



    ) {
        Column{
        AsyncImage(
            model = productDetails.image  ,
            placeholder = painterResource(id =R.drawable.placeholder),
            contentDescription = "productimage",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth,
            error = painterResource(id =R.drawable.placeholder)
        )
         Spacer(modifier = Modifier.fillMaxWidth().height(6.dp))
         TextItem(type = "Name", value = productDetails.product_name.removeSurrounding("\'\'","\'\'"))
            TextItem(type = "Price", value = productDetails.price.toString())
            TextItem(type = "Type", value = productDetails.product_type.removeSurrounding("\'\'","\'\'"))

         TextItem(type = "Tax", value = productDetails.tax.toString())
        }

    }
}


@Composable
fun TextItem(type:String,value:String){
    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
        Text(text = type , fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif,  color = md_theme_light_primary )
        Text(text = value, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace , color = md_theme_dark_onPrimary)

    }

}