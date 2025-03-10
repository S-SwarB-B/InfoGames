package com.example.infogames.RegScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldRegScreen(
    text: String,
    label: String,
    onValueChange: (String) ->  Unit
){
    TextField(value = text, onValueChange = {
        onValueChange(it)
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
        maxLines = 1,
        label = {
            Text(text = label, color = Color(0xff9b2d30))
        }
    )
}

@Composable
fun ButtonRegScreen(
    text: String,
    onClick: () -> Unit
){
    Button(onClick = {
        onClick()
    },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff9b2d30)
        ),
        modifier = Modifier.width(250.dp)
    ) {
        Text(text = text)
    }
}