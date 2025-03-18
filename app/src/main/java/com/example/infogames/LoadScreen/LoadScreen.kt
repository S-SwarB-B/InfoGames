package com.example.infogames.LoadScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infogames.Navigate.Screens
import com.example.infogames.R
import kotlinx.coroutines.delay

@Composable
fun LoadScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(painter = painterResource(id = R.drawable.logotype), contentDescription = "Logotype",
            modifier = Modifier
                .width(224.dp)
                .height(183.dp))
        LinearProgressIndicator(color = Color(0xff9b2d30))

        }
    }
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate(Screens.LogIn){
            popUpTo(Screens.LoadScreen) {
                inclusive = true
            }
        }
    }
}