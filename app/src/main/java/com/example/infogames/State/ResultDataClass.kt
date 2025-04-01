package com.example.infogames.State

sealed class ResultDataClass {
    data object Loading : ResultDataClass()
    data object Initialized : ResultDataClass()
    data class Success (val message: String) : ResultDataClass()
    data class Error(val message: String) : ResultDataClass()
}
