package com.lunatictiol.swipeycs21assignment.data

import java.io.File

data class AddProductDetails(
    val image: File?,
    val price: Double,
    var product_name: String,
    val product_type: String,
    val tax: Double
)
