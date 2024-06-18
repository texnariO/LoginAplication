package com.example.loginaplication.presentation.screens.startscreen

sealed class StartEvent {
    data class EnteredPin(val pinText: String): StartEvent()
    object ClearError: StartEvent()
    object Login: StartEvent()
}