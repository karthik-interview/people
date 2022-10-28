package com.zoho.people.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zoho.people.ui.detail.DetailRoute
import com.zoho.people.ui.home.HomeRoute

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: Destination = Destination.Home,
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination.route,
        modifier = modifier,
    ) {
        composable(Destination.Home.route) {
            HomeRoute(
                navController = navHostController
            )
        }

        composable(
            Destination.Detail.route,
            arguments = listOf(navArgument(Destination.Detail.ArgId) { type = NavType.StringType })
        ) {
            DetailRoute(
                navController = navHostController

            )
        }
    }
}