package com.example.infogames.Screens.MoreDetailedScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.infogames.Tables.Categories

@Composable
fun MoreDetailedButtonDelUpd(onClick: () -> Unit, text:String){
    Box(
        modifier = Modifier.padding(start = 5.dp, end = 5.dp)
    ){
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color(0xff9b2d30),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(10.dp)
            .width(150.dp)
            .height(40.dp)
            .background(color = Color(0xff9b2d30))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,

        ){
        Text(text = text, color = Color.White, fontWeight = FontWeight(1000), fontSize = 26.sp)
        }
    }
}

@Composable
fun TextFieldUpdate(text: String, label: String, onValueChanged: (String) -> Unit){
    TextField(
        value = text,
        onValueChange = {
            onValueChanged(it)
        },
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            cursorColor = Color(0xff9b2d30),
            unfocusedTextColor = Color(0xff9b2d30),
            focusedTextColor = Color(0xff9b2d30)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xff9b2d30), RoundedCornerShape(15.dp)),
        label = {
            Text(text = label, color = Color(0xff9b2d30))
        }
    )
}

@Composable
fun TextFieldCategoriesUpdate(text: String){
    TextField(
        value = text,
        onValueChange = {
        },
        enabled = false,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            cursorColor = Color(0xff9b2d30),
            unfocusedTextColor = Color(0xff9b2d30),
            focusedTextColor = Color(0xff9b2d30)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xff9b2d30), RoundedCornerShape(15.dp)),
    )
}

@Composable
fun CatregoriesButton(typeCategories: Categories?, onClick: () -> Unit){
    Box(
        modifier = Modifier.padding(start = 5.dp, end = 5.dp)
    ){
        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(0xff9b2d30),
                    shape = RoundedCornerShape(0.dp)
                )
                .clickable(onClick = onClick,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() })
                .padding(10.dp)
                .width(100.dp),
            contentAlignment = Alignment.Center,
        ){
            Text(text = typeCategories?.name ?: "", color = Color(0xff9b2d30), textAlign = TextAlign.Center)
        }
    }
}