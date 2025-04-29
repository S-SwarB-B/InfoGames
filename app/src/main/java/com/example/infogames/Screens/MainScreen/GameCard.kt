package com.example.infogames.Screens.MainScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.infogames.Tables.Games
import com.example.supabasesimpleproject.R

@Composable
fun GameCard(games: Games){
    Box(

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.padding(end = 10.dp, start = 10.dp, bottom = 15.dp, top = 10.dp))
        {
            Text(
                text = games.name,
                fontSize = 30.sp,
                color = Color(0xff9b2d30),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            AsyncImage(
                model = games.image,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                error = painterResource(R.drawable.logotype),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = games.description,
                fontSize = 15.sp,
                color = Color(0xff9b2d30),
                textAlign = TextAlign.Justify,
            )
        }
    }
}