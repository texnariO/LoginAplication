package com.example.loginaplication.presentation.screens.userscreen

import com.example.loginaplication.di.data.User
import kotlinx.datetime.LocalDateTime
import java.util.Date

data class UserState(
    var idUser: Int? = null,
    var userInfo: User? = null,
    var itWorking: Boolean = false,
    var timeStartWork: LocalDateTime? = null,
    var uploadInfo: UploadInfo? = UploadInfo.Loading,
    var isPaused: Boolean = false,
    var isSucess: Boolean = false,
){
    sealed class UploadInfo{
        object Loading: UploadInfo()
        object Ready: UploadInfo()
    }
}