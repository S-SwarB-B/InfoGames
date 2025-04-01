package com.example.infogames.Tables

import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id: String,
    val username: String,
    val surname: String
)