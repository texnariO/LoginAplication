package com.example.loginaplication.presentation.screens.startscreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.loginaplication.R

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(state.value.userErorr){
        state.value.userErorr?.let{ error ->
            val errorMessage = when(error){
                StartState.UserError.FieldEmpty -> context.getString(R.string.error_mes_field_empty)
                StartState.UserError.InputTooLong -> context.getString(R.string.error_mes_input_too_long)
                StartState.UserError.InputTooShort -> context.getString(R.string.error_mes_input_too_short)
                StartState.UserError.PinIncorrect -> context.getString(R.string.error_mes_pin_incorrect)
            }
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(StartEvent.ClearError)
        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //ToDO delete basic text
        BasicText(
            text = "",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        val buttons = listOf(
            listOf("1","2","3"),
            listOf("4","5","6"),
            listOf("7","8","9"),
            listOf("X","0","→")
        )

        buttons.forEach{row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ){
                row.forEach{text ->
                    KeypadButton(text){
                        when(text){
                            "→" -> viewModel.onEvent(StartEvent.Login)
                            else -> viewModel.onEvent(StartEvent.EnteredPin(text))
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun KeypadButton(text: String, onClick: ()->Unit) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ){
        BasicText(
            text = text,
            style = TextStyle(fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
            )
    }
}