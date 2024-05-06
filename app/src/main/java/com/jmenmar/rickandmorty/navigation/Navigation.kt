package com.jmenmar.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jmenmar.rickandmorty.core.Constants.CHAR_KEY
import com.jmenmar.rickandmorty.ui.screens.character.CharacterScreen
import com.jmenmar.rickandmorty.ui.screens.characters.CharactersScreen
import com.jmenmar.rickandmorty.ui.screens.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = "ROOT",
        startDestination = MainScreen.Splash.route
    ) {
        composable(MainScreen.Splash.route) {
            SplashScreen(
                navigateToCharacters = {
                    navController.popBackStack()
                    navController.navigate(MainScreen.Characters.route)
                }
            )
        }

        composable(route = MainScreen.Characters.route) {
            CharactersScreen(
                navigateToDetail = { id ->
                    navController.navigate(MainScreen.CharacterDetail.passCharacter(id))
                }
            )
        }

        composable(
            route = MainScreen.CharacterDetail.route,
            arguments = MainScreen.CharacterDetail.arguments
        ) {
            CharacterScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

sealed class MainScreen(
    val route: String,
    val arguments: List<NamedNavArgument> = listOf()
) {
    data object Splash: MainScreen(route = "SPLASH")

    data object Characters: MainScreen(route = "CHARACTERS")

    data object CharacterDetail : MainScreen(
        route = "CHARACTERDETAIL?$CHAR_KEY={$CHAR_KEY}",
        arguments = listOf(
            navArgument(CHAR_KEY) {
                type = NavType.IntType
            }
        )
    ) {
        fun passCharacter(id: Int) = "CHARACTERDETAIL?$CHAR_KEY=$id"
    }
}