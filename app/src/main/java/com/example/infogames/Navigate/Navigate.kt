package com.example.infogames.Navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.infogames.Screens.LoadScreen.LoadScreen
import com.example.infogames.Screens.LoginScreen.LoginScreen
import com.example.infogames.Screens.MainScreen.MainScreen
import com.example.infogames.Screens.RegScreen.RegScreen


@Composable
fun Navigate(){
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = Screens.Main
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
            composable(Screens.Main) {
                MainScreen(navController)
            }
        }
}