package com.julhdev.retrofitgames.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.julhdev.retrofitgames.views.DetailsView
import com.julhdev.retrofitgames.views.HomeView

/**
 * Administra la navegación entre las diferentes vistas de la aplicación utilizando NavController y NavHost.
 * Define las rutas de navegación y los parámetros necesarios para cada vista.
 * @usage Incluir este Composable en el punto de entrada de la aplicación para habilitar la navegación.
 */
@Composable
fun NavController(){
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = Routes.HOME
  ) {
    composable(Routes.HOME) {
      HomeView()
    }
    composable(
      route = "${Routes.DETAILS}/{gameId}",
      arguments = listOf(navArgument("gameId") { type = NavType.IntType })
    ) {
      val id = it.arguments?.getInt("gameId") ?: -1
      DetailsView()
    }
  }
}