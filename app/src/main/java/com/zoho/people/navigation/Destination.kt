package com.zoho.people.navigation

sealed class Destination(val route: String) {
    object Home: Destination("home")
    object Detail: Destination("detail")
}