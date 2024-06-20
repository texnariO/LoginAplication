package com.example.loginaplication.di.data

import kotlinx.datetime.LocalDateTime


data class InformationAboutWorkingHours(
    val isStartWork: Boolean?,
    val isPaused: Boolean?,
    val howLongWorking: String?,
    val userName: String?
)
