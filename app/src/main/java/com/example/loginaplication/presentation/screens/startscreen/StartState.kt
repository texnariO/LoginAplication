package com.example.loginaplication.presentation.screens.startscreen

data class StartState (
    var pinUser: String = "",
    var userErorr: UserError? = null,
    var startNext: StartNext? = StartNext.notReady,
    var idUser: Int? = null,
    var nameUser: String? = null
){
    sealed class StartNext{
        object goToNextWindow: StartNext()
        object notReady: StartNext()
    }
    sealed class UserError{
        object FieldEmpty: UserError()
        object InputTooShort: UserError()
        object InputTooLong: UserError()
        object PinIncorrect: UserError()
    }
}
