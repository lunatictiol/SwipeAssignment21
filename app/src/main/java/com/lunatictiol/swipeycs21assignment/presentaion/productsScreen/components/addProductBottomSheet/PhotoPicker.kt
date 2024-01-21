package com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.components.addProductBottomSheet

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import coil.compose.AsyncImage
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_inversePrimary
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_dark_onSurface
import com.lunatictiol.swipeycs21assignment.presentaion.ui.theme.md_theme_light_primaryContainer
import java.io.File

@Composable
fun PhotoPicker(viewModel: AddProductViewModel) {
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImage = uri
            val inputStream = context.contentResolver.openInputStream(selectedImage!!)

            val file = File(context.cacheDir, "mage.png")
            file.createNewFile()
            file.outputStream().use {
                inputStream!!.copyTo(it)
            }

            viewModel.setImage(file)
        }
    )

    fun launchPhotoPicker() {

            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )


    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

             ImageLayoutView(selectedImage = selectedImage)

        if (selectedImage==null){
        IconButton(
            modifier = Modifier.fillMaxWidth(.50f),
            onClick = {
            launchPhotoPicker()

        }) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = md_theme_light_primaryContainer,
                        shape = RoundedCornerShape(10.dp)
                    ) ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "",
                        tint = md_theme_dark_inversePrimary
                    )
                    Text(text = "Add Image", color = md_theme_dark_inversePrimary)
                }
                }
        }
        }
        else{
            IconButton(
                modifier = Modifier.fillMaxWidth(.50f),
                onClick = {

                selectedImage = null

            }) {
                Box( Modifier.background(color = md_theme_light_primaryContainer, shape = RoundedCornerShape(10.dp) ) ){
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically){


                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "",
                            tint = md_theme_dark_inversePrimary
                        )
                        Text(text = "Remove Image", color = md_theme_dark_inversePrimary)
                    }
                }
            }
        }
        }



}


@Composable
fun ImageLayoutView(selectedImage: Uri?) {

            Box(modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .padding(2.dp)
                .border(5.dp, md_theme_dark_inversePrimary)
                .background(md_theme_dark_onSurface),


            ) {


            AsyncImage(
                model = selectedImage,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
                   ,
                contentScale = ContentScale.FillWidth
            )

            }
        }




