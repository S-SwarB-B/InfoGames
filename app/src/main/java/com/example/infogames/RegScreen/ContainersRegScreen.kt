package com.example.infogames.RegScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun TextFieldRegScreen_Password(
    text: String,
    label: String,
    onValueChange: (String) ->  Unit
){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

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
        },
        visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon  = {
            val image = if (passwordVisible)
                Icons.Filled.Check
            else Icons.Filled.Clear

            IconButton(onClick = {passwordVisible = !passwordVisible}){

            }
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
@Composable
fun ButtonRegScreenLoading(
    text: String,
    onClick: () -> Unit
){
    Button(onClick = {
        onClick()
    },
        enabled = false,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff9b2d30)
        ),
        modifier = Modifier.width(250.dp)
    ) {
        Text(text = text)
    }
}