package com.lunatictiol.swipeycs21assignment.di

import android.util.Log
import com.lunatictiol.swipeycs21assignment.data.remote.SwipeApi
import com.lunatictiol.swipeycs21assignment.presentaion.SearchScreen.SearchScreenViewModel
import com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.components.addProductBottomSheet.AddProductViewModel
import com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.ProductsViewModel
import com.lunatictiol.swipeycs21assignment.repository.SwipeRepository
import com.lunatictiol.swipeycs21assignment.util.Constant
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.logger.KOIN_TAG
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SwipeApi::class.java)
    }
    factory<SwipeRepository> {
        SwipeRepository(get())
    }
    viewModel <AddProductViewModel>{
        Log.e("AddPRODUCT", KOIN_TAG)
        AddProductViewModel(get())
    }
    viewModel<ProductsViewModel>{
      ProductsViewModel(get())
    }

    viewModel<SearchScreenViewModel>{
        SearchScreenViewModel(get())
    }


}