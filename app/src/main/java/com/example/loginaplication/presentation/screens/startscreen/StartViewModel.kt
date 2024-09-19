package com.example.loginaplication.presentation.screens.startscreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginaplication.di.AppModule
import com.example.loginaplication.di.DatabaseApi
import com.example.loginaplication.presentation.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
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
        val databaseApi = AppModule.provideDatabaseApi()
        viewModelScope.launch {
            try {
                Log.d("UserViewModel", "Sending request to /user/$pin")
                val response = databaseApi.getInfoAboutUserUsingPIN(_state.value.pinUser)
                Log.d("UserViewModel", "Received response: $response")
                if(response.isSuccessful)
                {
                    _state.value = _state.value.copy(
                        idUser = response.body()!!.id,
                        nameUser = response.body()!!.name,
                        startNext = StartState.StartNext.goToNextWindow
                    )
                }else{
                    _state.value = _state.value.copy(
                        userErorr = StartState.UserError.PinIncorrect
                    )
                }
            } catch (e: IOException) {
                Log.e("UserViewModel", "Network error", e)
            } catch (e: HttpException) {
                Log.e("UserViewModel", "Server error", e)
            }
        }
    }
}