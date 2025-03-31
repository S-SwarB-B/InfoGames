package com.example.infogames.State

data class SignInDateClass(
    val email: String = "",
    val password: String = "",
    var ErrorLogSIG: Boolean = false,
    var ErrorPasSIG: Boolean = false
)
