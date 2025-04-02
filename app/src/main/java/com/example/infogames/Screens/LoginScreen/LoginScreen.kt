package com.example.infogames.Screens.LoginScreen

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
import com.example.infogames.ViewModelsSupaBase.SigInView

@Composable
fun LoginScreen(navController: NavController, signInView: SigInView = viewModel()){

    val timeState by signInView.resultState.collectAsState() //Получение состояния из SignInView
    val uiDataClass = signInView.uiDataClass //Получение состояния UI

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(
            start = 45.dp,
            end = 45.dp
        ),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically)
    ) {

        TextFieldLogScreen(
            txtvalue = uiDataClass.email,
            label = "Email",
            onValueChange = {it -> signInView.updateDataClass(uiDataClass.copy(email = it))}
        )

        TextFieldLogScreen_Password(
            text = uiDataClass.password,
            label = "Пароль",
            onValueChange = {it -> signInView.updateDataClass(uiDataClass.copy(password = it))}
        )
        when (timeState){
            is ResultDataClass.Error -> {
                Text((timeState as ResultDataClass.Error).message, fontSize = 15.sp, color = Color(0xff9b2d30),
                    fontWeight = FontWeight.Bold)
                ButtonLogScreen(
                    text = "ВОЙТИ"
                ){
                    signInView.signIn()
                }
            }
            is ResultDataClass.Initialized -> {
                ButtonLogScreen(
                    text = "ВОЙТИ"
                ){
                    signInView.signIn()
                }
            }
            is ResultDataClass.Loading -> {
                ButtonLogScreenLoading(
                    text = "Загрузка..."
                ){}
            }
            is ResultDataClass.Success -> {
                navController.navigate(Screens.Main){}
            }
        }

        Text("Зарегистрироваться", fontSize = 10.sp, color = Color(0xff9b2d30),
            modifier = Modifier.clickable { navController.navigate(Screens.Reg) },
            fontWeight = FontWeight.Bold)
    }
}