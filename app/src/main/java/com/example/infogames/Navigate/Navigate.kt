package com.example.infogames.Navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.infogames.Screens.LoadScreen.LoadScreen
import com.example.infogames.Screens.LoginScreen.LoginScreen
import com.example.infogames.Screens.MainScreen.MainScreen
import com.example.infogames.Screens.CreateScreen.CreateScreen
import com.example.infogames.Screens.MoreDetailedScreen.MoreDetailedScreen
import com.example.infogames.Screens.RegScreen.RegScreen


@Composable
fun Navigate(){
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = Screens.LogIn
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
            composable(Screens.CreateScreen) {
                CreateScreen(navController)
            }
            composable(Screens.MoreDetailed + "/{id}",
                arguments = listOf(navArgument("id"){
                    type = NavType.StringType
                })
                ){
                val id = it.arguments?.getString("id")

                if(id != null){
                    MoreDetailedScreen(navController, id)
                }
            }
        }
}