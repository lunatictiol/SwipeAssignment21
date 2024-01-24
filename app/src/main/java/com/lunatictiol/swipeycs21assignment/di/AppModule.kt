package com.lunatictiol.swipeycs21assignment.di

import com.lunatictiol.swipeycs21assignment.data.remote.SwipeApi
import com.lunatictiol.swipeycs21assignment.presentaion.searchScreen.SearchScreenViewModel
import com.lunatictiol.swipeycs21assignment.presentaion.addProductScreen.AddProductViewModel
import com.lunatictiol.swipeycs21assignment.presentaion.productsScreen.ProductsViewModel
import com.lunatictiol.swipeycs21assignment.repository.SwipeRepository
import com.lunatictiol.swipeycs21assignment.util.Constant
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//dependency injection using Koin
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

        AddProductViewModel(get())
    }
    viewModel<ProductsViewModel>{
      ProductsViewModel(get())
    }

    viewModel<SearchScreenViewModel>{
        SearchScreenViewModel(get())
    }


}