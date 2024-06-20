package com.example.loginaplication.presentation.screens.userscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.loginaplication.di.data.User
import com.example.loginaplication.presentation.screens.startscreen.StartState
import com.example.loginaplication.presentation.screens.startscreen.StartViewModel
import com.example.loginaplication.presentation.util.navigation.Routes
import com.example.loginaplication.ui.theme.ButtonAnulatedColor
import com.example.loginaplication.ui.theme.ButtonPauseColor
import com.example.loginaplication.ui.theme.ButtonStartWorkColor
import com.example.loginaplication.ui.theme.ButtonStopWorkColor
import kotlinx.coroutines.isActive

@Composable
fun UserScreen(
    idUser: Int,
    userName: String,
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LaunchedEffect(Unit){
        viewModel.onEvent(UserEvent.SaveID(idUser))
        viewModel.onEvent(UserEvent.TakeInfoAboutUser)
    }
    LaunchedEffect(state.value.isSucess ){
        state.value.isSucess?.let{isSucess ->
            if(isSucess == true){
                navController.navigate(Routes.SuccesScreen.route)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                TimeDisplay(state)
                Spacer(modifier = Modifier.height(16.dp))
                if (state.value.idUser != null && state.value.itWorking == false) {
                    ControlBoxStartWorkButtons(state, navController)
                }else{
                    if(state.value.idUser!=null && state.value.itWorking == true && state.value.isPaused == false)
                        ControlBoxButtons(state = state, navController =  navController, viewModel = viewModel)
                    if(state.value.idUser!= null && state.value.itWorking == true && state.value.isPaused == true)
                        ControlBoxPauseOnButtons(state = state, navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun ControlBoxStartWorkButtons(
    state: State<UserState>,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { navController.navigate("photo_screen/${state.value.idUser}/${true}") },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonStartWorkColor),
            modifier = Modifier
                .clip(CircleShape)
                .padding(8.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = "Rozpocznij pracę", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate(Routes.StartScreen.route) },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonAnulatedColor),
            modifier = Modifier
                .clip(CircleShape)
                .padding(8.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = "Anuluj", color = Color.White)
        }
    }
}

@Composable
fun ControlBoxPauseOnButtons(
    state: State<UserState>,
    navController: NavController,
    viewModel: UserViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                      navController.navigate("photo_screen/${state.value.idUser}/${false}")
            },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonStopWorkColor),
            modifier = Modifier
                .clip(CircleShape)
                .padding(8.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = "Zakończ pracę", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                      viewModel.onEvent(UserEvent.StopPause)
            },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonPauseColor),
            modifier = Modifier
                .clip(CircleShape)
                .padding(8.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = "Zakończ przerwe", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate(Routes.StartScreen.route) },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonAnulatedColor),
            modifier = Modifier
                .clip(CircleShape)
                .padding(8.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = "Anuluj", color = Color.White)
        }
    }
}

@Composable
fun ControlBoxButtons(
    state: State<UserState>,
    navController: NavController,
    viewModel: UserViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigate("photo_screen/${state.value.idUser}/${false}")
            },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonStopWorkColor),
            modifier = Modifier
                .clip(CircleShape)
                .padding(8.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = "Zakończ pracę", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                      viewModel.onEvent(UserEvent.StartPause)
            },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonPauseColor),
            modifier = Modifier
                .clip(CircleShape)
                .padding(8.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = "Rozpocznij przerwę", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate(Routes.StartScreen.route) },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonAnulatedColor),
            modifier = Modifier
                .clip(CircleShape)
                .padding(8.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = "Anuluj", color = Color.White)
        }
    }
}

@Composable
fun TimeDisplay(
    state: State<UserState>,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f))
            .padding(32.dp)
    ) {
        Text(
            text = "Cześć ",
            color = Color.White,
            fontSize = 15.sp
        )
        Text(
            text = state.value.userInfo?.name ?:  "Loading",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


