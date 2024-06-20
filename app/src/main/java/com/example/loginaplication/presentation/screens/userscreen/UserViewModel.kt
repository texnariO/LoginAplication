package com.example.loginaplication.presentation.screens.userscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginaplication.di.AppModule
import com.example.loginaplication.di.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import retrofit2.HttpException
import java.io.IOException
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state
    val databaseApi = AppModule.provideDatabaseApi()

    fun takeInfoFromDatabase(){
        viewModelScope.launch {
            if (_state.value.idUser != null) {
                try {
                    val response = databaseApi.getInfoAboutUserUsingID(_state.value.idUser!!)
                    if(response.isSuccessful){
                        _state.value = _state.value.copy(
                            userInfo = response.body()
                        )
                        val request = databaseApi.getInfoAboutWorkingHoursUsingID(_state.value.idUser!!)
                        if(request.isSuccessful){
                            _state.value = _state.value.copy(
                                itWorking = request.body()?.isStartWork!!,
                                timeStartWork = LocalDateTime.parse(request.body()?.howLongWorking!!),
                                isPaused =  request.body()?.isPaused!!,
                                uploadInfo = UserState.UploadInfo.Ready
                            )
                        }
                    }
                } catch (e: IOException) {
                    Log.e("UserViewModel", "Network error", e)
                } catch (e: HttpException) {
                    Log.e("UserViewModel", "Server error", e)
                }
            }
        }

    }
    fun stopPause(){
        viewModelScope.launch {
            try{
                val response = databaseApi.stopPause(state.value.idUser!!)
                if(response.isSuccessful){
                    _state.value = _state.value.copy(
                        isSucess = true
                    )
                }
            }catch (e: IOException) {
                Log.e("UserViewModel", "Network error", e)
            } catch (e: HttpException) {
                Log.e("UserViewModel", "Server error", e)
            }
        }
    }

    fun startPause(){
        viewModelScope.launch {
            try{
                val response = databaseApi.startPause(state.value.idUser!!)
                if(response.isSuccessful){
                    _state.value = _state.value.copy(
                        isSucess = true
                    )
                }
            }catch (e: IOException) {
                Log.e("UserViewModel", "Network error", e)
            } catch (e: HttpException) {
                Log.e("UserViewModel", "Server error", e)
            }
        }
    }

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.SaveID ->{
                _state.value = _state.value.copy(
                    idUser = event.id
                )
            }
            is UserEvent.TakeInfoAboutUser -> {
                takeInfoFromDatabase()
            }
            is UserEvent.StartPause ->{
                startPause()
            }
            is UserEvent.StopPause ->{
                stopPause()
            }
        }
    }

}
