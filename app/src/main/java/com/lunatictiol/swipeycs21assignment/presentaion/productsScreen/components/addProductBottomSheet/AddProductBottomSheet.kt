package com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.components.addProductBottomSheet

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_inversePrimary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_primaryContainer
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_primary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_primaryContainer
import org.koin.androidx.compose.koinViewModel


@Composable
fun AddProductBottomSheet(
    viewModel: AddProductViewModel = koinViewModel(),
    context: Context,
    onClick:()->Unit

    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        )

        {
            Text(
                text = "Add Product",
                fontSize = 48.sp,
                color = md_theme_light_primary
            )
            Card(
                modifier = Modifier.background(
                    color = md_theme_light_primaryContainer,
                    shape = RoundedCornerShape(40.dp)
                ),
                elevation = CardDefaults.cardElevation(20.dp)
            ) {
                CardContent(
                    modifier = Modifier
                        .background(color = md_theme_dark_primaryContainer)
                        .fillMaxWidth(),
                    viewModel = viewModel,
                    context=context,
                    onClick = onClick

                )
            }
        }

}

@Composable
fun CardContent(
    modifier: Modifier,
    viewModel: AddProductViewModel,
    context: Context,
    onClick: () -> Unit

    ) {

    // Local state for text fields
    var price by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var productImage by remember { mutableStateOf("") }
    var productType by remember { mutableStateOf("") }
    var tax by remember { mutableStateOf("") }
    val productDetails =viewModel.productDetailsState.value
    fun setImage(image:String=""){
        productImage = image
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {    // Text fields for entering product details
        PhotoPicker(
            viewModel=viewModel
        )
        TextField(
            value = productName,
            onValueChange = {
                productName = it
                viewModel.nameIsValid.value = it.isNotEmpty()
            },
            shape = RoundedCornerShape(5.dp),
            label = { Text("Product Name") },
            modifier = modifier.padding(10.dp),

            )
        TextField(
            value = productType,
            onValueChange = {
                productType = it
                viewModel.typeIsValid.value = it.isNotEmpty()
            },
            shape = RoundedCornerShape(5.dp),
            label = { Text("Product Type") },
            modifier = modifier.padding(10.dp),

            )
        Row(modifier.padding(bottom = 5.dp)) {
            TextField(

                value = tax,
                onValueChange = {
                    tax = it
                    viewModel.taxIsValid.value = it.isNotEmpty()
                },
                label = { Text("Tax") },
                shape = RoundedCornerShape(5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = modifier
                    .padding(10.dp)
                    .weight(1f),

                )
            TextField(
                value = price,
                onValueChange = {
                    price = it
                    viewModel.priceIsValid.value = true
                },
                shape = RoundedCornerShape(5.dp),
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = modifier
                    .padding(10.dp)
                    .weight(1f),

                )
        }
        // Button to submit the product details
            IconButton(
                modifier = Modifier
                    .background(
                        color=md_theme_light_primaryContainer,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .fillMaxWidth(),

                onClick = {
                    // Create ProductDetails object from entered data
                    productImage = viewModel.imageUri.value
                    val productDetails = productDetails.copy(
                        price = price.toDoubleOrNull() ?: 0.0,
                        product_name = productName,
                        product_type = productType,
                        tax = tax.toDoubleOrNull() ?: 0.0
                    )

                    //Call ViewModel function to handle the submission
                    viewModel.setProductDetails(productDetails)

                    if (viewModel.validate() && viewModel.success.value ){
                        val successToast = Toast.makeText(context,"Product added successfully <3",Toast.LENGTH_LONG)
                        successToast.show()
                        onClick()
                    }
                    else{
                        val toast = Toast.makeText(context,viewModel.toast.value,Toast.LENGTH_LONG)
                        toast.show()
                    }
                    // Clear the text fields
                    price = ""
                    productName = ""
                    productType = ""
                    tax = ""



                }
            ) {
                Text(text = "Submit" , color = md_theme_dark_inversePrimary
                    , textAlign = TextAlign.Center )
            }


    }
}

