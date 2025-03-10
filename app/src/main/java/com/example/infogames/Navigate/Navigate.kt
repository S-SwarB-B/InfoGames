package com.example.infogames.Navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.infogames.LoadScreen.LoadScreen
import com.example.infogames.LoginScreen.LoginScreen
import com.example.infogames.RegScreen.RegScreen


@Composable
fun Navigate(){
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = Screens.LoadScreen
        ){
            composable(Screens.LoadScreen)
            {
                LoadScreen(navController)
            }
            composable(Screens.LogIn) {
                LoginScreen(navController)
            }
            composable(Screens.Reg) {
                RegScreen(navController)
            }
        }
}