package com.example.infogames.RegScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.infogames.Navigate.Screens
import com.example.infogames.State.ResultDataClass
import com.example.infogames.ViewModelsSupaBase.SignUpView

@Composable
fun RegScreen(navController: NavController, signUpView: SignUpView = viewModel()){

    val timeState by signUpView.resultState.collectAsState() //Получение состояния из SignInView
    val uiDataClass = signUpView.uiDataClass //Получение состояния UI

    Column(modifier = Modifier.fillMaxSize().padding(
        start = 45.dp,
        end = 45.dp,
    ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically)
    ) {
        TextFieldRegScreen(
            text = uiDataClass.email,
            label = "Email",
            onValueChange = {it -> signUpView.updateDataClass(uiDataClass.copy(email = it))}
        )
        TextFieldRegScreen(
            text = uiDataClass.username,
            label = "Никнейм",
            onValueChange = {it -> signUpView.updateDataClass(uiDataClass.copy(username = it))}
        )
        TextFieldRegScreen_Password(
            text = uiDataClass.password,
            label = "Пароль",
            onValueChange = {it -> signUpView.updateDataClass(uiDataClass.copy(password = it))}
        )
        TextFieldRegScreen_Password(
            text = uiDataClass.confirm_password,
            label = "Повторите пароль",
            onValueChange = {it -> signUpView.updateDataClass(uiDataClass.copy(confirm_password = it))}
        )

        when(timeState){
            is ResultDataClass.Error -> {
                Text((timeState as ResultDataClass.Error).message, fontSize = 15.sp, color = Color(0xff9b2d30),
                    fontWeight = FontWeight.Bold)
                ButtonRegScreen( text = "ЗАРЕГИСТРИРОВАТЬСЯ") { signUpView.signUp() }
            }
            is ResultDataClass.Initialized ->{
                ButtonRegScreen( text = "ЗАРЕГИСТРИРОВАТЬСЯ") { signUpView.signUp() }
            }
            is ResultDataClass.Loading -> {
                ButtonRegScreenLoading( text = "ЗАРЕГИСТРИРОВАТЬСЯ") { signUpView.signUp() }
            }
            is ResultDataClass.Success -> {
                navController.navigate(Screens.LogIn)
            }
        }

        Text("Вернуться к окну входа", fontSize = 10.sp, color = Color(0xff9b2d30),
            modifier = Modifier.clickable { navController.navigate(Screens.LogIn) },
            fontWeight = FontWeight.Bold)
    }
}