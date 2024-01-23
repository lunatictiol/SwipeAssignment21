package com.lunatictiol.swipeycs21assignment.data.model.responses



data class ProductDetails(
    val image: String ,
    val price: Double,
    var product_name: String,
    val product_type: String,
    val tax: Double
)