package com.example.loginaplication.presentation.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginaplication.presentation.screens.startscreen.StartScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.StartScreen.route){
        composable(Routes.UserScreen.route){
            //UserScreen(navControlleer = navController)
        }
        composable(Routes.StartScreen.route){
            StartScreen(navController = navController)
        }
        composable(Routes.PhotoScreen.route){
            //PhotoScreen(navController = navController)
        }
        composable(Routes.SuccesScreen.route){
            //SuccesScreen(navConteroller = navController)
        }
    }

}