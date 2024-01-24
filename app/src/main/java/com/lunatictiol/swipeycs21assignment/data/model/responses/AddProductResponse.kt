package com.lunatictiol.swipeycs21assignment.data.model.responses
//response from api when product is added
data class AddProductResponse(
    val message: String,
    val product_details: ProductDetails,
    val product_id: Int,
    val success: Boolean
)