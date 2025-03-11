package com.example.infogames.RegScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.infogames.Navigate.Screens

@Composable
fun RegScreen(navController: NavController){

    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }
    val passwordState2 = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize().padding(
        start = 45.dp,
        end = 45.dp,
    ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically)
    ) {
        TextFieldRegScreen(
            text = emailState.value,
            label = "Логин или Email"
        ) {
            emailState.value = it
        }

        TextFieldRegScreen_Password(
            text = passwordState.value,
            label = "Пароль"
        ) {
            passwordState.value = it
        }
        TextFieldRegScreen_Password(
            text = passwordState2.value,
            label = "Повторите пароль"
        ) {
            passwordState2.value = it
        }
        ButtonRegScreen(
            text = "ЗАРЕГИСТРИРОВАТЬСЯ"
        ) {}

        Text("Вернуться к окну входа", fontSize = 10.sp, color = Color(0xff9b2d30),
            modifier = Modifier.clickable { navController.navigate(Screens.LogIn) },
            fontWeight = FontWeight.Bold)
    }
}