package com.lunatictiol.swipeycs21assignment.util
//routes for jetpack navigation
interface Destinations {
    val route : String
}
object ProductScreen : Destinations {
    override val route: String = "productScreen"

}

object SearchMenu: Destinations {
    override val route: String = "searchScreen"
}
object AddProductScreen: Destinations {
    override val route: String="addProduct"

}

