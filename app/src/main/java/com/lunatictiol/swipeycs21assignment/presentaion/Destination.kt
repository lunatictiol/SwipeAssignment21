package com.lunatictiol.swipeycs21assignment.presentaion

interface Destinations {
    val route : String
}
object ProductScreen : Destinations{
    override val route: String = "productScreen"

}

object Searchmenu:Destinations{
    override val route: String = "searchScreen"
}
