package com.example.loginaplication.di

import com.example.loginaplication.di.data.InformationAboutWorkingHours
import com.example.loginaplication.di.data.User
import com.example.loginaplication.presentation.util.Constants.GET_USER_INFO_ABOUT_WORKING_HOURS
import com.example.loginaplication.presentation.util.Constants.GET_USER_INFO_FROM_ID
import com.example.loginaplication.presentation.util.Constants.GET_USER_INFO_FROM_PIN
import com.example.loginaplication.presentation.util.Constants.START_PAUSE
import com.example.loginaplication.presentation.util.Constants.STOP_PAUSE
import com.example.loginaplication.presentation.util.SimpleRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DatabaseApi {

    @GET(GET_USER_INFO_FROM_PIN)
    suspend fun getInfoAboutUserUsingPIN(@Path("pin") pin: String): Response<User>

    @GET(GET_USER_INFO_FROM_ID)
    suspend fun getInfoAboutUserUsingID(@Path("id") id: Int): Response<User>

    @GET(GET_USER_INFO_ABOUT_WORKING_HOURS)
    suspend fun getInfoAboutWorkingHoursUsingID(@Path("id") id: Int): Response<InformationAboutWorkingHours>

    @POST(START_PAUSE)
    suspend fun startPause(@Path("id") id: Int): Response<Boolean>

    @PUT(STOP_PAUSE)
    suspend fun stopPause(@Path("id") id: Int): Response<Boolean>

    companion object{
        const val  BASE_URL = "http://192.168.1.5:8080/"
    }
}
