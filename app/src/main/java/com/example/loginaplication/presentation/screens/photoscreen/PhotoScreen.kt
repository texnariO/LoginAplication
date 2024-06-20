package com.example.loginaplication.presentation.screens.photoscreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.loginaplication.presentation.screens.userscreen.UserViewModel

@Composable
fun PhotoScreen(
    idUser: Int,
    request: Boolean,
    navController: NavController,
    viewModel: PhotoViewModel = hiltViewModel()
) {

}