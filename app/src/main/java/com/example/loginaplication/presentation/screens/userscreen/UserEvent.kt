package com.example.loginaplication.presentation.screens.userscreen

sealed class UserEvent{
    data class SaveID(val id: Int): UserEvent()
    object TakeInfoAboutUser: UserEvent()
    object StopPause: UserEvent()
    object StartPause: UserEvent()
}