package com.example.loginaplication.presentation.util.navigation

sealed class Routes(val route: String){
    object StartScreen: Routes(route = "start_screen")
    object UserScreen: Routes(route = "user_screen/{id}/{userNameLocal}")
    object PhotoScreen: Routes(route = "photo_screen")
    object SuccesScreen: Routes(route = "succesScreen")
}
