package com.example.infogames.State

data class SignUpDataClass (
    val email: String = "",
    val password: String = "",
    val confirm_password: String = "",
    var ErrorLogSIG: Boolean = false,
    var ErrorPasSIG: Boolean = false
)