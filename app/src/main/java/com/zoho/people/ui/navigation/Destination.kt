package com.zoho.people.ui.navigation

import androidx.navigation.NavController

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object Detail : Destination("detail/{id}") {
        const val ArgId = "id"
    }
}

fun NavController.navigateToDetail(id: String) {
    navigate("detail/$id")
}