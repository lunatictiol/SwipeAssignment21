package com.lunatictiol.swipeycs21assignment.data.remote.responses

data class AddProductResponse(
    val message: String,
    val product_details: ProductDetails,
    val product_id: Int,
    val success: Boolean
)