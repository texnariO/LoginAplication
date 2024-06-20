package com.example.loginaplication.presentation.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loginaplication.presentation.screens.photoscreen.PhotoScreen
import com.example.loginaplication.presentation.screens.startscreen.StartScreen
import com.example.loginaplication.presentation.screens.successscrenn.SuccessScreen
import com.example.loginaplication.presentation.screens.userscreen.UserScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.StartScreen.route){
        composable(Routes.UserScreen.route,arguments = listOf(
            navArgument(name = "id"){
                type = NavType.IntType
            },
            navArgument(name = "userNameLocal"){
                type = NavType.StringType
            }
        )){
            //TODO Add null safety
            UserScreen(
                idUser = it.arguments!!.getInt("id"),
                userName = it.arguments!!.getString("userNameLocal")!!,
                navController = navController,
            )
        }
        composable(Routes.StartScreen.route){
            StartScreen(navController = navController)
        }
        composable(Routes.PhotoScreen.route, arguments = listOf(
            navArgument(name="id"){
                type = NavType.IntType
            },
            navArgument(name = "request"){
                type = NavType.BoolType
            }
        )){
            PhotoScreen(
                idUser = it.arguments!!.getInt("id"),
                request = it.arguments!!.getBoolean("request"),
                navController = navController
            )
        }
        composable(Routes.SuccesScreen.route){
            SuccessScreen(navController = navController)
        }
    }

}