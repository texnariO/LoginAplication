package com.example.loginaplication.presentation.screens.startscreen

data class StartState (
    var pinUser: String = "",
    var userErorr: UserError? = null
){
    sealed class UserError{
        object FieldEmpty: UserError()
        object InputTooShort: UserError()
        object InputTooLong: UserError()
        object PinIncorrect: UserError()
    }
}
