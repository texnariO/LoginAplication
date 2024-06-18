package com.example.loginaplication.presentation.screens.startscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loginaplication.presentation.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(): ViewModel(){
    private val _state = mutableStateOf(StartState())
    val state: State<StartState> = _state

    fun onEvent(event: StartEvent){
        when(event){
            is StartEvent.EnteredPin -> {
                val newPin = when(event.pinText){
                    "X" -> if(_state.value.pinUser.isNotEmpty()) _state.value.pinUser.dropLast(1) else _state.value.pinUser
                    else -> _state.value.pinUser + event.pinText
                }
                _state.value = _state.value.copy(
                    pinUser = newPin
                )
            }
            is StartEvent.Login -> {
                validatePin(state.value.pinUser)
            }
            is StartEvent.ClearError ->{
                _state.value = _state.value.copy(
                    userErorr =  null
                )
            }
        }
    }


    private fun validatePin(pin: String){
        if(pin.isBlank()){
            _state.value = _state.value.copy(
                userErorr = StartState.UserError.FieldEmpty
            )
            return
        }
        if(pin.length < Constants.PIN_LENGTH){
            _state.value = _state.value.copy(
                userErorr = StartState.UserError.InputTooShort
            )
            return
        }
        if(pin.length > Constants.PIN_LENGTH){
            _state.value = _state.value.copy(
                userErorr  = StartState.UserError.InputTooLong

            )
            return
        }

        /*validate from Database
        if(pin ){
            _state.value = _state.value.copy(
                userErorr =  StartState.UserError.PinIncorrect
                )
                return
        }
         */
    }
}