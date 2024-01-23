package com.lunatictiol.swipeycs21assignment.presentaion.addProductScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lunatictiol.swipeycs21assignment.R
import com.lunatictiol.swipeycs21assignment.presentaion.addProductScreen.components.PhotoPicker
import com.lunatictiol.swipeycs21assignment.presentaion.common.MyAppBar
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_inversePrimary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_primaryContainer
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_background
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_primary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_primaryContainer
import org.koin.androidx.compose.koinViewModel


@Composable
fun AddProductScreen(
    viewModel: AddProductViewModel = koinViewModel(),
    context: Context,
    navHostController:NavHostController


) {
   Scaffold(
       containerColor = md_theme_light_background ,
       topBar = { MyAppBar(navHostController)  }

   ) {paddingValues->

       val isLoading =viewModel.isLoading

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                start = 10.dp,
                end = 10.dp,
                bottom = 100.dp

            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally

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
        ) { if (isLoading.value){
            AnimationContent(viewModel,context)
        }
        else{
            CardContent(
                modifier = Modifier
                    .background(color = md_theme_dark_primaryContainer)
                    .fillMaxWidth(),
                viewModel = viewModel,
                context = context,
                navHostController=navHostController


            )}
        }
    }
   }

}

@Composable
fun CardContent(
    modifier: Modifier,
    viewModel: AddProductViewModel,
    context: Context,
    navHostController:NavHostController


) {

    // Local state for text fields
    var price by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var productType by remember { mutableStateOf("") }
    var pTax by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {    // Text fields for entering product details

        TextField(
            value = productName,
            onValueChange = {
                productName = it


            },
            shape = RoundedCornerShape(5.dp),
            label = { Text("Product Name") },
            modifier = modifier.padding(10.dp),

            )
        TextField(
            value = productType,
            onValueChange = {
                productType = it
            },
            shape = RoundedCornerShape(5.dp),
            label = { Text("Product Type") },
            modifier = modifier.padding(10.dp),

            )
        Row(modifier.padding(bottom = 5.dp)) {

            TextField(
                value = price,
                onValueChange = {
                    price = it
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
            TextField(
                value = pTax,
                onValueChange = {
                    pTax = it
                },
                shape = RoundedCornerShape(5.dp),
                label = { Text("Tax") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = modifier
                    .padding(10.dp)
                    .weight(1f),

                )
        }
        PhotoPicker(
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(5.dp))
        // Button to submit the product details
        IconButton(
            modifier = Modifier
                .background(
                    color = md_theme_light_primaryContainer,
                    shape = RoundedCornerShape(25.dp)
                )
                .fillMaxSize(.50f)
                .height(40.dp)
                ,

            onClick = {
                //Call ViewModel function to handle the submission

               if (viewModel.validate(name = productName, type = productType,price=price, tax = pTax)) {
                   viewModel.addData(
                       name = productName,
                       type = productType,
                       price = price,
                       tax = pTax
                   )
               }else{
                   Toast.makeText(context,viewModel.toast.value,Toast.LENGTH_LONG).show()
               }



               // Clear the text fields
               productName=""
               productType =""
               price=""
               pTax=""







            }
        ) {
            Text(
                text = "Submit", color = md_theme_dark_inversePrimary, textAlign = TextAlign.Center
            )
        }



    }


}

@Composable
fun AnimationContent(viewModel:AddProductViewModel, context: Context){
    val isFailed = viewModel.failed
    val isSuccess = viewModel.success
    val showAddButton = viewModel.showAddAnotherButton

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )   {
        val clipSpecs = LottieClipSpec.Progress(
            min = if (isFailed.value) 0.499f else 0.0f,
            max = if (isSuccess.value) 0.44f else if (isFailed.value) 0.95f else 0.282f
        )

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_loading_success_failed))

        LottieAnimation(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            composition = composition,
            iterations = if (isSuccess.value || isFailed.value) 1 else LottieConstants.IterateForever,
            clipSpec = clipSpecs,
        )
        if (isFailed.value){
            Toast.makeText(context,viewModel.toast.value,Toast.LENGTH_LONG).show()
        }
        if(showAddButton.value){
            IconButton(
                modifier = Modifier
                    .background(
                        color = md_theme_light_primaryContainer,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .fillMaxSize(.50f)
                    .height(40.dp)
                , onClick = {
                    viewModel.isLoading.value=false

                }){
                Text(
                    text = "Add again", color = md_theme_dark_inversePrimary, textAlign = TextAlign.Center
                )
            }
        }
    }
    }


